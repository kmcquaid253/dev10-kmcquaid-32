package learn.concepts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 1 @SpringBootApplication annotation wraps a bunch of other Spring annotations:
                        // @Configuration (makes a class a Spring configuration source),
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // 2
        // SpringApplication.run is the Spring Boot magic. It automatically configures what it can,
        // scans our packages for dependencies, when a controller is found it decides how requests
        // are handled, and finally starts an internal Tomcat server and attaches our Spring MVC
        // application.


    }
}