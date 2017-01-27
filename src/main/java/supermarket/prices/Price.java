package supermarket.prices;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
@AllArgsConstructor
public class Price {
    private String currencyRate;
    @Embedded
    @NotNull
    private UnitPrice unitPrice;
    @Embedded
    private OfferPrice offerPrice;
    @Embedded
    private GiftProducts giftProduct;

    public Price() {

    }
}
