package my.repo.sainsburys.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.repo.sainsburys.model.BerryPage;
import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.model.PageTotal;
import my.repo.sainsburys.service.checkout.Cashier;
import my.repo.sainsburys.service.scraper.Scrapable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Log4j2
@Service
@RequiredArgsConstructor
public class Accountant {

    private static final String LINKS_QUERY = "#productLister .gridItem .productNameAndPromotions a[href]";
    
    private final Scrapable<BerryPageElement> scraper;
    private final Cashier cashier;
    
    public BerryPage report(String pageUrl) {
        
        try {
            
            Document doc = Jsoup.connect(pageUrl).get();
            
            List<String> links = doc.select(LINKS_QUERY)
                    .stream()
                    .map(link -> link.attr("abs:href"))
                    .collect(toList());

            List<BerryPageElement> berries = scraper.scrape(links);

            PageTotal total = cashier.checkout(berries);

            return BerryPage.builder()
                    .results(berries)
                    .total(total).build();
            
        } catch (IOException e) {
            log.error("Cannot connect to user provided url: \n{}", pageUrl);
            return BerryPage.builder().build();
        }
    }
    
}
