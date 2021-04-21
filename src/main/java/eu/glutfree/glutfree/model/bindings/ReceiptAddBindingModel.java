package eu.glutfree.glutfree.model.bindings;

import eu.glutfree.glutfree.model.entities.BaseEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class ReceiptAddBindingModel {

    private String name;
    private String description;
    private String productsList;
    private TypeOfMealsEnums typeOfMeal;
    private int duration;
    private MultipartFile image;



    public ReceiptAddBindingModel() {
    }
    @NotBlank(message = "The name cannot be empty!")
    @Length(min = 2, max = 50, message = "The name must contains between 2 and 50 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "The name cannot be empty!")
    @Length(min = 2, max = 50, message = "The product list must contains between 2 and 50 characters.")
    public String getProductsList() {
        return productsList;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }

//    @NotBlank(message = "You must select a category!")
    public TypeOfMealsEnums getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(TypeOfMealsEnums typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Length(min = 5, max = 1000, message = "The product description must be at least 5 characters and max 1000.")
    @NotBlank(message = "The description cannot be empty!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Min(value = 0, message = "Must be a more than 0")
    @Max(value = 500, message = "Must be a less than 500")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
