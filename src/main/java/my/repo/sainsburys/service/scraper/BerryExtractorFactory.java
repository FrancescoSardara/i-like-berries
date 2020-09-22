package my.repo.sainsburys.service;

public interface BerryExtractorFactory {
    
    BerryExtractor bindTo(String htmlFragment);
}
