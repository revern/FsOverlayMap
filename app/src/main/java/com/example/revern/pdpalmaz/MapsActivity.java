package com.example.revern.pdpalmaz;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String FLATSTACK_SITE_URL = "http://flatstack.com";
    private static final LatLng FLATSTACK_OFFICE_COORDINATES =
            new LatLng(55.79350525111156, 49.125429913401604);
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FLATSTACK_OFFICE_COORDINATES, 19));

        Polygon fsPolygon = mMap.addPolygon(getFsBuildingOverlayOptions());
        fsPolygon.setClickable(true);
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(FLATSTACK_SITE_URL)));
            }
        });

    }

    private PolygonOptions getFsBuildingOverlayOptions() {
        return new PolygonOptions().add(
                new LatLng(55.793581906676995, 49.12524953484535),
                new LatLng(55.79344262690911, 49.125253893435),
                new LatLng(55.7934472972143, 49.12556502968073),
                new LatLng(55.793586315139066, 49.12555832415819))
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(65, 255, 0, 0));
    }

}
