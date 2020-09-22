package my.repo.sainsburys.helper;

import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.service.BerryExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;

class JsoupBerryExtractorTest {

    private BerryExtractor sut;
    private Path testDir;

    @BeforeEach
    void setUp() {
        testDir = Path.of("src/test/resources");
    }

    @Test
    @DisplayName("Can extract all info from a fragment")
    void readAllInfo() {

        swallowException(() -> {
            // arrange
            sut = initializeSUT("strawberries-400g.html");

            // act
            BerryPageElement berry = sut.pick();
            
            // assert
            assertAll(
                    () -> assertThat(berry.getTitle(), is("Sainsbury's Strawberries 400g")),
                    () -> assertThat(berry.getUnit_price(), is(175)),
                    () -> assertThat(berry.getKcal_per_100g(), is(33)),
                    () -> assertThat(berry.getDescription(), is("by Sainsbury's strawberries"))
            );
        });
    }

    @Test
    @DisplayName("Can extract all info from a fragment with unusual tree model")
    void readAllInfoDifferentTree() {

        swallowException(() -> {
            // arrange
            sut = initializeSUT("cherry-punnet-200g-different-page-tree.html");

            // act
            BerryPageElement berry = sut.pick();

            // assert
            assertAll(
                    () -> assertThat(berry.getTitle(), is("Sainsbury's Cherry Punnet 200g")),
                    () -> assertThat(berry.getUnit_price(), is(150)),
                    () -> assertThat(berry.getKcal_per_100g(), is(52)),
                    () -> assertThat(berry.getDescription(), is("Cherries"))
            );
        });
    }
    
    @Test
    @DisplayName("When nutrition info is missing set Kcal_per_100g to -1")
    void missingKcal_per_100() {

        swallowException(() -> {
            // arrange
            sut = initializeSUT("mixed-berries-300g-no-nutrition-table.html");

            // act
            BerryPageElement berry = sut.pick();

            // assert
            assertThat(berry.getKcal_per_100g(), is(-1));
        });
    }

    private JsoupBerryExtractor initializeSUT(String fileName) throws IOException {
        Path testFile = testDir.resolve(fileName);
        
        String fragment = Files.readString(testFile);

        return new JsoupBerryExtractor(fragment);
    }

    private void swallowException(CheckedRunnable task) {
        try {
            task.run();
        } catch (Exception e) {
            Assertions.fail("Cannot read from test file", e);
        }
    }

    @FunctionalInterface
    public interface CheckedRunnable {
        void run() throws Exception;
    }
}