package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

public class PSU extends Component {

    @JsonProperty("size")
    private Integer size;//W

    @JsonProperty("weight")
    private float weight;//lbs

    @JsonProperty("dimensions")
    private String dimensions;//inches

    public PSU(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, Integer size, float weight, String dimensions) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.size = size;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
