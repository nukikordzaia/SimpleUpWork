package com.simpleupwork.repository.application.skill;

import com.simpleupwork.model.application.Skill;
import com.simpleupwork.utils.ListResult;

public interface CustomSkillQueries {

	ListResult<Skill> filterByAttributes(String name, int limit, int page);
}
