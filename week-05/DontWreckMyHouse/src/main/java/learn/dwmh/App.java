package learn.dwmh;

import learn.dwmh.data.HostFileRepository;
import learn.dwmh.ui.ConsoleIO;
import learn.dwmh.ui.Controller;
import learn.dwmh.ui.View;
import learn.dwmh.domain.HostService;

public class App {
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        HostFileRepository hostFileRepository = new HostFileRepository("./data/hosts.csv");

        HostService hostService = new HostService(hostFileRepository);

        Controller controller = new Controller(view, hostService );
        controller.run();
    }
}
