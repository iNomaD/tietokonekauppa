package fi.jyu.tietokonekauppa.web.providers;

import fi.jyu.tietokonekauppa.web.PriceUnits;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider // the annotation preregisters our Provider for JAX-RS to be used
public class PriceUnitsConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[]
            annotations) {
        if(rawType.getName().equals(PriceUnits.class.getName())){
            return new ParamConverter<T>(){
                @Override
                public T fromString(String value){
                    PriceUnits priceUnits = null;
                    if(value != null) {
                        if ("eur".equalsIgnoreCase(value)) {
                            priceUnits = PriceUnits.EUR;
                        }
                        if ("usd".equalsIgnoreCase(value)) {
                            priceUnits = PriceUnits.USD;
                        }
                    }
                    return rawType.cast(priceUnits);
                }
                @Override
                public String toString(T bean) { if(bean == null){return null;} return bean.toString(); }
            };
        }return null;
    }
}