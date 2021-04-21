//package eu.glutfree.glutfree.model.entities;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "food_comments")
//public class FoodCommentEntity extends BaseEntity {
//
//    private UserEntity author;
//    private FoodEntity foodEntity;
//    private String content;
//    private LocalDateTime addedOn;
//
//    public FoodCommentEntity() {
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    public UserEntity getAuthor() {
//        return author;
//    }
//
//    public FoodCommentEntity setAuthor(UserEntity author) {
//        this.author = author;
//        return this;
//    }
//
//    @ManyToOne
//    public FoodEntity getFoodEntity() {
//        return foodEntity;
//    }
//
//    public FoodCommentEntity setFoodEntity(FoodEntity foodEntity) {
//        this.foodEntity = foodEntity;
//        return this;
//    }
//
//
//
//    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
//    public String getContent() {
//        return content;
//    }
//
//    public FoodCommentEntity setContent(String content) {
//        this.content = content;
//        return this;
//    }
//
//    @Column(name = "added_on", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    public LocalDateTime getAddedOn() {
//        return addedOn;
//    }
//
//    public FoodCommentEntity setAddedOn(LocalDateTime addedOn) {
//        this.addedOn = addedOn;
//        return this;
//    }
//}
