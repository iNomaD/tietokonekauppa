package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "disks")
public class Disk extends Component {

    @JsonProperty("type")
    private String type; // HDD or SSD

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("capacityUnits")
    private String capacityUnits; //GB or TB

    @JsonProperty("rpm")
    private Integer rpm;

    public Disk(){}

    public Disk(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, String type, Integer capacity, String capacityUnits, Integer rpm) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.type = type;
        this.capacity = capacity;
        this.capacityUnits = capacityUnits;
        this.rpm = rpm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCapacityUnits() {
        return capacityUnits;
    }

    public void setCapacityUnits(String capacityUnits) {
        this.capacityUnits = capacityUnits;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }
}
