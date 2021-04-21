package eu.glutfree.glutfree.model.bindings;

import eu.glutfree.glutfree.model.entities.StoreEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class FoodAddBindingModel {

    private String name;
    private String brand;
    private String copyright;
    private MultipartFile image;
    private MultipartFile labelImage;
    private LocalDate pictureDate;
    private boolean nimaTested;
    private boolean markedAsGF;
    private boolean withoutLactose;
    private String store;

    public FoodAddBindingModel() {
    }
    @NotBlank(message = "The name cannot be empty!")
    @Length(min = 2, max = 50, message = "The name must contains between 2 and 50 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "The brand cannot be empty!")
    @Length(min = 2, max = 50, message = "The brand name must contains between 2 and 50 characters.")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    //
//    public String getUrlToPic() {
//        return urlToPic;
//    }
//
//    public void setUrlToPic(String urlToPic) {
//        this.urlToPic = urlToPic;
//    }

    //
//    public String getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }


    public MultipartFile getLabelImage() {
        return labelImage;
    }

    public void setLabelImage(MultipartFile labelImage) {
        this.labelImage = labelImage;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @NotBlank(message = "The Store cannot be empty!")
    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
