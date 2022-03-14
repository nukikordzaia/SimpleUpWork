package repository.application;

import model.application.Application;
import utils.ListResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomApplicationQueriesImpl implements CustomApplicationQueries {

	@PersistenceContext
	EntityManager em;

	@Override
	public ListResult<Application> filterByAttributes(String name, Long salary, Boolean active, Date createTime,
													  Date startDate, Date endDate, int limit, int page) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null) {
			sql.append("AND a.name = :name ");
			params.put("name", name);
		}

		if (salary != null) {
			sql.append("AND a.salary = :salary ");
			params.put("salary", salary);
		}

		if (active != null) {
			sql.append("AND a.active = :active ");
			params.put("active", active);
		}

		if (createTime != null) {
			sql.append("AND a.createTime >= :createTime ");
			params.put("createTime", createTime);
		}

		if (startDate != null) {
			sql.append("AND a.startDate = :startDate ");
			params.put("startDate", startDate);
		}

		if (endDate != null) {
			sql.append("AND a.endDate >= :endDate ");
			params.put("endDate", endDate);
		}

		TypedQuery<Application> query = em.createQuery("SELECT a from Application a WHERE 1=1 " + sql, Application.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(a.id) FROM Application a WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		query.setFirstResult(limit * page);
		query.setMaxResults(limit);
		List<Application> applications = query.getResultList();

		return new ListResult<>(applications, count.getSingleResult().intValue());
	}
}
