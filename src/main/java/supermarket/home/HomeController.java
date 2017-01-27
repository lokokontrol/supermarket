package supermarket.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
	log.debug("Metodo del index mapeado");
	return "index";
    }
}
