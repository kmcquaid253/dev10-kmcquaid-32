package learn.solarfarm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Solar Farm!");
    }
}
