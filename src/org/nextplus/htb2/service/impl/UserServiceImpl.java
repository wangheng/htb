package org.nextplus.htb2.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.proxy.HibernateProxy;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.HibernateTemplate;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.User;
import org.nextplus.htb2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public Long createUser(User user) throws DuplicateException {
		Assert.notNull(user);
		Assert.isTrue(user.isNew());

		User o;
		try {
			o = (User) loadUserByUsername(user.getUsername());
			if (o != null) {
				throw new DuplicateException(String.format("[%s]已经存在", user.getUsername()));
			}
		} catch (UsernameNotFoundException e) {
			user.setCreateDate();
			Long userId = (Long) template.save(user);
			return userId;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User user) throws DuplicateException {
		Assert.notNull(user);
		Assert.isTrue(!user.isNew());

		User o = (User) loadUserByUsername(user.getUsername());
		if (o == null || o.equals(user)) {
			template.merge(user);
		} else {
			throw new DuplicateException(String.format("[%s]已经存在", user.getUsername()));
		}
	}

	@Override
	public void deleteUser(Long userId) {
		User user = template.load(User.class, userId);
		template.delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T getUser(Class<T> clazz, Long userId) {
		T user = template.get(clazz, userId);
		if (user instanceof HibernateProxy) {
			Hibernate.initialize(user);
			user = (T) ((HibernateProxy) user).getHibernateLazyInitializer().getImplementation();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> List<T> getUsers(Class<T> clazz, Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.addOrder(Order.asc("username"));
		List<T> users = template.findByCriteria(criteria, paginator);
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Assert.hasText(username);
		// 系统用户
		String hql = "from User u where u.username = ?";
		List<User> users = template.find(hql, username);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException(String.format("用户[%s]不存在。", username));
		}
		return users.get(0);
	}

	// 依赖注入

	private HibernateTemplate template;

	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
