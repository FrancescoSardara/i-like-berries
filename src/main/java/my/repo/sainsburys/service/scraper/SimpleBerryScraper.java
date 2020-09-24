package my.repo.sainsburys.service.scraper;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.repo.sainsburys.model.BerryPageElement;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Log4j2
@Service
@AllArgsConstructor
public class SimpleBerryScraper implements Scrapable<BerryPageElement> {
    
    private final BerryExtractorFactory extractorFactory;
    
    @Override
    public List<BerryPageElement> scrape(List<String> hrefs) {
            
            return hrefs.stream()
                    .map(productUrl -> followLink(productUrl))
                    .filter(content -> StringUtils.isNotEmpty(content))
                    .map(html -> extractorFactory.bindTo(html).pick())
                    .filter(Objects::nonNull)
                    .collect(toList());
    }
    
    private String followLink(String link) {
        try {
            return Jsoup.connect(link).get().html();
        } catch (IOException e) {
            log.error("Cannot follow link: {}", link);
            return "";
        }
    }
}
