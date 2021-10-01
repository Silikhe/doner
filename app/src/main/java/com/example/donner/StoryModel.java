package com.example.donner;

import android.os.Parcel;
import android.os.Parcelable;

public class StoryModel implements Parcelable {
    private String storyTitle, storyDesc, storyImage, storyId;

    public StoryModel(String storyId, String storyTitle, String storyDesc, String storyImage) {
        this.storyTitle = storyTitle;
        this.storyDesc = storyDesc;
        this.storyImage = storyImage;
        this.storyId = storyId;
    }

    protected StoryModel(Parcel in) {
        storyTitle = in.readString();
        storyDesc = in.readString();
        storyImage = in.readString();
        storyId = in.readString();
    }

    public static final Creator<StoryModel> CREATOR = new Creator<StoryModel>() {
        @Override
        public StoryModel createFromParcel(Parcel in) {
            return new StoryModel(in);
        }

        @Override
        public StoryModel[] newArray(int size) {
            return new StoryModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(storyTitle);
        parcel.writeString(storyDesc);
        parcel.writeString(storyImage);
        parcel.writeString(storyId);
    }
}
