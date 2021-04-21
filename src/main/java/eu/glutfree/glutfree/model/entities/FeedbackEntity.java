package eu.glutfree.glutfree.model.entities;

import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="feedbacks")
public class FeedbackEntity extends BaseEntity{

    private String name;
    private int score;
    private String feedbackText;
    private String webSiteUrl;
    //Logo Image
    private String urlToPic;
    private TypeOfPlaceEnums typeOfPlace;
    private UserEntity user;


    public FeedbackEntity() {
    }
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Min(value = 1)
    @Max(value = 5)
    @Column(nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(nullable = false, columnDefinition = "TEXT", name = "feedback_text")
    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    @Column(name = "web_site_url")
    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }
    @Column(name = "url_to_pic")
    public String getUrlToPic() {
        return urlToPic;
    }

    public void setUrlToPic(String urlToPic) {
        this.urlToPic = urlToPic;
    }
    @Column(name = "type_of_place")
    public TypeOfPlaceEnums getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(TypeOfPlaceEnums typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }
    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
