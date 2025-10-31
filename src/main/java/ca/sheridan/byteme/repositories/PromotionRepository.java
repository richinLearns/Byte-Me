package ca.sheridan.byteme.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.sheridan.byteme.beans.Promotion;

public interface PromotionRepository extends MongoRepository<Promotion, String> {

    // Only active promos that are in their date window, nearest expiry first
    List<Promotion> findByActiveTrueAndStartsAtLessThanEqualAndEndsAtGreaterThanEqualOrderByEndsAtAsc(
        LocalDate today1, LocalDate today2
    );
}
