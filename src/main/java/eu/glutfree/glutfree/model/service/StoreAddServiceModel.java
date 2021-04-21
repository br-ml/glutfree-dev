package eu.glutfree.glutfree.model.service;

import eu.glutfree.glutfree.model.entities.FoodEntity;

import java.util.Set;

public class StoreAddServiceModel {

    private String name;
    private String storeWebSiteUrl;
    private String logoUrl;

    public StoreAddServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreWebSiteUrl() {
        return storeWebSiteUrl;
    }

    public void setStoreWebSiteUrl(String storeWebSiteUrl) {
        this.storeWebSiteUrl = storeWebSiteUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
