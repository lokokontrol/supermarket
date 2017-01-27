package supermarket.prices;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@RequiredArgsConstructor
public class UnitPrice {

    private BigDecimal priceUnit;

    public UnitPrice(BigDecimal pPrice) {
	this.priceUnit = pPrice;
    }

}
