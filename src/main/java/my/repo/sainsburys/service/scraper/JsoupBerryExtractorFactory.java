package my.repo.sainsburys.service;

import my.repo.sainsburys.helper.JsoupBerryExtractor;
import org.springframework.stereotype.Component;

@Component
public class JsoupBerryExtractorFactory implements BerryExtractorFactory {
    
    @Override
    public BerryExtractor bindTo(String htmlFragment) {
        return new JsoupBerryExtractor(htmlFragment);
    }
}
