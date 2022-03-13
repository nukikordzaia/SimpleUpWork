package repository.user.usergroup;

import model.sysparams.SystemParameter;
import model.user.UserGroup;

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
	public List<UserGroup> filterByAttributes(String name, String permission, Boolean active) {
		StringBuilder sql = new StringBuilder("SELECT a from UserGroup a WHERE 1=1 ");
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

		TypedQuery<UserGroup> query = em.createQuery(sql.toString(), UserGroup.class);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
}
