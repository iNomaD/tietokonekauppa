package fi.jyu.tietokonekauppa.models.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fi.jyu.tietokonekauppa.models.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "motherboards")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, defaultImpl = Motherboard.class)
public class Motherboard extends Component {

    @JsonProperty("cpuModelSocket")
    private String cpuModelSocket;

    @JsonProperty("ramMemoryTechnology")
    private String ramMemoryTechnology;

    @JsonProperty("formFactor")
    private String formFactor;

    @JsonProperty("memoryMaximumSize")
    private Integer memoryMaximumSize;//GB

    public Motherboard(){};

    public Motherboard(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable, String cpuModelSocket, String ramMemoryTechnology, String formFactor, Integer memoryMaximumSize) {
        super(name, vendor, price, priceUnits, amountAvailable);
        this.cpuModelSocket = cpuModelSocket;
        this.ramMemoryTechnology = ramMemoryTechnology;
        this.formFactor = formFactor;
        this.memoryMaximumSize = memoryMaximumSize;
    }

    public String getCpuModelSocket() {
        return cpuModelSocket;
    }

    public void setCpuModelSocket(String cpuModelSocket) {
        this.cpuModelSocket = cpuModelSocket;
    }

    public String getRamMemoryTechnology() {
        return ramMemoryTechnology;
    }

    public void setRamMemoryTechnology(String ramMemoryTechnology) {
        this.ramMemoryTechnology = ramMemoryTechnology;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public Integer getMemoryMaximumSize() {
        return memoryMaximumSize;
    }

    public void setMemoryMaximumSize(Integer memoryMaximumSize) {
        this.memoryMaximumSize = memoryMaximumSize;
    }
}
