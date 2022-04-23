package com.simpleupwork.auth;

import com.simpleupwork.model.user.SystemUser;
import com.simpleupwork.model.user.UserGroup;
import com.simpleupwork.repository.user.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SystemUserDetailsService implements UserDetailsService {

	@Autowired
	SystemUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser user = userRepository.findByUsername(username);
		if (user == null || !user.isActive()) {
			throw new UsernameNotFoundException("User not found!");
		}
		return User.withUsername(user.getUsername())
			.password(user.getPassword())
			.authorities(getAuthorities(user))
			.build();
	}

	private Collection<GrantedAuthority> getAuthorities(SystemUser user) {
		List<UserGroup> userGroups = user.getGroups();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (UserGroup group : userGroups) {
			String[] permissions = group.getPermissions().split(",");
			for (String permission : permissions) {
				authorities.add(new SimpleGrantedAuthority(permission));
			}
		}
		return authorities;
	}
}
