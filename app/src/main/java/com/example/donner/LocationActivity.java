package com.example.donner;

import androidx.fragment.app.FragmentActivity;

import android.location.LocationRequest;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.donner.databinding.ActivityLocationBinding;

import java.util.Vector;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocationBinding binding;
    MarkerOptions marker;
    LatLng ctrLocation;
    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ctrLocation = new LatLng(-1.286389, 36.817223);

        markerOptions = new Vector<>();
        markerOptions.add(new MarkerOptions().title("parklands Hockey field")
                .position(new LatLng(1.2606459323111758 , 36.82733593696287))
                .snippet("Parkland Recross Donation Centre"));

        markerOptions.add(new MarkerOptions().title("Karen Langata")
                .position(new LatLng(1.3219694060948566, 36.70695216044942))
                .snippet("Donation Centre Langata"));

        markerOptions.add(new MarkerOptions().title("Opposite Machakos GK Prison")
                .position(new LatLng(1.5169552659092207, 37.261526584625244))
                .snippet("Machakos Donation Centre"));


//        marker = new MarkerOptions().title("Karen Langata")
//                .position(new LatLng(1.3219694060948566, 36.70695216044942))
//                .snippet("Donation Centre Langata");
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctrLocation, 9));

        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }
    }


}