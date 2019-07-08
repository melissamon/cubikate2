package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frmBuscarMaestro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar_maestro);
    }

    //Metodo para el boton clientes btnAceptar2
    public void btnAceptar_click(View view){
        Intent btnAceptar_click = new Intent(this, MainActivity.class);

        startActivity(btnAceptar_click);
    }

}
