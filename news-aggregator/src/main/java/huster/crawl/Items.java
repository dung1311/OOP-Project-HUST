package com.example;

public class Items {
    private String link;
    private String title;
    private String category;
    private String summary;
    private String author;
    private String date;
    
    public Items(){}

    public Items(String title, String author, String date, String summary, String link, String category) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.link = link;
        this.category = category;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
}
