package co.com.gsdd.client.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

import co.com.gsdd.client.services.model.User;

@RestController
@RequestMapping("users")
public class UserController extends AbstractBaseController {

	@Autowired
	public UserController(EurekaClient client, RestTemplate restTemplate) {
		super(client, restTemplate, User.class);
	}

	@Override
	public String getApplicationName() {
		return "fx-user-app";
	}

	@Override
	public String getControllerRoute() {
		return "/users";
	}

	@Override
	public String getAllMsg() {
		return "Our users are: ";
	}

	@Override
	public String getFirstMsg() {
		return "Our first user is: ";
	}

	@Override
	public String fallbackGetAll() {
		return "Can not render our users in this moment";
	}

	@Override
	public String fallbackGetFirst() {
		return "Can not render our first user in this moment";
	}

}
