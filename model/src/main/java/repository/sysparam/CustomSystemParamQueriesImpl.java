package repository.sysparam;

import model.sysparams.SystemParameter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSystemParamQueriesImpl implements CustomSystemParamQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SystemParameter> filterByAttributes(String code, String description, String value) {
		StringBuilder sql = new StringBuilder("SELECT a from SystemParameter a WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (code != null) {
			sql.append("AND a.code = :code ");
			params.put("code", code);
		}

		if (description != null) {
			sql.append("AND a.description = :description ");
			params.put("description", description);
		}

		if (value != null) {
			sql.append("AND a.value = :value ");
			params.put("value", value);
		}

		TypedQuery<SystemParameter> query = em.createQuery(sql.toString(), SystemParameter.class);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
}