package com.simpleupwork.repository.application.offer;

import com.simpleupwork.model.application.Offer;
import com.simpleupwork.utils.ListResult;

import java.util.Date;

public interface CustomOfferQueries {
	ListResult<Offer> filterByAttributes(String name, Date createTime, Boolean active, int limit, int page);
}
