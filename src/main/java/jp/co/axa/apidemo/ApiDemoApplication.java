package jp.co.axa.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Declare this class as a Spring Boot application and enable Swagger 2
@SpringBootApplication
@EnableSwagger2
public class ApiDemoApplication {

    // Main method to start the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }

}
