package com.example.neshanak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.mapbox.android.core.location.LocationEngineListener;

import ir.map.sdk_map.annotations.Marker;
import ir.map.sdk_map.annotations.MarkerOptions;
import ir.map.sdk_map.annotations.PolygonOptions;
import ir.map.sdk_map.annotations.Polyline;
import ir.map.sdk_map.annotations.PolylineOptions;
import ir.map.sdk_map.camera.CameraUpdateFactory;
import ir.map.sdk_map.geometry.LatLng;
import ir.map.sdk_map.location.LocationComponent;
import ir.map.sdk_map.maps.MapirMap;
import ir.map.sdk_map.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements LocationEngineListener {

    private SupportMapFragment supportMapFragment;
    private MapirMap mapirMap;
    private Marker CheckpointMarker;
    private LatLng SADRA = new LatLng(29.812218, 52.480531);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find fragment using support Fragment Manager...
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMapView);
        // Check User Location
        currentUserLocation();

        supportMapFragment.getMapAsync(mapirMap1 -> {
            mapirMap.setOnMapClickListener(new MapirMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng point) {

                    LocationComponent component = MainActivity.this.mapirMap.getLocationComponent();
                    LatLng userLocation = new LatLng(component.getLastKnownLocation().getLatitude(), component.getLastKnownLocation().getLongitude());
                    MasirYabi(point, userLocation);
                }
            });
        });
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            LatLng changeLocationLatLog = new LatLng(location.getLatitude(), location.getLongitude());
            mapirMap.animateCamera(CameraUpdateFactory.newLatLngZoom(changeLocationLatLog, 14));
        }
    }

    // get current user location and Check Permissions
    private void currentUserLocation() {
        supportMapFragment.getMapAsync(mapirMap -> {
            MainActivity.this.mapirMap = mapirMap;
            if (MainActivity.this.mapirMap != null) {
                // Check permission allow or die
                // Request Permission
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                else if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                // end Request :) all permission exits :)
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    LocationComponent component = MainActivity.this.mapirMap.getLocationComponent();
                    component.activateLocationComponent(MainActivity.this);
                    component.setLocationComponentEnabled(true);
                    if (component.getLocationEngine() != null) {
                        component.getLocationEngine().addLocationEngineListener(MainActivity.this);
                    }
                    if (component.getLastKnownLocation() != null) {
                        LatLng UserLocation = new LatLng(component.getLastKnownLocation().getLatitude(), component.getLastKnownLocation().getLongitude());
                        mapirMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UserLocation, 10));
                    }
                }
            }
        });
    }

    private void MasirYabi(LatLng point, LatLng userLocation) {
        MainActivity.this.mapirMap = mapirMap;
        supportMapFragment.getMapAsync(mapirMap1 -> {
            String driving_without_traffic = "https://map.ir/routes/route/v1/driving/" + point + "," + userLocation;


            StringRequest traffic_req = new StringRequest(Request.Method.GET, driving_without_traffic, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JsonObject object = new JsonObject(response);

                    } catch (Exception e) {

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
//                mapirMap.clear();
//                mapirMap.addPolyline(new PolylineOptions().add(userLocation, point).color(Color.RED).width(10));
//                mapirMap.addMarker(new MarkerOptions().position(point).setTitle("مقصدت اینجاست!"));
//                mapirMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
        });
    }
}
}