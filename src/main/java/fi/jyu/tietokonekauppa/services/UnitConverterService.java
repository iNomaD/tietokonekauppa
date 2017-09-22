package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.web.PriceUnits;

public class UnitConverterService {
    private static double USD_IN_EUR = 1.19385;

    public static void convert(Component component, PriceUnits priceUnits){
        if(priceUnits != null) {
            String currentPriceUnits = component.getPriceUnits();
            //from USD to EUR
            if (currentPriceUnits.equalsIgnoreCase("USD") && priceUnits == PriceUnits.EUR) {
                component.setPrice((int) (component.getPrice() / USD_IN_EUR));
                component.setPriceUnits("EUR");
            }
            //from EUR to USD
            else if (currentPriceUnits.equalsIgnoreCase("EUR") && priceUnits == PriceUnits.USD) {
                component.setPrice((int) (component.getPrice() * USD_IN_EUR));
                component.setPriceUnits("USD");
            }
        }
    }
}
