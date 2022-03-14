package repository.application.skill;

import model.application.Skill;
import utils.ListResult;

public interface CustomSkillQueries {

	ListResult<Skill> filterByAttributes(String name, int limit, int page);
}
