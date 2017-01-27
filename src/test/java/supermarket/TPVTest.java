package supermarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import supermarket.orders.entity.Order;
import supermarket.orders.entity.OrderLine;
import supermarket.prices.GiftProducts;
import supermarket.prices.OfferPrice;
import supermarket.prices.Price;
import supermarket.prices.UnitPrice;
import supermarket.prices.Price.PriceBuilder;
import supermarket.products.entity.Product;
import supermarket.products.entity.Product.ProductBuilder;

public class TPVTest {

    private static Order order;

    private static Order orderEmpty;

    private static TPV tpv = new TPV();

    @BeforeClass
    public static void init() {
	// log.debug("Inicializando Test");
	PriceBuilder priceb = Price.builder();
	//@formatter:off
	Price myPrice = priceb	.currencyRate("EUR")
							.unitPrice(new UnitPrice(new BigDecimal(8)))
							.offerPrice(null)
							.giftProduct(null)
							.build();
	
	Price myPrice2 = priceb	.currencyRate("EUR")
							.unitPrice(new UnitPrice(new BigDecimal(5)))
							.offerPrice(new OfferPrice(6, new BigDecimal(25)))
							.giftProduct(null)
							.build();
	

	Price myPrice3 = priceb	.currencyRate("EUR")
							.unitPrice(new UnitPrice(new BigDecimal(8)))
							.offerPrice(null)
							.giftProduct(new GiftProducts(3, 1))
							.build();	
	
	//Created some Products with Prices
	ProductBuilder pb = Product.builder();
	Product myProduct =  pb .name("Cuchilla de afeitar")
				.price(myPrice)
				.offerName("Sin Oferta")
				.build();
	
	Product myProduct2 = pb	.name("Cuaderno")
				.price(myPrice2)
				.offerName("6ud por 25€")
				.build();
	
	Product myProduct3 = pb	.name("Estuches")
				.price(myPrice3)
				.offerName("Regalo 1ud por cada 3 compradas")
				.build();
	
	ArrayList<OrderLine> lines = new ArrayList<OrderLine>();
	
	order = new Order(lines);
	
	OrderLine lo1 = new OrderLine(null,4,BigDecimal.ZERO,myProduct,order);
	OrderLine lo2 = new OrderLine(null,27,BigDecimal.ZERO,myProduct2,order);
	OrderLine lo3 = new OrderLine(null,7,BigDecimal.ZERO,myProduct3,order);
	
	
	order.getOrderLines().add(lo1);
	order.getOrderLines().add(lo2);
	order.getOrderLines().add(lo3);
	
	
	
	orderEmpty = new Order(null);

	//@formatter:on
    }

    /*
     * @AfterClass public static void tearDown(){ log.debug(
     * "liberando recursos del Test"); }
     * 
     * @Before public void beforeTest(){ log.debug("ejecutando before"); }
     * 
     * @After public void afterTest(){ log.debug("ejecutando after"); }
     * 
     * @Test public void testLog() { log.debug("Ejecutando test testlog"); }
     * 
     */

    @Test
    public void calculateTotalPriceMixedProductList() {
	assertEquals(new BigDecimal(187), tpv.calculateTotalPrice(order));
    }

    @Test
    public void calculateTotalPriceEmptyProductList() {
	assertEquals(BigDecimal.ZERO, tpv.calculateTotalPrice(orderEmpty));
    }

    @Test
    public void calculateTotalPriceNullProductList() {
	assertEquals(BigDecimal.ZERO, tpv.calculateTotalPrice(null));
    }

}
