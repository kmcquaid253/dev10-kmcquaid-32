package learn.dwmh;

import learn.dwmh.data.GuestFileRepository;
import learn.dwmh.data.HostFileRepository;
import learn.dwmh.data.ReservationFileRepository;
import learn.dwmh.domain.ReservationService;
import learn.dwmh.ui.ConsoleIO;
import learn.dwmh.ui.Controller;
import learn.dwmh.ui.View;
import learn.dwmh.domain.HostService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:data.properties")
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        Controller controller = context.getBean(Controller.class);

        controller.run();


    }
}
