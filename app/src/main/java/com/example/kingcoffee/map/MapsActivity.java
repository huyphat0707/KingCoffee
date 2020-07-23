package com.example.kingcoffee.map;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.example.kingcoffee.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    public static int feacke = 1;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void onZoom(View view){
        if (view.getId() == R.id.Bzoomin){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.Bzoomout){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addLayerList = null;
        if (!location.equals(" ")) {
            Geocoder geocoder = new Geocoder(MapsActivity.this);
            try {
                addLayerList = geocoder.getFromLocationName(location, feacke);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < addLayerList.size(); i++) {
                Address address = addLayerList.get(i);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("FPT Polytechnich"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }

        }
    }
    public void chanType(View view){
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng devpro = new LatLng(16.075755, 108.169949);
        mMap.addMarker(new MarkerOptions().position(devpro).title("FPT Polytechnich"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(devpro , 15));
    }
}
