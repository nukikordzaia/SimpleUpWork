package com.simpleupwork.application;

import com.simpleupwork.model.application.Offer;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/offer")
public class OfferController {

	@Autowired
	OfferService offerService;

	@GetMapping("/all")
	public ResponseEntity<ListResult<Offer>> filterByAttributes(@RequestParam(required = false) String name,
																@RequestParam(required = false) Boolean active,
																@RequestParam(required = false) Date createTime,
																@RequestParam(required = false) int limit,
																@RequestParam(required = false) int page) {
		return ResponseEntity.ok(offerService.filterByAttributes(name, createTime, active, limit, page));
	}

	@DeleteMapping(path = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		offerService.delete(id);
	}

	@PostMapping("/create")
	public void create(@RequestBody Offer offer) {
		Offer newOffer = new Offer();
		newOffer.setName(offer.getName());
		newOffer.setCreateTime(new Date());
		newOffer.setActive(true);
		offerService.save(newOffer);
	}

	@PutMapping("/update")
	public void update(@RequestBody Offer offer) {
		offerService.findById(offer.getId())
			.map(offerInDB -> {
				offerInDB.setName(offer.getName());
				offerInDB.setActive(offer.isActive());
				offerInDB.setCreateTime(offer.getCreateTime());
				return offerService.save(offerInDB);
			});
	}

	@PutMapping("/update/active")
	public void updateActivity(@RequestBody Offer offer) {
		Optional<Offer> offerInDB = offerService.findById(offer.getId());
		offerInDB.get().setActive(!offer.isActive());
	}
}
