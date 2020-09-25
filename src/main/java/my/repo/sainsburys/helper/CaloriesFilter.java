package my.repo.sainsburys.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import my.repo.sainsburys.model.BerryPageElement;

public class CaloriesFilter extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField (Object pojo, 
                                  JsonGenerator jgen, 
                                  SerializerProvider provider, 
                                  PropertyWriter writer) throws Exception {
        
        if (include(writer)) {
            if (!writer.getName().equals("kcal_per_100g")) {
                writer.serializeAsField(pojo, jgen, provider);
                return;
            }
            
            int kcal_per_100g = ((BerryPageElement) pojo).getKcal_per_100g();
            
            if (kcal_per_100g >= 0) {
                writer.serializeAsField(pojo, jgen, provider);
            }
        } else if (!jgen.canOmitFields()) {
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }
    }
}
