package com.example.grupoc.cubikate;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class frmBuscarCliente extends AppCompatActivity {
    RequestQueue rq;
    JsonRequest jrq;

    ListView listaResultado;

    private String sIDmaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar_cliente);

        listaResultado = (ListView)findViewById(R.id.lstCliente);

        rq = Volley.newRequestQueue(getApplicationContext());

        Bundle parametros = this.getIntent().getExtras();

        if(parametros !=null) {
            sIDmaestro = parametros.getString("IDmaestro");
        }

        iniciarSesion();
    }

    public void CargarListView(JSONArray ja){

        final ArrayList<String> lista = new ArrayList<>();
        final ArrayList<String> lista2 = new ArrayList<>();
        final ArrayList<String> listaLat = new ArrayList<>();
        final ArrayList<String> listaLon = new ArrayList<>();

        //for(int i=0;i<ja.length();i+=4){
        for(int i=0;i<ja.length();i+=4){

            try {
                //lista.add(ja.getString(i)+" "+ja.getString(i+1)+" "+ja.getString(i+2)+" "+ja.getString(i+3));
                //lista.add(ja.getString(i)+" "+ja.getString(i+1));
                lista2.add(ja.getString(i));
                lista.add(ja.getString(i+1));
                listaLat.add(ja.getString(i+2));
                listaLon.add(ja.getString(i+3));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listaResultado.setAdapter(adaptador);

        listaResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(frmBuscarCliente.this, frmContactarCliente.class);
                intent.putExtra("IDcliente",lista2.get(i));
                intent.putExtra("Nombre",lista.get(i));
                intent.putExtra("c_latitud",listaLat.get(i));
                intent.putExtra("c_longitud",listaLon.get(i));
                intent.putExtra("IDmaestro",sIDmaestro);



                startActivity(intent);

            }
        });



    }



    private void iniciarSesion() {
        String URL;

        URL = "https://uniacc.000webhostapp.com/cubikate/buscar_cliente.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                //Toast.makeText(getApplicationContext(), "Ubicación Marcada", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            //protected Map<String, String> getParams() throws AuthFailureError {
            //    Map<String,String> parametros=new HashMap<String, String>();
            //    parametros.put("RUT",RUT);
            //    parametros.put("longitud",lon);
            //    parametros.put("latitud",lat);

             //   return parametros;
            //}
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
