package com.simpleupwork.repository.sysparam;

import com.simpleupwork.model.sysparams.SystemParameter;
import com.simpleupwork.utils.ListResult;

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
	public ListResult<SystemParameter> filterByAttributes(String code, String description, String value, int limit, int page) {
		StringBuilder sql = new StringBuilder();
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

		TypedQuery<SystemParameter> query = em.createQuery("SELECT a from SystemParameter a WHERE 1=1 " + sql, SystemParameter.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(a.id) FROM SystemParameter a WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		query.setFirstResult(limit * page);
		query.setMaxResults(limit);
		List<SystemParameter> sysParams = query.getResultList();

		return new ListResult<>(sysParams, count.getSingleResult().intValue());
	}
}