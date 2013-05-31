package org.nextplus.htb2.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends Domain implements UserDetails {

	private static final long serialVersionUID = 7660967392830483492L;

	// Authentications.
	private String username;
	private String password;
	// Basic Info.
	private String fullname;

	public User() {
		super();
	}

	public User(Long id) {
		super(id);
	}

	public User(Long id, String username, String password, String fullname) {
		super(id);
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}

	@Transient
	public abstract List<Role> getRoles();

	@Transient
	public Role getRole() {
		List<Role> roles = getRoles();
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		return roles.get(0);
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		String rolesValue = StringUtils.collectionToCommaDelimitedString(getRoles());
		return AuthorityUtils.commaSeparatedStringToAuthorityList(rolesValue);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFullname());
		if (!CollectionUtils.isEmpty(getRoles())) {
			sb.append("[" + getRoles().get(0).getEx() + "]");
		}
		return sb.toString();
	}

	@Override
	@Transient
	public boolean isDeletable() {
		return super.isDeletable();
	}

	// Mutators

	@Override
	@NotBlank
	@Length(max = 255)
	@Column(nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	@NotBlank
	@Length(max = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	@Length(max = 255)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * 角色
	 *
	 * @author Daniel
	 *
	 */
	public enum Role {

		ROLE_ADMINISTRATOR("Administrator", "/backend/"),
		ROLE_MEMBER("Member", "/cs/");

		private String name;
		private String prefix;

		private Role(String name, String prefix) {
			this.name = name;
			this.prefix = prefix;
		}

		public String getEx() {
			return name;
		}

		public String getPrefix() {
			return prefix;
		}

	}

	/**
	 * 性别
	 *
	 * @author Daniel
	 *
	 */
	public enum Gender {

		MALE("男"),
		FEMALE("女");

		private String name;

		private Gender(String name) {
			this.name = name;
		}

		public String getEx() {
			return name;
		}

	}

}
