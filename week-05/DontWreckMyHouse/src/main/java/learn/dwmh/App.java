package learn.dwmh;

import learn.dwmh.data.HostFileRepository;
import learn.dwmh.data.ReservationFileRepository;
import learn.dwmh.domain.ReservationService;
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

        ReservationFileRepository reservationFileRepository = new ReservationFileRepository("./data/reservations");
        ReservationService reservationService = new ReservationService(reservationFileRepository, hostFileRepository);

        Controller controller = new Controller(view, hostService,  reservationService);
        controller.run();
    }
}
