package com.da.model;

public class Album {
    private long userId;
    private long id;
    private String title;

    public Album() {
    }

    public Album(long userId, long id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                "userId=" + userId + ",\n" +
                "id=" + id +  ",\n" +
                "title='" + title + "\n" +
                "}\n";
    }
}
