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

    private EditText etUsuario, etContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText)findViewById(R.id.txtUsuario);
        etContrasenia = (EditText)findViewById(R.id.txtContrasenia);

        rq = Volley.newRequestQueue(getApplicationContext());

//        SharedPreferences spInicio = getSharedPreferences("dtCubikate", Context.MODE_PRIVATE);
//        etUsuario.setText(spInicio.getString("usuario", ""));
//        etContrasenia.setText(spInicio.getString("contrasenia", ""));


    }

    public void btnIngresarIngresar_click(View view){
        iniciarSesion();
    }


    //Metodo para el boton clientes btnCliente
    public void btnIngresarCliente_click(View view){
        Intent btnIngresarCliente_click = new Intent(this, frmAltaUsuario.class);
        startActivity(btnIngresarCliente_click);
    }

    //Metodo para el boton clientes btnAceptar2
    public void btnIngresarMaestro_click(View view){
        Intent btnIngresarMaestro_click = new Intent(this, frm_alta_maestros.class);
        startActivity(btnIngresarMaestro_click);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se encontró el usuario "+error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        Toast.makeText(getApplicationContext(),"Se ha encontrado el usuario "+etUsuario.getText().toString(),Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try{
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setPwd(jsonObject.optString("user"));
            usuario.setPwd(jsonObject.getString("owd"));
            usuario.setNames(jsonObject.optString("names"));

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    private void iniciarSesion() {
        String url = "https://uniacc.000webhostapp.com/cubikate/sesion.php?user="+etUsuario.getText().toString()+"&pwd="+etContrasenia.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }

}
