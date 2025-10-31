package ca.sheridan.byteme.beans;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Server-driven promotion shown to CUSTOMERS on the dashboard.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "promotions")
public class Promotion {

    @Id
    private String id;

    // Short headline (e.g., “20% off Giant Cookie”)
    @Indexed
    private String title;

    // One-liner that appears under the title
    private String blurb;

    // Optional badge like “NEW”, “LIMITED”, “TODAY ONLY”
    private String badge;

    // Optional promo code to display
    private String promoCode;

    // Date window (inclusive) for validity
    private LocalDate startsAt;
    private LocalDate endsAt;

    // Whether it should be shown
    @Builder.Default
    private boolean active = true;

    // Optional marketing image (absolute or /images/*)
    private String imageUrl;
}
