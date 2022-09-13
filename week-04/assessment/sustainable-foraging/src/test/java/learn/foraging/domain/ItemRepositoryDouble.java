package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Category;
import learn.foraging.models.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryDouble implements ItemRepository {

    private final ArrayList<Item> items = new ArrayList<>();

    public ItemRepositoryDouble() {
        Item itemOne = new Item(1, "Chanterelle", Category.EDIBLE, new BigDecimal("9.99"));
        Item itemTwo = new Item(2, "Duckweed", Category.INEDIBLE, new BigDecimal("0.00"));
        Item itemThree = new Item(3, "Fibercaps", Category.POISONOUS, new BigDecimal("0.00"));


        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemThree);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public Item findById(int id) {
        return findAll().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Item add(Item item) throws DataException {
        List<Item> all = findAll();

        int nextId = all.stream()
                .mapToInt(Item::getId)
                .max()
                .orElse(0) + 1;

        item.setId(nextId);

        all.add(item);
        return item;
    }
}
