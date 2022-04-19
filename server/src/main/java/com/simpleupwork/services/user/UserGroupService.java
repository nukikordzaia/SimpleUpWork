package com.simpleupwork.services.user;

import com.simpleupwork.model.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simpleupwork.repository.user.usergroup.UserGroupRepository;
import com.simpleupwork.utils.ListResult;

import java.util.Optional;

@Service
public class UserGroupService {

	@Autowired
	private UserGroupRepository userGroupRepository;

	public Optional<UserGroup> findById(Long id) { return userGroupRepository.findById(id); }

	public ListResult<UserGroup> filterByAttributes(String name, String permission, Boolean active, int limit, int page) {
		return userGroupRepository.filterByAttributes(name, permission, active, limit, page);
	}

	public void delete(Long id) {
		userGroupRepository.deleteById(id);
	}

	public UserGroup save(UserGroup userGroup) {
		return userGroupRepository.save(userGroup);
	}
}
