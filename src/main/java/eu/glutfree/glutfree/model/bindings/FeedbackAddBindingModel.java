package eu.glutfree.glutfree.model.bindings;

import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FeedbackAddBindingModel {

    private String name;
    private int score;
    private String feedbackText;
    private String webSiteUrl;
    private MultipartFile image;
    private TypeOfPlaceEnums typeOfPlace;

    public FeedbackAddBindingModel() {
    }

    @NotBlank(message = "The name cannot be empty!")
    @Length(min = 2, max = 50, message = "The feedback name must contains between 2 and 50 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 0, message = "The score  must be between 0 and 5.")
    @Max(value = 5, message = "The score  must be between 0 and 5.")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    @Length(min = 5, max = 1000, message = "The product description must be at least 5 characters and max 1000.")
    @NotBlank(message = "The feedback cannot be empty!")
    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

//    @NotBlank(message = "You must select a category!")
    public TypeOfPlaceEnums getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(TypeOfPlaceEnums typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }
}
