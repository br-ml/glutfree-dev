package eu.glutfree.glutfree.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="stores")
public class StoreEntity extends BaseEntity{

    private String name;
    private String storeWebSiteUrl;
    private String logoUrl;
//    private Set<FoodEntity> foodEntities;


    public StoreEntity() {
    }
    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 1, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @ManyToMany(mappedBy = "stores")
//    public Set<FoodEntity> getFoods() {
//        return foodEntities;
//    }
//
//    public void setFoods(Set<FoodEntity> foodEntities) {
//        this.foodEntities = foodEntities;
//    }
    @Column(name = "web_site_url" ,length = 512)
    public String getStoreWebSiteUrl() {
        return storeWebSiteUrl;
    }

    public void setStoreWebSiteUrl(String storeWebSiteUrl) {
        this.storeWebSiteUrl = storeWebSiteUrl;
    }
    @Column(name = "logo_url", length = 512)
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
