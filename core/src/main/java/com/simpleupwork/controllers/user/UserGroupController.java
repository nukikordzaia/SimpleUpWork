package com.simpleupwork.controllers.user;

import com.simpleupwork.model.user.UserGroup;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.user.UserGroupService;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/userGroups")
public class UserGroupController {

	@Autowired
	UserGroupService userGroupService;

	@GetMapping("/all")
	public ResponseEntity<ListResult<UserGroup>> filterByAttributes(@RequestParam(required = false) String name,
																	@RequestParam(required = false) String permission,
																	@RequestParam(required = false) Boolean active,
																	@RequestParam(required = false) int limit,
																	@RequestParam(required = false) int page) {
		return ResponseEntity.ok(userGroupService.filterByAttributes(name, permission, active, limit, page));
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Long id) {
		userGroupService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody UserGroup userGroup) {
		UserGroup newUserGroup = new UserGroup();
		newUserGroup.setCreateTime(new Date());
		newUserGroup.setName(userGroup.getName());
		newUserGroup.setPermissions(userGroup.getPermissions());
		newUserGroup.setActive(true);
	}

	@PutMapping("/update")
	public void update(@RequestBody UserGroup userGroup) {
		userGroupService.findById(userGroup.getId())
			.map(userInDB -> {
				userInDB.setName(userGroup.getName());
				userInDB.setPermissions(userGroup.getPermissions());
				userInDB.setActive(userGroup.isActive());
				return userGroupService.save(userInDB);
			});
	}

	@PutMapping("/update/active")
	public void updateActivity(@RequestBody UserGroup userGroup) {
		Optional<UserGroup> groupInDB = userGroupService.findById(userGroup.getId());
		groupInDB.get().setActive(!userGroup.isActive());
	}
}
