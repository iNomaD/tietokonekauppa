package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gpus")
public class GPU extends Component {

    @JsonProperty("ramSize")
    private Integer ramSize;//GB

    @JsonProperty("ramType")
    private String ramType;

    @JsonProperty("coprocessor")
    private String coprocessor;

    @JsonProperty("busWidth")
    private Integer busWidth;//bits

    @JsonProperty("clockSpeed")
    private Integer clockSpeed;//MHz

    public GPU(){};

    public GPU(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, Integer ramSize, String ramType, String coprocessor, Integer busWidth, Integer clockSpeed) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.ramSize = ramSize;
        this.ramType = ramType;
        this.coprocessor = coprocessor;
        this.busWidth = busWidth;
        this.clockSpeed = clockSpeed;
    }

    public Integer getRamSize() {
        return ramSize;
    }

    public void setRamSize(Integer ramSize) {
        this.ramSize = ramSize;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getCoprocessor() {
        return coprocessor;
    }

    public void setCoprocessor(String coprocessor) {
        this.coprocessor = coprocessor;
    }

    public Integer getBusWidth() {
        return busWidth;
    }

    public void setBusWidth(Integer busWidth) {
        this.busWidth = busWidth;
    }

    public Integer getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Integer clockSpeed) {
        this.clockSpeed = clockSpeed;
    }
}
