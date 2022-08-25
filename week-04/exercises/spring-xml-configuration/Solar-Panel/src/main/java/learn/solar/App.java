package learn.solar;

import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelService;
import learn.solar.ui.Controller;
import learn.solar.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan // 2. Tells Spring to scan all classes in this package or its children. <- Annotation based configuration
@PropertySource("classpath:data.properties")//@PropertySource annotation that reads data from an external properties file. <- ALLOWS data to be something that lives outside of our code -- something that our code responds to but isn't part of the code
                                            //The annotation's argument: "classpath:data.properties", tells Spring to scan the classpath to find a file named data.properties
                                            //The resources directory is part of the classpath, so we add the file there.
public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Solar Farm");
        System.out.println("=".repeat("Welcome to Solar Farm".length()));

// **************************************************** Regular Way *******************************************************************************
//        View view = new View();
//        PanelFileRepository fileRepo = new PanelFileRepository("./data/panels.txt");
//        PanelService service = new PanelService(fileRepo);
//
//        Controller controller = new Controller(view, service );
//        controller.run();










//************************************************** XML Configuration Way ************************************************************************
//        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");
//
//        Controller controller = context.getBean(Controller.class);
//        // Run the app!
//        controller.run();










//************************************************** Annotation- Based Configuration **************************************************************

        /*
            - AnnotationConfigApplicationContext is a second concrete implementation of ApplicationContext.

            - It has several constructors.

            - Below, we turn our App class into Spring DI annotation configuration

            - App is the starting point for configuring dependencies.

            -App is annotated with @ComponentScan. This tells Spring to scan through
                compiled classes in the current package and all child packages, looking for dependencies.
         */

        // 1. We pass the App.class, this class, as a constructor argument.
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        // 3. The context works the same as the XML context.
        Controller controller = context.getBean(Controller.class);
        // Run the app!
        controller.run();
    }
}
