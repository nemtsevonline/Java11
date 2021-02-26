package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product book1 = new Book(2, "Война и мир", 100, "Толстой");
    private Product book2 = new Book(16, "Анна Каренина", 1990, "Лев Николаевич");
    private Product phone1 = new Smartphone(1, "Iphone 5", 9999999, "Apple");
    private Product phone2 = new Smartphone(99, "A51", 25000, "Samsung");
    private Product phone3 = new Smartphone(2, "Iphone 8", 29000, "Apple");
    private Product product1 = new Product(9, "Untitled", 10);

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(phone1);
        manager.add(product1);
        manager.add(phone2);
        manager.add(book2);
        manager.add(phone3);
    }

    @Test
    void searchByNotFoundTest() {
        Product[] actual = manager.searchBy("Москва");
        Product[] expected = new Product[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorBookOneFoundTest() {
        Product[] actual = manager.searchBy("Лев Николаевич");
        Product[] expected = new Product[]{book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNameBookOneFoundTest() {
        Product[] actual = manager.searchBy("Анна Каренина");
        Product[] expected = new Product[]{book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNamePhoneOneFoundTest() {
        Product[] actual = manager.searchBy("A51");
        Product[] expected = new Product[]{phone2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByVendorPhoneSeveralFoundTest() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{phone1, phone3};

        assertArrayEquals(expected, actual);

    }
}