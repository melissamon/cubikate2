package com.example.grupoc.cubikate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;

    boolean sw = true; //si es true el modulo se comporta como cliente en caso contrario es maestro.
    //public static final String nombres = "names";

    private EditText etUsuario, etContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText)findViewById(R.id.txtUsuario);
        etContrasenia = (EditText)findViewById(R.id.txtContrasenia);

        rq = Volley.newRequestQueue(getApplicationContext());
    }

    public void btnIngresarIngresar_click(View view){
        iniciarSesion();
    }
    public void btnIngresarIngresarMaestro_click(View view){
        iniciarSesionMaestro();
    }

    //Metodo para el boton clientes btnCliente
    public void btnIngresarCliente_click(View view){
        Intent btnIngresarCliente_click = new Intent(this, frmAltaUsuario.class);
        startActivity(btnIngresarCliente_click);
    }

    //Metodo para el boton de maestros btnIngresarMaestro
    public void btnIngresarMaestro_click(View view){
        Intent btnIngresarMaestro_click = new Intent(this, frm_alta_maestros.class);
        startActivity(btnIngresarMaestro_click);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if(sw) Toast.makeText(getApplicationContext(),"No se encontró el Usuario " + error.toString(),Toast.LENGTH_LONG).show();
        else Toast.makeText(getApplicationContext(),"No se encontró el Maestro " + error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        //Toast.makeText(getApplicationContext(),"Se ha encontrado el usuario "+etUsuario.getText().toString(),Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try{
            jsonObject = jsonArray.getJSONObject(0);

            if(sw) {
                usuario.setUser(jsonObject.optString("usuario"));
                usuario.setPwd(jsonObject.getString("clave"));
                usuario.setNames(jsonObject.optString("names"));

                Toast.makeText(getApplicationContext(), "¡ Bienvenido " + usuario.getNames() + " !", Toast.LENGTH_SHORT).show();
            }
            else
            {
                usuario.setUser(jsonObject.optString("RUT_maestro"));
                usuario.setPwd(jsonObject.getString("clave"));
                usuario.setNames(jsonObject.optString("names"));

                Toast.makeText(getApplicationContext(), "¡ Bienvenido " + usuario.getNames() + " !", Toast.LENGTH_SHORT).show();
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void iniciarSesion() {
        sw = true;
        String url = "https://uniacc.000webhostapp.com/cubikate/sesion.php?usuario="+etUsuario.getText().toString()+"&clave="+etContrasenia.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }

    private void iniciarSesionMaestro() {
        sw = false;
        String url = "https://uniacc.000webhostapp.com/cubikate/sesion_maestros.php?RUT_maestro="+etUsuario.getText().toString()+"&clave="+etContrasenia.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }
}
