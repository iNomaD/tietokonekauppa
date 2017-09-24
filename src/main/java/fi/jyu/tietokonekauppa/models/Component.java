package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fi.jyu.tietokonekauppa.models.components.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

// persistence
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// jackson serialization
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, defaultImpl = Component.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Case.class, name = "Case"),
        @JsonSubTypes.Type(value = Disk.class, name = "Disk"),
        @JsonSubTypes.Type(value = GPU.class, name = "GPU"),
        @JsonSubTypes.Type(value = Motherboard.class, name = "Motherboard"),
        @JsonSubTypes.Type(value = Processor.class, name = "Processor"),
        @JsonSubTypes.Type(value = PSU.class, name = "PSU"),
        @JsonSubTypes.Type(value = RAM.class, name = "RAM") }
)
public abstract class Component implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("name")
    private String name; // name that appears in the table

    @JsonProperty("vendor")
    private String vendor;

    @NotNull
    @JsonProperty("price")
    private Integer price;

    @NotNull
    @JsonProperty("price_units")
    private String priceUnits; // USD or EUR

    @JsonProperty("amount_available")
    private Integer amountAvailable; // >=0

    @OneToMany(fetch = FetchType.EAGER, mappedBy="component", cascade=CascadeType.ALL)
    @JsonProperty("links")
    private List<Link> links;

    protected Component(){}

    protected Component(String name, String vendor, Integer price, String priceUnits, Integer amountAvailable) {
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.priceUnits = priceUnits;
        this.amountAvailable = amountAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceUnits() {
        return priceUnits;
    }

    public void setPriceUnits(String priceUnits) {
        this.priceUnits = priceUnits;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public static enum Type{
        Case, Disk, GPU, Motherboard, Processor, PSU, RAM;

        public static Type getType(Component item) {
            if(item instanceof fi.jyu.tietokonekauppa.models.components.Case)
                return Case;
            if(item instanceof fi.jyu.tietokonekauppa.models.components.Disk)
                return Disk;
            if(item instanceof fi.jyu.tietokonekauppa.models.components.GPU)
                return GPU;
            if(item instanceof fi.jyu.tietokonekauppa.models.components.Motherboard)
                return Motherboard;
            if(item instanceof fi.jyu.tietokonekauppa.models.components.Processor)
                return Processor;
            if(item instanceof fi.jyu.tietokonekauppa.models.components.PSU)
                return PSU;
            return RAM;
        }

        public static Type getType(String typeLoweCase){
            switch (typeLoweCase){
                case "case":
                    return Case;
                case "disk":
                    return Disk;
                case "gpu":
                    return GPU;
                case "motherboard":
                    return Motherboard;
                case "processor":
                    return Processor;
                case "psu":
                    return PSU;
                case "ram":
                    return RAM;
                default:
                    return null;
            }
        }
    }
}
