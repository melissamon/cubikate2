package com.example.grupoc.cubikate;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class vMapsMaestros extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_maps_maestros);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        double lat, lon;



        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        //this.lat = mMap.getArguments().getDouble("lat");
        //this.lon = getArguments().getDouble("lon");


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);

        }
        //mMap.setMyLocationEnabled(true);

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

       // if(ubicacion != null) {
       //     Log.d("Latitud", String.valueOf(loc.getLatitude()));
       //     Log.d("longitud", String.valueOf(loc.getLongitude()));
        //}
        float zoom = 17;


        // Add a marker in Sydney and move the camera
        //LatLng latLng = new LatLng(lat,lon);
        LatLng chile = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(chile).title("Ciudad de Chile").snippet("en este lugar tiene que estar el nombre del electrico").icon(BitmapDescriptorFactory.fromResource(R.drawable.oil_and_gas_well)));

//        LatLng mexico = new LatLng(19.4326077, -99.13320799999997);
//        mMap.addMarker(new MarkerOptions().position(mexico).title("Ciudad de mexico").snippet("shl hlkjh lkjh lkjh lkjhlkjhljh").icon(BitmapDescriptorFactory.fromResource(R.drawable.oil_and_gas_well)));


//        LatLng mexico2 = new LatLng(19.1950964, -100.13267250000001);
//        mMap.addMarker(new MarkerOptions().position(mexico2).title("Ciudad de mexico").snippet("shl hlkjh lkjh lkjh lkjhlkjhljh").icon(BitmapDescriptorFactory.fromResource(R.drawable.bighouse)));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico,7));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chile,zoom));


/*
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        UiSettings settings = googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
  */



    }
}
