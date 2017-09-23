package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "processors")
public class Processor extends Component {

    @JsonProperty("processorCount")
    private Integer processorCount;

    @JsonProperty("speed")
    private Integer speed;//GHz

    @JsonProperty("ramType")
    private String ramType;

    @JsonProperty("socket")
    private String socket;

    @JsonProperty("manufacturer")
    private String manufacturer;

    public Processor(){};

    public Processor(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, Integer processorCount, Integer speed, String ramType, String socket, String manufacturer) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.processorCount = processorCount;
        this.speed = speed;
        this.ramType = ramType;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }

    public Integer getProcessorCount() {
        return processorCount;
    }

    public void setProcessorCount(Integer processorCount) {
        this.processorCount = processorCount;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
