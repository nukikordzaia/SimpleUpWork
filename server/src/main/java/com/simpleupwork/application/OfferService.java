package com.simpleupwork.application;

import com.simpleupwork.model.application.Offer;
import com.simpleupwork.repository.application.offer.OfferRepository;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OfferService {

	@Autowired
	OfferRepository offerRepository;

	public Optional<Offer> findById(Long id) {
		return offerRepository.findById(id);
	}

	public ListResult<Offer> filterByAttributes(String name, Date createTime, Boolean active, int limit, int page) {
		return offerRepository.filterByAttributes(name, createTime, active, limit, page);
	}

	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}

	public void delete(Long id) {
		offerRepository.deleteById(id);
	}
}
