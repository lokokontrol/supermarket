package supermarket.products.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import supermarket.products.control.ProductRepository;
import supermarket.products.entity.Product;

@Controller
@Slf4j
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductSearch productSearch;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public String listProducts(Model model) {
	log.debug("Metodo del detail mapeado");
	model.addAttribute("products", productRepository.findAll());
	return "products/listProductsWithOutOrder";
    }

    @RequestMapping(value = "/products/{idProduct}", method = RequestMethod.GET)
    public String detailProduct(Model model, @PathVariable("idProduct") Long id) {
	log.debug("Metodo del detail mapeado");
	model.addAttribute("product", productRepository.findOne(id));
	return "products/detailsProductWithOutOrder";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String HibernateSearch(String search, Model model) {
	log.debug(search);
	log.debug("metodo search");
	List<Product> productList = productSearch.search(search);
	model.addAttribute("products", productList);

	return "products/listProductsWithOutOrder";

    }

}
