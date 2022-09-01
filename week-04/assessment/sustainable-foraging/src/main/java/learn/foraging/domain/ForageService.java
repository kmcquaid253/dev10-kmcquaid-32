package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForageRepository;
import learn.foraging.data.ForagerRepository;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForageService {

    private final ForageRepository forageRepository;
    private final ForagerRepository foragerRepository;
    private final ItemRepository itemRepository;

    public ForageService(ForageRepository forageRepository, ForagerRepository foragerRepository, ItemRepository itemRepository) {
        this.forageRepository = forageRepository;
        this.foragerRepository = foragerRepository;
        this.itemRepository = itemRepository;
    }

    public List<Forage> findByDate(LocalDate date) {

        Map<String, Forager> foragerMap = foragerRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));
        Map<Integer, Item> itemMap = itemRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));

        List<Forage> result = forageRepository.findByDate(date);
        for (Forage forage : result) {
            forage.setForager(foragerMap.get(forage.getForager().getId()));
            forage.setItem(itemMap.get(forage.getItem().getId()));
        }

        return result;
    }

    public Result<Forage> add(Forage forage) throws DataException {
        Result<Forage> result = validate(forage);

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(forageRepository.add(forage));

        return result;
    }

    public int generate(LocalDate start, LocalDate end, int count) throws DataException {

        if (start == null || end == null || start.isAfter(end) || count <= 0) {
            return 0;
        }

        count = Math.min(count, 500);

        ArrayList<LocalDate> dates = new ArrayList<>();
        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        List<Item> items = itemRepository.findAll();
        List<Forager> foragers = foragerRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Forage forage = new Forage();
            forage.setDate(dates.get(random.nextInt(dates.size())));
            forage.setForager(foragers.get(random.nextInt(foragers.size())));
            forage.setItem(items.get(random.nextInt(items.size())));
            forage.setKilograms(random.nextDouble() * 5.0 + 0.1);
            forageRepository.add(forage);
        }

        return count;
    }

    private Result<Forage> validate(Forage forage) {

        Result<Forage> result = validateNulls(forage);


        if (!result.isSuccess()) {
            return result;
        }

        validateFields(forage, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(forage, result);

        return result;
    }

    private Result<Forage> validateNulls(Forage forage) {
        Result<Forage> result = new Result<>();

        if (forage == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        Forage existingForage = forageRepository.getForageByLocation(forage.getDate(), forage.getForager(), forage.getItem());///////////////////////////////////////////////////////////////////////

        if(existingForage != null){/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            result.addErrorMessage("Cannot add duplicate Foragers");
            return result;
        }

        if (forage.getDate() == null) {
            result.addErrorMessage("Forage date is required.");
        }

        if (forage.getForager() == null) {
            result.addErrorMessage("Forager is required.");
        }

        if (forage.getItem() == null) {
            result.addErrorMessage("Item is required.");
        }
        return result;
    }

    private void validateFields(Forage forage, Result<Forage> result) {
        // No future dates.
        if (forage.getDate().isAfter(LocalDate.now())) {
            result.addErrorMessage("Forage date cannot be in the future.");
        }

        if (forage.getKilograms() <= 0 || forage.getKilograms() > 250.0) {
            result.addErrorMessage("Kilograms must be a positive number less than 250.0");
        }
    }

    private void validateChildrenExist(Forage forage, Result<Forage> result) {

        if (forage.getForager().getId() == null
                || foragerRepository.findById(forage.getForager().getId()) == null) {
            result.addErrorMessage("Forager does not exist.");
        }

        if (itemRepository.findById(forage.getItem().getId()) == null) {
            result.addErrorMessage("Item does not exist.");
        }
    }

    public Map<String, Double> createKgPerItem(LocalDate date) {
        List<Forage> dailyForages = findByDate(date);
        Map<String, Double> report = dailyForages.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getItem().getName(), //telling how to build keys for map
                        Collectors.summingDouble(f -> f.getKilograms())));//telling how to build a single double

        return report;
    }

    public Map<Category, BigDecimal> createValueForCategory(LocalDate theeDay) {
        List<Forage> fromTheDay = findByDate(theeDay);
        BigDecimal medicinal = BigDecimal.ZERO;
        BigDecimal edible = BigDecimal.ZERO;
        BigDecimal inedible = BigDecimal.ZERO;
        BigDecimal poisonous = BigDecimal.ZERO;

        for(Forage forage : fromTheDay){
            BigDecimal value = calculateValue(forage);

            switch (forage.getItem().getCategory()){
                case MEDICINAL:
                    medicinal = medicinal.add(value);
                    break;
                case EDIBLE:
                    edible = edible.add(value);
                    break;
                case INEDIBLE:
                    inedible = inedible.add(value);
                    break;
                case POISONOUS:
                    poisonous = poisonous.add(value);
                    break;
            }
        }
        Map<Category, BigDecimal> report = new HashMap<>();
        report.put(Category.EDIBLE, edible);
        report.put(Category.INEDIBLE, inedible);
        report.put(Category.MEDICINAL, medicinal);
        report.put(Category.POISONOUS, poisonous);

        return report;
    }

    private BigDecimal calculateValue(Forage forage) {
        BigDecimal pricePer = forage.getItem().getDollarPerKilogram();
        Double totalKilo = forage.getKilograms();
        BigDecimal convertedNum = BigDecimal.valueOf(totalKilo);
        BigDecimal totalPrice = pricePer.multiply(convertedNum);

        return totalPrice;
    }
}
