package repository.user.usergroup;

import model.user.UserGroup;
import utils.ListResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomUserGroupQueriesImpl implements CustomUserGroupQueries {

	@PersistenceContext
	EntityManager em;

	@Override
	public ListResult<UserGroup> filterByAttributes(String name, String permission, Boolean active, int limit, int page) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null) {
			sql.append("AND a.name = :name ");
			params.put("name", name);
		}

		if (permission != null) {
			sql.append("AND a.permission = :permission ");
			params.put("permission", permission);
		}

		if (active != null) {
			sql.append("AND a.active = :active ");
			params.put("active", active);
		}

		TypedQuery<UserGroup> query = em.createQuery("SELECT a from UserGroup a WHERE 1=1 " + sql, UserGroup.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(a.id) FROM UserGroup a WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		query.setFirstResult(limit * page);
		query.setMaxResults(limit);
		List<UserGroup> userGroups = query.getResultList();

		return new ListResult<>(userGroups, count.getSingleResult().intValue());
	}
}
