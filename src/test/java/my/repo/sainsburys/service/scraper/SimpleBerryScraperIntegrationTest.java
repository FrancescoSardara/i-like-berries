package my.repo.sainsburys.service.scraper;

import my.repo.sainsburys.model.BerryPageElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes = {
        SimpleBerryScraper.class, 
        JsoupBerryExtractorFactory.class
})
class SimpleBerryScraperIntegrationTest {
    
    @Autowired
    private Scrapable<BerryPageElement> sut;
    
    private static final String STRAWBERRY = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
    private static final String MIXED_BERRIES = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html";
    private static final String WRONG_PAGE = "https://www.google.com";

    @Test
    @DisplayName("Providing no links return an empty list without bubbling up  exceptions")
    void noLinks() {
        // arrange
        
        //act
        List<BerryPageElement> berries = sut.scrape(List.of());

        // assert
        assertThat(berries, empty());
    }

    @Test
    @DisplayName("Can reach out to Sainsbury pages and download their html content")
    void withSainsburyLinks() {
        // arrange

        //act
        List<BerryPageElement> berries = sut.scrape(List.of(STRAWBERRY, MIXED_BERRIES));

        // assert
        assertThat(berries, hasSize(2));
    }

    @Test
    @DisplayName("Pages that don't follow Sainsbury test structure are not included")
    void withWrongLinks() {
        // arrange

        //act
        List<BerryPageElement> berries = sut.scrape(List.of(STRAWBERRY, WRONG_PAGE));

        // assert
        assertThat(berries, hasSize(1));
    }
}