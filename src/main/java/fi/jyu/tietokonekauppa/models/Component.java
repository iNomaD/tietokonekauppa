package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {

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
    }

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name; // name that appears in the table

    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("price_units")
    private String priceUnits; // USD or EUR

    @JsonProperty("amount_available")
    private Integer amountAvailable; // >=0

    @JsonProperty("links")
    private List<Link> links = new ArrayList<>();

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

    public void addLink(String url, String rel) {
        Link link = new Link(url, rel);
        links.add(link);
    }
}
