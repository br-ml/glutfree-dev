package eu.glutfree.glutfree.model.bindings;

import eu.glutfree.glutfree.model.entities.FoodEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class StoreAddBindingModel {

    private String name;
    private String storeWebSiteUrl;
    private String logoUrl;

    public StoreAddBindingModel() {
    }
    @NotBlank(message = "The name cannot be empty!")
    @Length(min = 1, max = 30, message = "The feedback name must contains between 1 and 30 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 2, max = 512, message = "The web site url name must contains between 2 and 512 characters.")
    public String getStoreWebSiteUrl() {
        return storeWebSiteUrl;
    }

    public void setStoreWebSiteUrl(String storeWebSiteUrl) {
        this.storeWebSiteUrl = storeWebSiteUrl;
    }
    @Length(min = 2, max = 512, message = "The Logo url name must contains between 2 and 512 characters.")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
