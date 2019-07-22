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

import org.json.JSONObject;

public class frmAltaUsuario extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;

    private EditText etUsuario, etRUT_Cliente, etNombres, etApellidos, etClave, etDireccion, etTelefono, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_alta_usuario);

        etUsuario = (EditText)findViewById(R.id.txtUsuario);
        etRUT_Cliente = (EditText)findViewById(R.id.txtRUT);
        etNombres = (EditText)findViewById(R.id.txtNombre);
        etApellidos = (EditText)findViewById(R.id.txtApellido);
        etClave = (EditText)findViewById(R.id.txtContrasenia);
        etDireccion = (EditText)findViewById(R.id.txtDireccion);
        etTelefono = (EditText)findViewById(R.id.txtTelefono);
        etEmail = (EditText)findViewById(R.id.txtEmail);

        rq = Volley.newRequestQueue(getApplicationContext());
    }






    //Metodo para el boton clientes btnAceptar2
    public void btnAceptar_click(View view){
        //Intent btnAceptar_click = new Intent(this, MainActivity.class);
        //startActivity(btnAceptar_click);

        registrar_usuario();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo registrar el usuario "+error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Se ha registrado el usuario " + etUsuario.getText().toString(), Toast.LENGTH_LONG).show();
        limpiarCajas();
    }


    void limpiarCajas() {

        etUsuario.setText("");
        etRUT_Cliente.setText("");
        etNombres.setText("");
        etApellidos.setText("");
        etClave.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etEmail.setText("");


        //txtNames.setText("");
        //txtUser.setText("");
        //txtPwd.setText("");
    }


    private void registrar_usuario() {

        //String url = "https://uniacc.000webhostapp.com/cubikate/alta_usuario.php?names=" +etUsuario.getText().toString()+"&user="+ txtUser.getText().toString() +
        //        "&pwd=" + txtPwd.getText().toString();

        String url = "https://uniacc.000webhostapp.com/cubikate/alta_usuario.php?usuario=" + etUsuario.getText().toString() + "&RUT_Cliente=" + etRUT_Cliente.getText().toString() +
                "&nombres=" + etNombres.getText().toString() + "&apellidos=" + etApellidos.getText().toString() +
                "&clave=" + etClave.getText().toString() + "&direccion=" + etDireccion.getText().toString() +
                "&telefono=" + etTelefono.getText().toString() + "&email=" + etEmail.getText().toString();

        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);


        //String url = "https://uniacc.000webhostapp.com/cubikate/sesion.php?usuario="+etUsuario.getText().toString()+"&clave="+etContrasenia.getText().toString();
        //jrq = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        //rq.add(jrq);
    }



}
