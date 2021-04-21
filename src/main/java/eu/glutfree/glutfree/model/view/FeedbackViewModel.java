package eu.glutfree.glutfree.model.view;

import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import org.springframework.web.multipart.MultipartFile;

public class FeedbackViewModel {

    private Long id;
    private String name;
    private int score;
    private String feedbackText;
    private String webSiteUrl;
    private String urlToPic;
    private MultipartFile image;
    private TypeOfPlaceEnums typeOfPlace;

    public FeedbackViewModel() {
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

    public TypeOfPlaceEnums getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(TypeOfPlaceEnums typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }
}
