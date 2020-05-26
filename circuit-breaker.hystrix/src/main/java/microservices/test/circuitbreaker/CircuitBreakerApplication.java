package microservices.test.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class CircuitBreakerApplication {

	private RobotService robotService;

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerApplication.class, args);
	}

	@Autowired
	public void setRobotService(RobotService robotService) {
		this.robotService = robotService;
	}

	@GetMapping("/robot")
	public String getRobot(){
		return this.robotService.getRobot();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder buider) {
		return buider.build();
	}

}
