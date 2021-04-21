package eu.glutfree.glutfree.model.service;

import java.time.LocalDateTime;

public class LogServiceModel {

    private Long id;
//    private String user;
    private String food;
    private String action;
    private LocalDateTime dateTime;

    public LogServiceModel() {
    }

//    public String getUser() {
//        return user;
//    }
//
//    public LogServiceModel setUser(String user) {
//        this.user = user;
//        return this;
//    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getAction() {
        return action;
    }

    public LogServiceModel setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogServiceModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LogServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}