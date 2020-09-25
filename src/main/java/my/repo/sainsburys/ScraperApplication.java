package my.repo.sainsburys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.log4j.Log4j2;
import my.repo.sainsburys.helper.CaloriesFilter;
import my.repo.sainsburys.model.BerryPage;
import my.repo.sainsburys.service.Accountant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.fasterxml.jackson.databind.SerializationFeature.*;
import static org.apache.commons.lang3.StringUtils.*;

@Log4j2
@SpringBootApplication
public class ScraperApplication implements CommandLineRunner {
    
    @Value("${scraping.url.default}")
    private String defaultUrl; 
    private final Accountant accountant;
    
    @Autowired
    public ScraperApplication(Accountant accountant) {
        this.accountant = accountant;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScraperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String berryUrl = (args.length > 0 && isNotBlank(args[0])) 
                                        ? args[0]
                                        : defaultUrl;        
        
        log.info("Start scraping url: {}", berryUrl);
        
        BerryPage result = accountant.report(berryUrl);

        FilterProvider filters = new SimpleFilterProvider().addFilter("CaloriesFilter", new CaloriesFilter());
        
        String json = new ObjectMapper().enable(INDENT_OUTPUT).writer(filters).writeValueAsString(result);

        System.out.println(json);

        log.info("End scraping!");
    }
}
