package com.example.donner;

public class StoryModel {
    private String storyTitle, storyDesc, storyImage, storyId;

    public StoryModel(String storyTitle, String storyDesc, String storyImage, String storyId) {
        this.storyTitle = storyTitle;
        this.storyDesc = storyDesc;
        this.storyImage = storyImage;
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryDesc() {
        return storyDesc;
    }

    public void setStoryDesc(String storyDesc) {
        this.storyDesc = storyDesc;
    }

    public String getStoryImage() {
        return storyImage;
    }

    public void setStoryImage(String storyImage) {
        this.storyImage = storyImage;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public StoryModel (){}
}
