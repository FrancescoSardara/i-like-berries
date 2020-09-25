package my.repo.sainsburys.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.repo.sainsburys.helper.TwoDecimalSerializer;

@Builder
@RequiredArgsConstructor
@Getter
public class PageTotal {
    
    @JsonSerialize(using = TwoDecimalSerializer.class)
    private final int gross;

    @JsonSerialize(using = TwoDecimalSerializer.class)
    private final int vat;
}
