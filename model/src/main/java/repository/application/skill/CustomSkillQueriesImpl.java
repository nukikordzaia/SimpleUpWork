package repository.application.skill;

import model.application.Skill;

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
	public List<Skill> filterByAttributes(String name) {
		StringBuilder sql = new StringBuilder("SELECT a from Skill a WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null) {
			sql.append("AND a.name = :name ");
			params.put("name", name);
		}

		TypedQuery<Skill> query = em.createQuery(sql.toString(), Skill.class);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
}
