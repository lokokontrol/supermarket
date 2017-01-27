package supermarket.prices;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class GiftProducts {
    @Column(nullable = true)
    private final int quantityGif;
    @Column(nullable = true)
    private final int gift;

    public GiftProducts() {
	this.quantityGif = 0;
	this.gift = 0;
    }

    public GiftProducts(int quantityGif, int gift) {
	this.quantityGif = quantityGif;
	this.gift = gift;
    }

}
