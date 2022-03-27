package com.simpleupwork.repository.application.offer;

import com.simpleupwork.model.application.Offer;
import com.simpleupwork.utils.ListResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOfferQueriesImpl implements CustomOfferQueries{

	@PersistenceContext
	EntityManager em;

	@Override
	public ListResult<Offer> filterByAttributes(String name, Date createTime, Boolean active, int limit, int page) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null) {
			sql.append("AND a.name = :name ");
			params.put("name", name);
		}

		if (createTime != null) {
			sql.append("AND a.createTime >= :createTime ");
			params.put("createTime", createTime);
		}

		if (active != null) {
			sql.append("AND a.active = :active ");
			params.put("active", active);
		}

		TypedQuery<Offer> query = em.createQuery("SELECT a from Offer a WHERE 1=1 " + sql, Offer.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(a.id) FROM Offer a WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		query.setFirstResult(limit * page);
		query.setMaxResults(limit);
		List<Offer> offers = query.getResultList();

		return new ListResult<>(offers, count.getSingleResult().intValue());
	}
}
