package learn.solar;

import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelService;
import learn.solar.ui.Controller;
import learn.solar.ui.View;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Solar Farm");
        System.out.println("=".repeat("Welcome to Solar Farm".length()));

        View view = new View();
        PanelFileRepository fileRepo = new PanelFileRepository("./data/panels.txt");
        PanelService service = new PanelService(fileRepo);

        Controller controller = new Controller(view, service );
        controller.run();

    }
}
