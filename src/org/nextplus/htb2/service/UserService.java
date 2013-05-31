package org.nextplus.htb2.service;

import java.util.List;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends UserDetailsService {

	@Transactional(rollbackFor = Exception.class)
	Long createUser(User user) throws DuplicateException;

	@Transactional(rollbackFor = Exception.class)
	void updateUser(User user) throws DuplicateException;

	@Transactional
	void deleteUser(Long userId);

	@Transactional(readOnly = true)
	<T extends User> T getUser(Class<T> clazz, Long userId);

	@Transactional(readOnly = true)
	<T extends User> List<T> getUsers(Class<T> clazz, Paginator paginator);

}
