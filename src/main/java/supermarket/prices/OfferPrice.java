package supermarket.prices;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class OfferPrice {
    @Column(nullable = true)
    private int quantity;
    @Column(nullable = true)
    private BigDecimal priceOffer;

    public OfferPrice() {

    }
}
