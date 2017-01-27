package supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import supermarket.orders.entity.Order;
import supermarket.orders.entity.OrderLine;

public class TPV {

    BigDecimal totalPrice;
    BigDecimal priceOfferAux;
    BigDecimal quantityAux;
    BigDecimal totalPriceAux;
    BigDecimal numOfferApplied;
    BigDecimal rest;
    BigDecimal quantityOfferaux;
    BigDecimal totalPriceAux2;

    public BigDecimal calculateTotalPrice(Order order) {

	BigDecimal totalPrice;

	totalPrice = BigDecimal.ZERO;
	totalPriceAux2 = BigDecimal.ZERO;
	priceOfferAux = BigDecimal.ZERO;
	quantityAux = BigDecimal.ZERO;
	totalPriceAux = BigDecimal.ZERO;
	if (order != null) {
	    if (order.getOrderLines() == null) {
		return new BigDecimal(0);
	    }
	    if (order.getOrderLines().size() == 0) {
		return new BigDecimal(0);
	    } else {
		for (OrderLine lo : order.getOrderLines()) {
		    // Product without Offer and giftProducts
		    if (lo.getProduct().getPrice().getOfferPrice() == null
			    && lo.getProduct().getPrice().getGiftProduct() == null) {
			lo.setPriceOrderLine(calculateUnitPrice(lo));
			totalPrice = totalPrice.add(calculateUnitPrice(lo));
			// Products with OfferPrice
		    } else if (lo.getProduct().getPrice().getGiftProduct() == null) {
			lo.setPriceOrderLine(calculateOfferPrice(lo));
			totalPrice = totalPrice.add(calculateOfferPrice(lo));
		    }
		    // Product with GiftProducts
		    else {
			lo.setPriceOrderLine(calculateGiftProduct(lo));
			totalPrice = totalPrice.add(calculateGiftProduct(lo));
		    }
		}

	    }
	    return totalPrice;
	} else {
	    return new BigDecimal(0);
	}

    }

    private BigDecimal calculateUnitPrice(OrderLine lo) {
	quantityAux = new BigDecimal(lo.getQuantity());
	priceOfferAux = lo.getProduct().getPrice().getUnitPrice().getPriceUnit();
	totalPriceAux = priceOfferAux.multiply(quantityAux);
	return totalPriceAux;
    }

    private BigDecimal calculateOfferPrice(OrderLine lo) {
	// Obtenemos la cantidad de productos comprados
	quantityAux = new BigDecimal(lo.getQuantity());
	// Obtenemos la cantidad de productos necesarios para aplicarse la
	// oferta
	quantityOfferaux = new BigDecimal(lo.getProduct().getPrice().getOfferPrice().getQuantity());
	// Obtenemos precio de los productos de oferta
	priceOfferAux = lo.getProduct().getPrice().getOfferPrice().getPriceOffer();
	// Obtenemos cociente, que es numero de veces que se aplica la oferta
	numOfferApplied = quantityAux.divide(quantityOfferaux, 0, RoundingMode.DOWN);
	// Obtenemos resto, que es el nº de ud q hay q aplicarles el precio
	// unitario
	rest = quantityAux.remainder(quantityOfferaux);
	totalPriceAux = numOfferApplied.multiply(priceOfferAux);
	totalPriceAux2 = rest.multiply(lo.getProduct().getPrice().getUnitPrice().getPriceUnit());

	return totalPriceAux.add(totalPriceAux2);
    }

    private BigDecimal calculateGiftProduct(OrderLine lo) {
	int quantityOfferAplied;
	int quantityGift;
	quantityOfferAplied = (int) lo.getQuantity() / lo.getProduct().getPrice().getGiftProduct().getQuantityGif();
	quantityGift = (int) (lo.getQuantity()
		- (quantityOfferAplied * lo.getProduct().getPrice().getGiftProduct().getGift()));
	totalPriceAux = lo.getProduct().getPrice().getUnitPrice().getPriceUnit().multiply(new BigDecimal(quantityGift));
	return totalPriceAux;
    }
}
