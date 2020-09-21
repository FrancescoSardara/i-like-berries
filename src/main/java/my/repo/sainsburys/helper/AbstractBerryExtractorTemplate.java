package my.repo.sainsburys.helper;

import lombok.RequiredArgsConstructor;
import my.repo.sainsburys.model.BerryPageElement;

@RequiredArgsConstructor
abstract class AbstractBerryExtractorTemplate {
    
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
