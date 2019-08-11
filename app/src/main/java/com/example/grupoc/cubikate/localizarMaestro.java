package com.example.grupoc.cubikate;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

public class localizarMaestro implements LocationListener {

    frmBuscarCliente frmbuscarcliente;
    TextView tvMensaje;

    public frmBuscarCliente getFrmbuscarcliente() {return  frmbuscarcliente;}

    public void setFrmbuscarcliente(frmBuscarCliente frmbuscarcliente, TextView tvMensaje){
        this.frmbuscarcliente = frmbuscarcliente;
        this.tvMensaje = tvMensaje;
    }


    @Override
    public void onLocationChanged(Location location) {
        //Este metodo se ejecuta cuando el GPS recibe nuevas coordenadas
        String texto = "Mi ubicación es: \n"
                + "Latitud = " + location.getLatitude() + "\n"
                + "Longitud = " + location.getLongitude();

        tvMensaje.setText(texto);

        mapa(location.getLatitude(), location.getLongitude());
    }

    public void mapa(double lat, double lon) {

        FragmentMaps fragment = new FragmentMaps();

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", new Double(lat));
        bundle.putDouble("lon", new Double(lon));
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFrmbuscarcliente().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment, null);
        fragmentTransaction.commit();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        switch (i){
            case LocationProvider.AVAILABLE:
                Log.d("debug","LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug","LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug","LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String s) {
        tvMensaje.setText("GPS Activado");
    }

    @Override
    public void onProviderDisabled(String s) {
        tvMensaje.setText("GPS Desactivado");
    }
}
