package my.repo.sainsburys.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.repo.sainsburys.helper.TwoDecimalSerializer;

@JsonFilter("CaloriesFilter")
@Builder
@RequiredArgsConstructor
@Getter
public class BerryPageElement {
    
    private final String title;
    private final int kcal_per_100g;
    
    @JsonSerialize(using = TwoDecimalSerializer.class)
    private final int unit_price;
    private final String description;
}
