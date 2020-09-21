package my.repo.sainsburys.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class PageTotal {
    
    private final int gross;
    private final int vat;
}
