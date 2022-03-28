package com.simpleupwork.service.application;

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

	public ListResult<Skill> filterByAttributes(String name, int limit, int page) {
		return skillRepository.filterByAttributes(name, limit, page);
	}

	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	public void delete(Long id) {
		skillRepository.deleteById(id);
	}
}
