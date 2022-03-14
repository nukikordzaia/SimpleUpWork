package repository.application.offer;

import model.application.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long>, CustomOfferQueries {
}
