package my.repo.sainsburys;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class ScraperApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScraperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start scraping url: {}", "Saintsburys");
    }
}
