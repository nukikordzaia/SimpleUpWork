package repository.application.offer;

import model.application.Offer;
import utils.ListResult;

import java.util.Date;

public interface CustomOfferQueries {
	ListResult<Offer> filterByAttributes(String name, Date createTime, Boolean active, int limit, int page);
}
