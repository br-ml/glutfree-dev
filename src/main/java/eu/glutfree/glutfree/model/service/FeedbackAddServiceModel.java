package eu.glutfree.glutfree.model.service;

import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import org.springframework.web.multipart.MultipartFile;

public class FeedbackAddServiceModel {

    private String name;
    private int score;
    private String feedbackText;
    private String webSiteUrl;
    private MultipartFile image;
    private String urlToPic;
    private TypeOfPlaceEnums typeOfPlace;

    public FeedbackAddServiceModel() {
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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

    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
    }

    public TypeOfPlaceEnums getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(TypeOfPlaceEnums typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }
}
