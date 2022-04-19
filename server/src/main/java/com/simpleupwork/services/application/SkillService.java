package com.simpleupwork.services.application;

import com.simpleupwork.model.application.Skill;
import com.simpleupwork.repository.application.skill.SkillRepository;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;

	public Optional<Skill> findById(Long id) {
		return skillRepository.findById(id);
	}

	public ListResult<Skill> filterByAttributes(String name) {
		return skillRepository.filterByAttributes(name);
	}

	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	public void delete(Long id) {
		skillRepository.deleteById(id);
	}
}
