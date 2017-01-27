package supermarket.product;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import supermarket.Application;
import supermarket.prices.GiftProducts;
import supermarket.prices.OfferPrice;
import supermarket.prices.Price;
import supermarket.prices.UnitPrice;
import supermarket.prices.Price.PriceBuilder;
import supermarket.products.control.ProductRepository;
import supermarket.products.entity.Product;
import supermarket.products.entity.Product.ProductBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class ProductITTest {

    @Autowired
    ProductRepository productRepo;

    Product myProduct;
    Product myProduct2;
    Product myProduct3;

    @Before
    public void setup() {

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
	myProduct =  pb .name("Cuchilla de afeitar")
				.price(myPrice)
				.offerName("Sin Oferta")
				.build();

	myProduct2 = pb	.name("Cuaderno")
				.price(myPrice2)
				.offerName("6ud por 25€")
				.build();

	myProduct3 = pb	.name("Estuches")
				.price(myPrice3)
				.offerName("Regalo 1ud por cada 3 compradas")
				.build();
	
	myProduct = productRepo.save(myProduct);
	myProduct2 = productRepo.save(myProduct2);
	myProduct3 = productRepo.save(myProduct3);

		
    }
    
    @After
    public void erase(){
	productRepo.deleteAll();
    }
    
   @Test
    public void mySimpleTest(){
	Product expected, actual;
	expected = myProduct;
	actual = productRepo.findOne(myProduct.getId());
	assertEquals(expected, actual);
    }
     
    @Test
    public void mySimpleTest2(){
	boolean actual;
	actual = productRepo.exists(myProduct.getId());
	assertEquals(true, actual);
    }
    
    
    @Test
    public void mySimpleTest3(){
	int size = 3;
	List<Product> lp = (List<Product>)productRepo.findAll();
	
	Iterator<Product> it = lp.iterator();
	while (it.hasNext()){
	    System.out.println(it.next());
	}
	
	
	assertEquals(size, productRepo.count());
	
    }
    
 
}
