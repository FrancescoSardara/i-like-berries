package my.repo.sainsburys.service.checkout;

import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.model.PageTotal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedVatBerryCalculatorTest {
    
    private Cashier sut;

    @BeforeEach
    void setUp() {
        sut = new FixedVatBerryCalculator();
    }

    @Test
    @DisplayName("An empty list of berries should have gross and vat equal to zero")
    void noItem() {
        // arrange
        List<BerryPageElement> noItems = Collections.emptyList();
        
        // act
        PageTotal actual = sut.checkout(noItems);
        
        // assert
        assertAll(
                () -> assertEquals(actual.getGross(), 0),
                () -> assertEquals(actual.getVat(), 0)
        );
    }

    @Test
    @DisplayName("Calculate the correct total of a list of berries ")
    void withItems() {
        // arrange
        List<BerryPageElement> berries = buildListofBerries();

        // act
        PageTotal actual = sut.checkout(berries);

        // assert
        assertAll(
                () -> assertEquals(actual.getGross(), 500),
                () -> assertEquals(actual.getVat(), 83)
        );
    }
    
    private List<BerryPageElement> buildListofBerries() {
        return List.of(
                BerryPageElement.builder().title("Strawberries").kcal_per_100g(33).unit_price(175).description("by Sainsbury's").build(),
                BerryPageElement.builder().title("Blueberries").kcal_per_100g(45).unit_price(175).description("by Sainsbury's").build(),
                BerryPageElement.builder().title("Cherry Punnet").kcal_per_100g(52).unit_price(150).description("Cherries").build()
        );
    }
}