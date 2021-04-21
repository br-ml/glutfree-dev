package eu.glutfree.glutfree.model.view;

import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class FoodViewModel {

    private Long id;
    private String name;
    private String brand;
    private String urlToPic;
    private String urlToLabelImage;
    private String pictureDate;
    private String copyright;
    private boolean nimaTested;
    private boolean markedAsGF;
    private boolean withoutLactose;
    private String storelogoUrl;
    private String store;


    public FoodViewModel() {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStorelogoUrl() {
        return storelogoUrl;
    }

    public void setStorelogoUrl(String storelogoUrl) {
        this.storelogoUrl = storelogoUrl;
    }

    public String getUrlToLabelImage() {
        return urlToLabelImage;
    }

    public void setUrlToLabelImage(String urlToLabelImage) {
        this.urlToLabelImage = urlToLabelImage;
    }

    public String getPictureDate() {
        return pictureDate;
    }

    public void setPictureDate(String pictureDate) {
        this.pictureDate = pictureDate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
