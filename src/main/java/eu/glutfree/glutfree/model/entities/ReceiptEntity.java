package eu.glutfree.glutfree.model.entities;

import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="receipts")
public class ReceiptEntity extends BaseEntity{

    private String name;
    private String description;
    private String productsList;
    private TypeOfMealsEnums typeOfMeal;
    private int duration;
    private String urlToPic;

    private UserEntity user;

    public ReceiptEntity() {
    }


    @Column(nullable = false)
    @Size(min = 2, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "products_list", nullable = false)
    @Size(min = 2, max = 50)
    public String getProductsList() {
        return productsList;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }
    @Enumerated
    @Column(name = "type_of_meal", nullable = false)
    public TypeOfMealsEnums getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(TypeOfMealsEnums typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }
    @Column(name = "url_to_pic")
    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
    }
    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Min(value = 1)
    @Max(value = 500)
    @Column(nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
