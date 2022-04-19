package com.simpleupwork.controllers.application;

import com.simpleupwork.services.application.AppService;
import com.simpleupwork.model.application.Application;
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

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	AppService appService;

	@GetMapping("/all")
	public ResponseEntity<ListResult<Application>> filterByAttributes(@RequestParam(required = false) String name,
																	  @RequestParam(required = false) String skills,
																	  @RequestParam(required = false) Long salary,
																	  @RequestParam(required = false) Boolean active,
																	  @RequestParam(required = false) Date createTime,
																	  @RequestParam(required = false) Date startDate,
																	  @RequestParam(required = false) Date endDate,
																	  @RequestParam(required = false) int limit,
																	  @RequestParam(required = false) int page) {
		return ResponseEntity.ok(appService.filterByAttributes(name, skills, salary, active, createTime, startDate, endDate,
			limit, page));
	}

	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		appService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody Application application) {
		Application newApplication = new Application();
		newApplication.setRequiredSkills(application.getRequiredSkills());
		newApplication.setName(application.getName());
		newApplication.setDescription(application.getDescription());
		newApplication.setSalary(application.getSalary());
		newApplication.setActive(application.isActive());
		newApplication.setCreateTime(new Date());
		newApplication.setLastUpdateTime(new Date());
		newApplication.setStartDate(application.getStartDate());
		appService.save(newApplication);
	}

	@PutMapping("/update")
	public void update(@RequestBody Application application) {
		appService.findById(application.getId())
			.map(appInDB -> {
				appInDB.setRequiredSkills(application.getRequiredSkills());
				appInDB.setName(application.getName());
				appInDB.setDescription(application.getDescription());
				appInDB.setSalary(application.getSalary());
				appInDB.setActive(application.isActive());
				appInDB.setCreateTime(application.getCreateTime());
				appInDB.setStartDate(application.getStartDate());
				appInDB.setLastUpdateTime(new Date());
				return appService.save(appInDB);
			});
	}

	@PutMapping("/update/active")
	public void updateActivity(@RequestBody Application application) {
		Optional<Application> appInDB = appService.findById(application.getId());
		appInDB.get().setActive(!application.isActive());
	}
}
