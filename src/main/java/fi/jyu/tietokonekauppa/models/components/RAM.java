package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rams")
public class RAM extends Component {

    @JsonProperty("memorySize")
    private Integer memorySize;//GB

    @JsonProperty("ramType")
    private String ramType;

    @JsonProperty("formFactor")
    private String formFactor;

    @JsonProperty("speed")
    private Integer speed;//MHz

    @JsonProperty("ramMemoryTechnology")
    private String ramMemoryTechnology;

    public RAM(){};

    public RAM(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, Integer memorySize, String ramType, String formFactor, Integer speed, String ramMemoryTechnology) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.memorySize = memorySize;
        this.ramType = ramType;
        this.formFactor = formFactor;
        this.speed = speed;
        this.ramMemoryTechnology = ramMemoryTechnology;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getRamMemoryTechnology() {
        return ramMemoryTechnology;
    }

    public void setRamMemoryTechnology(String ramMemoryTechnology) {
        this.ramMemoryTechnology = ramMemoryTechnology;
    }
}
