package com.example.donner;

public class Blogs {

    String title;
    String displayName;
    String img_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

    public Blogs(String title, String displayName, String img_url, String content) {
        this.title = title;
        this.displayName = displayName;
        this.img_url = img_url;
        this.content = content;
    }

}
