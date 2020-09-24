package my.repo.sainsburys.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.service.scraper.BerryExtractor;

@Log4j2
@RequiredArgsConstructor
abstract class AbstractBerryExtractorTemplate implements BerryExtractor {
    
    protected final String fragment;
    
    public final BerryPageElement pick() {

        try {
            
            return BerryPageElement.builder()
                    .title(retrieveTitle())
                    .kcal_per_100g(retrieveKcal100g())
                    .unit_price(retrieveUnitPrice())
                    .description(retrieveDescription()).build();
            
        } catch (Exception e) {
            log.error("Cannot extract all required info from fragment \n{} \n[message: {}]", fragment.substring(0,300), e);
            return null;
        }
    }
    
    protected abstract String retrieveTitle();
    
    protected abstract int retrieveKcal100g();
    
    protected abstract int retrieveUnitPrice();
    
    protected abstract String retrieveDescription();
}
