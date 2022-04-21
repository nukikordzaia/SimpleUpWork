package com.simpleupwork.application;

import com.simpleupwork.model.application.Skill;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillController {

	@Autowired
	SkillService skillService;


	@GetMapping("/all")
	public ResponseEntity<ListResult<Skill>> filterByAttributes(@RequestParam(required = false) String name) {
		return ResponseEntity.ok(skillService.filterByAttributes(name));
	}

	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		skillService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody Skill skill) {
		Skill newSkill = new Skill();
		newSkill.setName(skill.getName());
	}

	@PutMapping("/update")
	public void update(@RequestBody Skill skill) {
		skillService.findById(skill.getId())
			.map(skillInDB -> {
				skillInDB.setName(skill.getName());
				return skillService.save(skillInDB);
			});
	}
}
