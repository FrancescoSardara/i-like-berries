package my.repo.sainsburys.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor
@Getter
public class BerryPage {
    
    private List<BerryPageElement> results;
    
    private PageTotal total;
}
