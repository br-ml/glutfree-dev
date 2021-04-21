package eu.glutfree.glutfree.model.view;

import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import org.springframework.web.multipart.MultipartFile;


public class ReceiptViewModel {

    private Long id;
    private String name;
    private String description;
    private String productsList;
    private TypeOfMealsEnums typeOfMeal;
    private int duration;
    private String urlToPic;
    private MultipartFile image;

    public ReceiptViewModel() {
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

    public String getProductsList() {
        return productsList;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }

    public TypeOfMealsEnums getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(TypeOfMealsEnums typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }


    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
