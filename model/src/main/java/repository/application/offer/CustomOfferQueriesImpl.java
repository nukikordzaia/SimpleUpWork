package repository.application.offer;

import model.application.Offer;
import model.user.UserGroup;

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
	public List<Offer> filterByAttributes(String name, Date createTime, Boolean active) {
		StringBuilder sql = new StringBuilder("SELECT a from Offer a WHERE 1=1 ");
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

		TypedQuery<Offer> query = em.createQuery(sql.toString(), Offer.class);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
}
