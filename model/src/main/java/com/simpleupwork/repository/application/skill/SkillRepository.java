package com.simpleupwork.repository.application.skill;

import com.simpleupwork.model.application.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>, CustomSkillQueries{

}
