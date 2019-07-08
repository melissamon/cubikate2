package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frmAltaUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_alta_usuario);
    }

    //Metodo para el boton clientes btnAceptar2
    public void btnAceptar_click(View view){
        Intent btnAceptar_click = new Intent(this, MainActivity.class);

        startActivity(btnAceptar_click);
    }
}
