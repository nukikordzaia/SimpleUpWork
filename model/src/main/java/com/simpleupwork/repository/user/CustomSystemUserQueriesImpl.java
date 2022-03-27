package com.simpleupwork.repository.user;

import com.simpleupwork.model.user.SystemUser;
import com.simpleupwork.utils.ListResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSystemUserQueriesImpl implements CustomSystemUserQueries {

	@PersistenceContext
	EntityManager em;

	@Override
	public ListResult<SystemUser> filterByAttributes(String firstName, String lastName, String username, String title,
													 String email, String userGroups, String skills, Boolean active, int limit, int page) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		if (firstName != null) {
			sql.append("AND a.firstName = :firstName ");
			params.put("firstName", firstName);
		}

		if (lastName != null) {
			sql.append("AND a.lastName = :lastName ");
			params.put("lastName", lastName);
		}

		if (username != null) {
			sql.append("AND a.username = :username ");
			params.put("username", username);
		}

		if (title != null) {
			sql.append("AND a.title LIKE :title ");
			params.put("title", title);
		}

		if (email != null) {
			sql.append("AND a.email = :email ");
			params.put("email", email);
		}

		if (userGroups != null) {
			sql.append("AND b.name = :userGroups ");
			params.put("userGroups", userGroups);
		}

		if (skills != null) {
			sql.append("AND c.skills = :skills ");
			params.put("skills", skills);
		}

		if (active != null) {
			sql.append("AND a.active = :active ");
			params.put("active", active);
		}

		TypedQuery<SystemUser> query = em.createQuery("SELECT DISTINCT a from SystemUser a " +
			"LEFT JOIN a.groups b " + "AND LEFT JOIN a.skills c  WHERE 1=1 " + sql, SystemUser.class);
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(DISTINCT a.id) from SystemUser a LEFT JOIN a.groups b " +
			"AND LEFT JOIN a.skills c  WHERE 1=1 " + sql, Long.class);

		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			count.setParameter(key, params.get(key));
		}
		query.setFirstResult(limit * page);
		query.setMaxResults(limit);
		List<SystemUser> users = query.getResultList();

		return new ListResult<>(users, count.getSingleResult().intValue());
	}
}
