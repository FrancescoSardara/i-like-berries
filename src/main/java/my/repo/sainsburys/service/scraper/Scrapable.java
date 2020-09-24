package my.repo.sainsburys.service.scraper;

import java.util.List;

public interface Scrapable<T> {
    
    List<T> scrape(List<String> hrefs);
}
