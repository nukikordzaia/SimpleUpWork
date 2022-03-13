package repository.application.offer;

import model.application.Offer;

import java.util.Date;
import java.util.List;

public interface CustomOfferQueries {
	List<Offer> filterByAttributes(String name, Date createTime, Boolean active);
}
