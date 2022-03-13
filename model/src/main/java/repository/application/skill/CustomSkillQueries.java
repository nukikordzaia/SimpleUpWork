package repository.application.skill;

import model.application.Skill;

import java.util.List;

public interface CustomSkillQueries {

	List<Skill> filterByAttributes(String name);
}
