package my.repo.sainsburys.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class BerryPageElement {
    
    private final String title;
    private final int kcal_per_100g;
    private final int unit_price;
    private final String description;
}
