package com.simpleupwork.repository.application.skill;

import com.simpleupwork.model.application.Skill;
import com.simpleupwork.utils.ListResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSkillQueriesImpl implements CustomSkillQueries {

	@PersistenceContext
	EntityManager em;

	@Override
	public ListResult<Skill> filterByAttributes(String name) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null) {
			sql.append("AND a.name = :name ");
			params.put("name", name);
		}

		TypedQuery<Skill> query = em.createQuery("SELECT a from Skill a WHERE 1=1 " + sql, Skill.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(a.id) FROM Skill a WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		List<Skill> skills = query.getResultList();

		return new ListResult<>(skills, count.getSingleResult().intValue());
	}
}
