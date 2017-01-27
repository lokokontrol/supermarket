package supermarket.orders.boundary;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import supermarket.TPV;
import supermarket.orders.control.OrderLineRepository;
import supermarket.orders.control.OrderRepository;
import supermarket.orders.entity.Order;
import supermarket.orders.entity.OrderLine;
import supermarket.products.control.ProductRepository;
import supermarket.products.entity.Product;

@Controller
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderlineRepository;

    /**
     * 
     * /orders/order-13/add/product-24/qty-25
     * 
     * @param id
     * @param productId
     * @param qty
     */
    /*
     * @RequestMapping(value = "/order-{id}/add/product-{productId}", method =
     * RequestMethod.POST) public void addProductToOrder(@PathVariable("id")
     * Long id, @PathVariable("productId") Long productId,
     * 
     * @RequestParam(value = "qty") int qty) { }
     * 
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String orderList(Model model) {
	log.debug("Metodo del orderForm mapeado");
	model.addAttribute("orders", orderRepository.findAll());
	return "orders/listOrders";
    }

    @RequestMapping(value = "/order-new/add/", method = RequestMethod.GET)
    public String orderForm(Model model) {
	log.debug("Metodo del orderForm mapeado");
	model.addAttribute("orderDTO", new OrderDTO());
	return "orders/order";
    }

    @RequestMapping(value = "/order-new/add/", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute @Valid OrderDTO orderDTO, BindingResult errors, Model model) {
	log.debug("Metodo del createOrder mapeado");

	if (errors.hasErrors()) {
	    model.addAttribute("orderDTO", orderDTO);

	    if (errors.hasFieldErrors("idBill"))
		model.addAttribute("errorIdBill", true);

	    if (errors.hasFieldErrors("customer"))
		model.addAttribute("errorCustomer", true);

	    return "/orders/order";
	} else {
	    Order order = new Order();
	    order.setIdBill(orderDTO.getIdBill());
	    order.setCustomer(orderDTO.getCustomer());
	    orderRepository.save(order);
	    return "redirect:/orders/order/" + order.getId();
	}
    }

    @RequestMapping(value = "/order/{orderId}/addProduct", method = RequestMethod.GET)
    public String listProducts(Model model, @PathVariable("orderId") Long id) {
	log.debug("Metodo del controller mapeado");
	model.addAttribute("products", productRepository.findAll());
	model.addAttribute("order", orderRepository.findOne(id));
	return "products/listProducts";
    }

    @RequestMapping(value = "/order/{orderId}/addProduct/{productId}", method = RequestMethod.GET)
    public String detailProduct(Model model, @PathVariable("orderId") Long idOrder,
	    @PathVariable("productId") Long idProduct) {
	log.debug("Metodo del detailsProduct");
	OrderLine orderline = new OrderLine(null, 0, BigDecimal.ZERO, productRepository.findOne(idProduct),
		orderRepository.findOne(idOrder));
	model.addAttribute("order", orderRepository.findOne(idOrder));
	model.addAttribute("orderLine", orderline);
	return "products/detailsProduct";
    }

    @RequestMapping(value = "/order/{orderId}/addProduct/{productId}", method = RequestMethod.POST)
    public String createOrderLine(@ModelAttribute OrderLine orderline, @PathVariable("orderId") Long idOrder,
	    @PathVariable("productId") Long idProduct) {
	log.debug("Metodo del createOrderLine");
	log.debug(orderline.toString());
	Order order = orderRepository.findOne(idOrder);
	Product product = productRepository.findOne(idProduct);
	orderline.setOrder(order);
	orderline.setProduct(product);
	orderlineRepository.save(orderline);

	return "redirect:/orders/order/" + idOrder;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public String listOrderLines(Model model, @PathVariable("orderId") Long id) {
	log.debug("Metodo del OrderLines mapeado");
	model.addAttribute("order", orderRepository.findOne(id));
	return "orderLines/listOrderLines";
    }

    @RequestMapping(value = "/order/{orderId}/tpv", method = RequestMethod.GET)
    public String goToPay(Model model, @PathVariable("orderId") Long id) {
	log.debug("Metodo del controller mapeado");
	Order order = orderRepository.findOne(id);
	TPV tpv = new TPV();
	model.addAttribute("totalPrice", tpv.calculateTotalPrice(order));
	model.addAttribute("order", order);
	return "tpv/supermarket";
    }

}
