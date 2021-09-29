package com.example.donner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StationWrapper {
    @SerializedName("markers")
    private List<Stations> markers;

    public List<Stations> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Stations> markers) {
        this.markers = markers;
    }
}
