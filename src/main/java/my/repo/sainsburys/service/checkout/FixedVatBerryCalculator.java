package my.repo.sainsburys.service.checkout;

import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.model.PageTotal;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedVatBerryCalculator implements Cashier {
    
    public static final double MAGIC_NUMBER_VAT = 1.0 / 6.0; // after all the math
    
    @Override
    public PageTotal checkout(List<BerryPageElement> berries) {
        
        int gross = berries.stream()
                .mapToInt(BerryPageElement::getUnit_price)
                .sum();

        return PageTotal.builder()
                .gross(gross)
                .vat(calcVat(gross)).build();
    }

    private int calcVat(int gross) {
        return (int) Precision.round(MAGIC_NUMBER_VAT * gross, 3);
    }
}
