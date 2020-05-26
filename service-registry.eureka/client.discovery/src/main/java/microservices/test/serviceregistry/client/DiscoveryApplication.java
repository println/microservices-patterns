package microservices.test.serviceregistry.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class DiscoveryApplication {

    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @Autowired
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/discovery/{serviceName}")
    public List<ServiceInstance> servicesByName(@PathVariable String serviceName){
        return this.discoveryClient.getInstances(serviceName);
    }


}
