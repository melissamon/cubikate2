package com.example.grupoc.cubikate;

import android.content.Intent;
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

public class frm_alta_maestros extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    RequestQueue rq;
    JsonRequest jrq;

    boolean sw = true; //Si se pulsa el boton Buscar es True, en caso contrario es falso.

    private EditText etRUT_maestro, etNombre2, etApellido2, etDireccion2, etEmail2, etTelefono2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_alta_maestros);

        etRUT_maestro = (EditText)findViewById(R.id.txtRUT2);
        etNombre2 = (EditText)findViewById(R.id.txtNombre2);
        etApellido2 = (EditText)findViewById(R.id.txtApellido2);
        etDireccion2 = (EditText)findViewById(R.id.txtDireccion2);
        etEmail2 = (EditText)findViewById(R.id.txtEmail2);
        etTelefono2 = (EditText)findViewById(R.id.txtTelefono2);

        rq = Volley.newRequestQueue(getApplicationContext());
    }

    public void btnBuscar_click(View view){
        BuscarMaestro();
    }

    public void btnRegistrarse_click(View view){ CorreoRegistrarse();}

    @Override
    public void onErrorResponse(VolleyError error) {
        if(sw) Toast.makeText(getApplicationContext(),"No se encontró el Maestro " + error.toString(),Toast.LENGTH_LONG).show();
        else {
            //Toast.makeText(getApplicationContext(), "No fue posible enviar el correo " + error.toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "¡ Revise su correo para continuar !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        AltaMaestro maestro = new AltaMaestro();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try{
            jsonObject = jsonArray.getJSONObject(0);

            if(sw) {


                maestro.setRUT_maestro(jsonObject.optString("RUT_maestro"));
                maestro.setNombres(jsonObject.optString("nombres"));
                maestro.setApellidos(jsonObject.optString("apellidos"));
                maestro.setDireccion(jsonObject.optString("direccion"));
                maestro.setTelefono(jsonObject.optString("telefono"));
                maestro.setEmail(jsonObject.optString("email"));

                //Toast.makeText(getApplicationContext(), "¡ Bienvenido " + maestro.getNames() + " !", Toast.LENGTH_SHORT).show();
                //llenar los controles

                //Limpio
                etNombre2.setText(maestro.getNombres());
                etApellido2.setText(maestro.getApellidos());
                etDireccion2.setText(maestro.getDireccion());
                //etEmail2.setText(maestro.getEmail()); Temporalmente lo desactivo
                etTelefono2.setText(maestro.getTelefono());
            }
            else
            {
                Toast.makeText(getApplicationContext(), "¡ Revise su correo para continuar !", Toast.LENGTH_SHORT).show();
            }
            //Asigno
            //etApellido2.getText() = (EditText)maestro.getApellidos();

        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    private void BuscarMaestro() {
        sw = true;
        String url = "https://uniacc.000webhostapp.com/cubikate/alta_maestros.php?RUT_maestro="+etRUT_maestro.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }

    //CorreoRegistrarse
    private void CorreoRegistrarse() {
        sw = false;
        String url = "https://uniacc.000webhostapp.com/cubikate/correo.php?email="+etEmail2.getText().toString()+"&RUT_maestro="+etRUT_maestro.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        rq.add(jrq);
    }

}
