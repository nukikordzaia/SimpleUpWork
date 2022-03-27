package com.simpleupwork.controllers.user;

import com.simpleupwork.model.user.SystemUser;
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
import service.user.UserService;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/all")
	public ResponseEntity<ListResult<SystemUser>> filterByAttributes(@RequestParam(required = false) String firstName,
																	 @RequestParam(required = false) String lastName,
																	 @RequestParam(required = false) String username,
																	 @RequestParam(required = false) String title,
																	 @RequestParam(required = false) String email,
																	 @RequestParam(required = false) String group,
																	 @RequestParam(required = false) String skill,
																	 @RequestParam(required = false) Boolean active,
																	 @RequestParam(required = false) int limit,
																	 @RequestParam(required = false) int page) {


		return ResponseEntity.ok(userService.filterByAttributes(firstName, lastName, username, title, email, group, skill,
			active, limit, page));
	}

	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody SystemUser user) {
		SystemUser newUser = new SystemUser();
		newUser.setCreateTime(new Date());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUsername(user.getUsername());
		newUser.setTitle(user.getTitle());
		newUser.setBio(user.getBio());
		newUser.setSalary(user.getSalary());
		newUser.setImage(user.getImage());
		newUser.setEmail(user.getEmail());
		newUser.setGroups(user.getGroups());
		newUser.setSkills(user.getSkills());
		newUser.setPassword(user.getPassword());
		newUser.setActive(true);
		userService.save(newUser);
	}

	@PutMapping("/update")
	public void update(@RequestBody SystemUser user) {
		userService.findById(user.getId())
			.map(userInDB -> {
				userInDB.setFirstName(user.getFirstName());
				userInDB.setLastName(user.getLastName());
				userInDB.setUsername(user.getUsername());
				userInDB.setTitle(user.getTitle());
				userInDB.setBio(user.getBio());
				userInDB.setImage(user.getImage());
				userInDB.setSalary(user.getSalary());
				userInDB.setEmail(user.getEmail());
				if (!user.getPassword().equals("")) {
					userInDB.setPassword(user.getPassword());
				}
				userInDB.setGroups(user.getGroups());
				userInDB.setSkills(user.getSkills());
				userInDB.setActive(user.isActive());
				return userService.save(userInDB);
			});
	}

	@PutMapping("/update/active")
	public void updateActivity(@RequestBody SystemUser user) {
		Optional<SystemUser> userInDB = userService.findById(user.getId());
		userInDB.get().setActive(!user.isActive());
	}
}
