package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "psus")
public class PSU extends Component {

    @JsonProperty("size")
    private Integer size;//W

    @JsonProperty("weight")
    private Float weight;//lbs

    @JsonProperty("dimensions")
    private String dimensions;//inches

    public PSU(){};

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
}
