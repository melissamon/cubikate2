package com.example.grupoc.cubikate;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class frmMaestros extends AppCompatActivity{
    RequestQueue rq;
    JsonRequest jrq;


    private GoogleMap mMap;
    private LocationManager ubicacion;

    private TextView etNombre;
    private String RUT;
    private String lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_maestros);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String nombre = parametros.getString("nombre");
            RUT = parametros.getString("RUT");

            etNombre = (TextView)findViewById(R.id.lblNombreMaestro);

            etNombre.setText("¡ Bienvenido " + nombre + " !");
        }
    }

    public void btnMarcarUbicacionMaestro_click(View view){
        MarcarUbicacionMaestro();
    }
    public void btnBuscarCliente_click(View view){
        BuscarCliente();
    }
    public void btnEvaluarCliente_click(View view){
        EvaluarCliente();
    }

    private void MarcarUbicacionMaestro() {
        String URL;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Guardar en la BD la ubicacion del maestro
        lat = String.valueOf(loc.getLatitude());
        lon = String.valueOf(loc.getLongitude());

        URL = "https://uniacc.000webhostapp.com/cubikate/marcar_ubicacion_maestro.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Ubicación Marcada", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("RUT",RUT);
                parametros.put("longitud",lon);
                parametros.put("latitud",lat);

                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private void BuscarCliente() {
        //Intent btnBuscarCliente_click = new Intent(this, frmBuscarCliente.class);
        //Intent btnBuscarCliente_click = new Intent(this, vMapsMaestros.class);
        Intent btnBuscarCliente_click = new Intent(this, frmBuscarCliente.class);
        startActivity(btnBuscarCliente_click);
    }

    private void EvaluarCliente() {
        Intent btnEvaluarCliente_click = new Intent(this, frmEvaluarCliente.class);
        startActivity(btnEvaluarCliente_click);
    }

}
