package co.com.gsdd.client.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

import co.com.gsdd.client.services.model.Level;

@RestController
@RequestMapping("levels")
public class LevelController extends AbstractBaseController {

	@Autowired
	public LevelController(EurekaClient client, RestTemplate restTemplate) {
		super(client, restTemplate, Level.class);
	}

	@Override
	public String getApplicationName() {
		return "dw2-svc";
	}

	@Override
	public String getControllerRoute() {
		return "/v1/levels";
	}

	@Override
	public String getAllMsg() {
		return "Our levels are: ";
	}

	@Override
	public String getFirstMsg() {
		return "Our first level is: ";
	}

	@Override
	public String fallbackGetAll() {
		return "Can not render our levels in this moment";
	}

	@Override
	public String fallbackGetFirst() {
		return "Can not render our first level in this moment";
	}

}
