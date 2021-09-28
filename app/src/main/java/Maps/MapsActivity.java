//package Maps;
//
//import android.content.Context;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.maps.GoogleMap;
//
//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.GsonConverterFactory;
//import retrofit.Response;
//import retrofit.Retrofit;
//
//public class MapsActivity {
//    private static final int PROXIMITY_RADIUS = 12;
//    Context context;
//    private boolean CheckGooglePlayServices() {
//        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
//        int result = googleAPI.isGooglePlayServicesAvailable(context);
//        if(result != ConnectionResult.SUCCESS) {
////            if(googleAPI.isUserResolvableError(result)) {
////                googleAPI.getErrorDialog(this, result,
////                        0).show();
////            }
////            return false;
//        }
//        return true;
//    }
//
//    private void build_retrofit_and_get_response(String type) {
//
//        String url = "https://maps.googleapis.com/maps/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitMaps service = retrofit.create(RetrofitMaps.class);
//
//        String latitude;
//        String longitude;
//        Call<Example> call = service.getNearbyPlaces(type, latitude + "," + longitude, PROXIMITY_RADIUS);
//
//        call.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Response<Example> response, Retrofit retrofit) {
//
//                try {
//                    GoogleMap mMap;
//                    mMap.clear();
//                    // This loop will go through all the results and add marker on each location.
//                    for (int i = 0; i < response.body().getResults().size(); i++) {
//                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
//                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
//                        String placeName = response.body().getResults().get(i).getName();
//                        String vicinity = response.body().getResults().get(i).getVicinity();
//                        MarkerOptions markerOptions = new MarkerOptions();
//                        LatLng latLng = new LatLng(lat, lng);
//                        // Position of Marker on Map
//                        markerOptions.position(latLng);
//                        // Adding Title to the Marker
//                        markerOptions.title(placeName + " : " + vicinity);
//                        // Adding Marker to the Camera.
//                        Marker m = mMap.addMarker(markerOptions);
//                        // Adding colour to the marker
//                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                        // move map camera
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//                    }
//                } catch (Exception e) {
//                    Log.d("onResponse", "There is an error");
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("onFailure", t.toString());
//            }
//        });
//    }
//
//}
