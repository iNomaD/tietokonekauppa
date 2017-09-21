package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Component {

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
}