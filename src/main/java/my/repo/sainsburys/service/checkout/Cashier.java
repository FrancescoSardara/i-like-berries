package my.repo.sainsburys.service.checkout;

import my.repo.sainsburys.model.BerryPageElement;
import my.repo.sainsburys.model.PageTotal;

import java.util.List;

public interface Cashier {

    PageTotal checkout(List<BerryPageElement> berries);
    
}
