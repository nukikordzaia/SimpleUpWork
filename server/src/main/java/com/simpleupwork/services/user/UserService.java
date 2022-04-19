package com.simpleupwork.services.user;

import com.simpleupwork.model.user.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simpleupwork.repository.user.SystemUserRepository;
import com.simpleupwork.utils.ListResult;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private SystemUserRepository userRepository;

	public Optional<SystemUser> findById(Long id) {
		return userRepository.findById(id);
	}

	public ListResult<SystemUser> filterByAttributes(String firstName, String lastName, String username, String title,
													 String email, String userGroups, String skills, Boolean active,
													 int limit, int page) {
		return userRepository.filterByAttributes(firstName, lastName, username, title,
			email, userGroups, skills, active, limit, page);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public SystemUser save(SystemUser user) {
		return userRepository.save(user);
	}
}
