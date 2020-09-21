package my.repo.sainsburys.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;

public class JsoupBerryExtractor extends AbstractBerryExtractorTemplate {

    private static final String TITLE_QUERY = "#content .productSummary .productTitleDescriptionContainer h1";
    private static final String KCAL_QUERY_V1 = "#information table.nutritionTable tbody tr[class^=tableRow]:eq(1) td";
    private static final String KCAL_QUERY_V2 = "#information table.nutritionTable tbody tr:eq(1) td";
    private static final String PRICE_PER_UNIT_QUERY = "#content .productSummary p.pricePerUnit";
    private static final String DESCRIPTION_QUERY_V1 = "#information .memo p";
    private static final String DESCRIPTION_QUERY_V2 = "#information .productText p";
    
    private final Document doc;
    
    public JsoupBerryExtractor(String fragment) {
        super(fragment);
        doc = Jsoup.parseBodyFragment(fragment);
    }

    @Override
    protected String retrieveTitle() {
        Element title = doc.selectFirst(TITLE_QUERY);
        return title.text();
    }

    @Override
    protected int retrieveKcal100g() {
        Optional<Element> kcalRowTry = Optional.ofNullable(doc.selectFirst(KCAL_QUERY_V1));
        Optional<Element> kcalRowTryAgain = Optional.ofNullable(doc.selectFirst(KCAL_QUERY_V2));
        
        return kcalRowTry
                .or(() -> kcalRowTryAgain)
                .map(Element::text)
                .map(this::stripLetters)
                .map(Integer::parseInt)
                .orElse(-1);
    }

    @Override
    protected int retrieveUnitPrice() {
        Element pricePerUnit = doc.selectFirst(PRICE_PER_UNIT_QUERY);
        
        return Integer.parseInt(stripCurrencyAndDot(pricePerUnit.ownText()));
    }

    @Override
    protected String retrieveDescription() {
        Optional<Element> descriptionTry = Optional.ofNullable(doc.selectFirst(DESCRIPTION_QUERY_V1));
        Optional<Element> descriptionTryAgain = Optional.ofNullable(doc.selectFirst(DESCRIPTION_QUERY_V2));

        return descriptionTry
                .or(() -> descriptionTryAgain)
                .map(Element::text)
                .orElse("");
    }
    
    private String stripLetters(String input) {
        return input.replaceAll("[a-zA-Z]*", "");
    }

    private String stripCurrencyAndDot(String input) {
        return input.replaceAll("[£.]*", "");
    }
}
