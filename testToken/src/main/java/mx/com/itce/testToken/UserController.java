package mx.com.itce.testToken;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/users")
	public @ResponseBody String getUser()
	{
		return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
		           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	}
}
