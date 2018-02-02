package mx.com.itce.testToken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class TestTokenApplication {
	
	@RequestMapping("/")
	String hello()
	{
		return "Hello world!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TestTokenApplication.class, args);
	}
}
