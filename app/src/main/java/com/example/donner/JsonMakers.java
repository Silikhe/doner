package com.example.donner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonMakers {

    @GET("loadcare.php")
    Call<StationWrapper> getStations();
}
