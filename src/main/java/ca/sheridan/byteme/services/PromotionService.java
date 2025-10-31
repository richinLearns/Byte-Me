package ca.sheridan.byteme.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridan.byteme.beans.Promotion;
import ca.sheridan.byteme.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public List<Promotion> getActivePromotionsForToday() {
        LocalDate today = LocalDate.now();
        return promotionRepository
            .findByActiveTrueAndStartsAtLessThanEqualAndEndsAtGreaterThanEqualOrderByEndsAtAsc(today, today);
    }

    // helper for seeding outside prod
    public void saveAll(List<Promotion> promos) {
        promotionRepository.saveAll(promos);
    }

    public long count() {
        return promotionRepository.count();
    }
}
