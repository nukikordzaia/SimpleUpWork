package com.simpleupwork.repository.application.offer;

import com.simpleupwork.model.application.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long>, CustomOfferQueries {
}
