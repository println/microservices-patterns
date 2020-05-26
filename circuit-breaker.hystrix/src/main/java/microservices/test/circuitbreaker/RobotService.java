package microservices.test.circuitbreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RobotService {

    private RestTemplate restTemplate;

    public RobotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliableRobot")
    public String getRobot() {
        URI uri = URI.create("http://localhost:8080/robot");
        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliableRobot() {
        return "Robot is shutdown!";
    }

}
