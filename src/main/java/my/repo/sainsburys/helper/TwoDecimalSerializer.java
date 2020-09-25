package my.repo.sainsburys.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;

public class TwoDecimalSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer priceTag,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)  throws IOException, JsonProcessingException {
        
        jsonGenerator.writeObject(Precision.round(priceTag  / 100.0, 2));
    }
    
}
