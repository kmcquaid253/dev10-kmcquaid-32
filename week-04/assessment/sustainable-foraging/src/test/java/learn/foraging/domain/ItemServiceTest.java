package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    ItemService service = new ItemService(new ItemRepositoryDouble());

    @Test
    void shouldNotSaveNullName() throws DataException {
        Item item = new Item(0, null, Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveBlankName() throws DataException {
        Item item = new Item(0, "   \t\n", Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddInedibleWithValueGreaterThanZero() throws DataException {
        Item item = new Item(99, "Test Item", Category.INEDIBLE, new BigDecimal("9.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddInedibleWithValueLessThanZero() throws DataException {
        Item item = new Item(99, "Test Item", Category.INEDIBLE, new BigDecimal("-8.23"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPoisonousWithValueGreaterThanZero() throws DataException {
        Item item = new Item(100, "Cherry Test", Category.POISONOUS, new BigDecimal("4.88"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPoisonousWithValueLessThanZero() throws DataException {
        Item item = new Item(100, "Cherry Test", Category.POISONOUS, new BigDecimal("-3.75"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldAddInedibleWithValueOfZero() throws DataException {
        Item item = new Item(100, "Test Item", Category.INEDIBLE, new BigDecimal("0"));
        Result<Item> result = service.add(item);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldAddPoisonousWithValueOfZero() throws DataException {
        Item item = new Item(101, "Cherry Test", Category.POISONOUS, new BigDecimal("0"));
        Result<Item> result = service.add(item);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotSaveNegativeDollars() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("-5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveTooLargeDollars() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("9999.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldSave() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("5.00"));

        Result<Item> result = service.add(item);

        assertNotNull(result.getPayload());
        assertEquals(2, result.getPayload().getId());
    }

}