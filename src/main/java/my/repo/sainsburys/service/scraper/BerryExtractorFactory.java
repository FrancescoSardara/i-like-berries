package my.repo.sainsburys.service.scraper;

import my.repo.sainsburys.service.scraper.BerryExtractor;

public interface BerryExtractorFactory {
    
    BerryExtractor bindTo(String htmlFragment);
}
