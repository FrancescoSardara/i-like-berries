package my.repo.sainsburys.helper;

import lombok.RequiredArgsConstructor;
import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.service.scraper.BerryExtractor;

@RequiredArgsConstructor
abstract class AbstractBerryExtractorTemplate implements BerryExtractor {
    
    protected final String fragment;
    
    public final BerryPageElement pick() {
        
        return BerryPageElement.builder()
                .title(retrieveTitle())
                .kcal_per_100g(retrieveKcal100g())
                .unit_price(retrieveUnitPrice())
                .description(retrieveDescription()).build();
    }
    
    protected abstract String retrieveTitle();
    
    protected abstract int retrieveKcal100g();
    
    protected abstract int retrieveUnitPrice();
    
    protected abstract String retrieveDescription();
}
