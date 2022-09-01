package learn.foraging.ui;

import learn.foraging.data.DataException;
import learn.foraging.domain.ForageService;
import learn.foraging.domain.ForagerService;
import learn.foraging.domain.ItemService;
import learn.foraging.domain.Result;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class  Controller {


    private final ForagerService foragerService;
    private final ForageService forageService;
    private final ItemService itemService;
    private final View view;

    @Autowired // indicates both a view and service should be injected.
    public Controller(ForagerService foragerService, ForageService forageService, ItemService itemService, View view) {
        this.foragerService = foragerService;
        this.forageService = forageService;
        this.itemService = itemService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Sustainable Foraging");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_FORAGES_BY_DATE:
                    viewByDate();
                    break;
                case VIEW_ITEMS:
                    viewItems();
                    break;
                case ADD_FORAGE:
                    addForage();
                    break;
                case ADD_FORAGER:
                    addForager();
                    break;
                case ADD_ITEM:
                    addItem();
                    break;
                case REPORT_KG_PER_ITEM:
                    kilogramsReport();
                    break;
                case REPORT_CATEGORY_VALUE:
                    categoryValueReport();
                    break;
                case VIEW_FORAGERS:
                    viewForagers();
                    break;
                case GENERATE:
                    generate();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void categoryValueReport() {
        view.displayHeader(MainMenuOption.REPORT_CATEGORY_VALUE.getMessage());
        LocalDate theeDay = view.getForageDate();

        Map<Category, BigDecimal> report = forageService.createValueForCategory(theeDay);
        view.displayCat(report);
    }

    private void kilogramsReport() {
        view.displayHeader(MainMenuOption.REPORT_KG_PER_ITEM.getMessage());
        //Ask user for report data
        LocalDate date = view.getForageDate();
        Map<String,Double> report = forageService.createKgPerItem(date);
        view.displayKgReport(report);
    }

    // top level menu
    private void viewByDate() {
        LocalDate date = view.getForageDate();
        List<Forage> forages = forageService.findByDate(date);
        view.displayForages(forages);
    }

    private void viewItems() {
        view.displayHeader(MainMenuOption.VIEW_ITEMS.getMessage());
        Category category = view.getItemCategory();
        List<Item> items = itemService.findByCategory(category);
        view.displayHeader("Items");
        view.displayItems(items);
        view.enterToContinue();
    }

    private void viewForagers() {
        view.displayHeader(MainMenuOption.VIEW_FORAGERS.getMessage());
        String forager = view.getForagerNamePrefix();
        List<Forager> foragersName = foragerService.findByLastName(forager);

        view.displayHeader("Foragers");

        Forager fori = view.chooseForager(foragersName);
        view.displayForagers(fori);
    }

    private void addForage() throws DataException {
        view.displayHeader(MainMenuOption.ADD_FORAGE.getMessage());
        Forager forager = getForager();
        if (forager == null) {
            return;
        }
        Item item = getItem();
        if (item == null) {
            return;
        }
        Forage forage = view.makeForage(forager, item);
        Result<Forage> result = forageService.add(forage);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Forage %s created.", result.getPayload().getId());
            view.displayStatus(true, successMessage);
        }
    }

    private void addForager() throws DataException {//////////////////////////////////////////////////////////////////////////////////////////
        view.displayHeader(MainMenuOption.ADD_FORAGER.getMessage());
        Forager partiallyHydrated = view.getNewForagerDetails();
        Result fullyHydrated = foragerService.add(partiallyHydrated);

        //Result<Forager> result = foragerService.add(partiallyHydrated);

        if (!fullyHydrated.isSuccess()) {
            view.displayStatus(false, fullyHydrated.getErrorMessages());
        } else {
            String successMessage = String.format("[SUCCESS]");
            view.displayStatus(true, successMessage);
        }
    }

    private void addItem() throws DataException {
        Item item = view.makeItem();
        Result<Item> result = itemService.add(item);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Item %s created.", result.getPayload().getId());
            view.displayStatus(true, successMessage);
        }
    }

    private void generate() throws DataException {
        GenerateRequest request = view.getGenerateRequest();
        if (request != null) {
            int count = forageService.generate(request.getStart(), request.getEnd(), request.getCount());
            view.displayStatus(true, String.format("%s forages generated.", count));
        }
    }

    // support methods
    private Forager getForager() {
        String lastNamePrefix = view.getForagerNamePrefix();
        List<Forager> foragers = foragerService.findByLastName(lastNamePrefix);
        return view.chooseForager(foragers);
    }

    private Item getItem() {
        Category category = view.getItemCategory();
        List<Item> items = itemService.findByCategory(category);
        return view.chooseItem(items);
    }
}
