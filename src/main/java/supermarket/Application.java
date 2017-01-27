package supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import supermarket.orders.control.OrderRepository;
import supermarket.orders.entity.Order;
import supermarket.orders.entity.OrderLine;
import supermarket.prices.GiftProducts;
import supermarket.prices.OfferPrice;
import supermarket.prices.Price;
import supermarket.prices.UnitPrice;
import supermarket.prices.Price.PriceBuilder;
import supermarket.products.control.ProductRepository;
import supermarket.products.entity.Product;
import supermarket.products.entity.ProductCategoryType;
import supermarket.products.entity.Product.ProductBuilder;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private ProductRepository pr;

    @Autowired
    private OrderRepository or;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
	return (args) -> {

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
	
		 ProductCategoryType category = new ProductCategoryType(null, "Food");
		 ProductCategoryType category2= new ProductCategoryType(null, "Beauty");
		 ProductCategoryType category3 = new ProductCategoryType(null, "College");
		//Created some Products with Prices
		ProductBuilder pb = Product.builder();
		
		Product myProduct =  pb .name("Macarrones")
                			.price(myPrice)
                			.offerName("Sin Oferta")
                			.build();

                Product myProduct2 = pb	.name("Desodorantes")
                			.price(myPrice2)
                			.offerName("6ud por 25€")
                			.build();

                Product myProduct3 = pb	.name("Macetas")
                			.price(myPrice3)
                			.offerName("Regalo 1ud por cada 3 compradas")
                			.build();
		
                
                myProduct.setCategory(category);
                myProduct2.setCategory(category2);
                myProduct3.setCategory(category3);
                
		//Added some products into BBDD
		pr.save(myProduct);
		pr.save(myProduct2);
		pr.save(myProduct3);
		
		ArrayList<OrderLine> lines = new ArrayList<OrderLine>();
		
		//Creamos el Order con una lista de lineas de pedido vacia.
		Order order = new Order("1","JoseDavid");
		
		//Creamos una linea de pedido, para nuestro pedido "order"
		OrderLine ol = new OrderLine(null,3,new BigDecimal(24),myProduct,order);
		OrderLine ol1 = new OrderLine(null,5,new BigDecimal(25),myProduct2,order);		
			
//		//Añadimos la linea de pedido, a la lista de lineas de pedido.
		lines.add(ol);
		lines.add(ol1);
		
//		//Asignamos la lista de lineas de pedido al pedido.
		order.setOrderLines(lines);
//		
//		//Actualizamos el pedido con la nueva lista de lines de pedido.
		or.save(order);
		
		List<Object> listPersonal;
		
		
		listPersonal = pr.personalQuery();
		
		
		    Iterator<Object> it = listPersonal.iterator();
		    while(it.hasNext()){
			log.debug(it.next().toString());
		    }
		
		
		
		
	};
	
    }

}
