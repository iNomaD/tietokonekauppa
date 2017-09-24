package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cases")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, defaultImpl = Case.class)
public class Case extends Component {

    @JsonProperty("weight")
    private Float weight;//lbs

    @JsonProperty("dimensions")
    private String dimensions;//inches

    @JsonProperty("color")
    private String color;

    public Case(){};

    public Case(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, float weight, String dimensions, String color) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.weight = weight;
        this.dimensions = dimensions;
        this.color = color;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
