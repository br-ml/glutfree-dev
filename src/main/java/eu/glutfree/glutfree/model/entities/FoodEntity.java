package eu.glutfree.glutfree.model.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="foods")
public class FoodEntity extends BaseEntity{

    private String name;
    private String brand;
    private String urlToPic;
    private String copyright;
    private boolean nimaTested;
    private boolean markedAsGF = false;
    private boolean withoutLactose = false;
    private String urlToLabelImage;
    private LocalDate pictureDate;
    private StoreEntity store;


    public FoodEntity() {
    }
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }


    @Column(name = "url_top_pic")
    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
    }
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUrlToLabelImage() {
        return urlToLabelImage;
    }

    public void setUrlToLabelImage(String urlToLabelImage) {
        this.urlToLabelImage = urlToLabelImage;
    }

    public LocalDate getPictureDate() {
        return pictureDate;
    }

    public void setPictureDate(LocalDate pictureDate) {
        this.pictureDate = pictureDate;
    }

    public boolean isNimaTested() {
        return nimaTested;
    }

    public void setNimaTested(boolean nimaTested) {
        this.nimaTested = nimaTested;
    }

    public boolean isMarkedAsGF() {
        return markedAsGF;
    }

    public void setMarkedAsGF(boolean markedAsGF) {
        this.markedAsGF = markedAsGF;
    }

    public boolean isWithoutLactose() {
        return withoutLactose;
    }

    public void setWithoutLactose(boolean withoutLactose) {
        this.withoutLactose = withoutLactose;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
