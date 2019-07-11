package com.example.grupoc.cubikate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etContrasenia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        etUsuario = (EditText)findViewById(R.id.txtUsuario);
//        etContrasenia = (EditText)findViewById(R.id.txtContrasenia);

//        SharedPreferences spInicio = getSharedPreferences("dtCubikate", Context.MODE_PRIVATE);
//        etUsuario.setText(spInicio.getString("usuario", ""));
//        etContrasenia.setText(spInicio.getString("contrasenia", ""));


    }

    public void btnIngresarIngresar_click(View view){

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


}
