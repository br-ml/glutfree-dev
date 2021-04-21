//package eu.glutfree.glutfree.model.view;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import java.time.LocalDateTime;
//
//public class FoodCommentViewModel {
//
//    private String author;
//    private String content;
//    private LocalDateTime addedOn;
//
//    public FoodCommentViewModel() {
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public FoodCommentViewModel setContent(String content) {
//        this.content = content;
//        return this;
//    }
//
//    @JsonFormat(pattern="yyyy-MM-dd / HH:mm")
//    public LocalDateTime getDateAdded() {
//        return addedOn;
//    }
//
//    public FoodCommentViewModel setDateAdded(LocalDateTime addedOn) {
//        this.addedOn = addedOn;
//        return this;
//    }
//}
