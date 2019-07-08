package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frm_alta_maestros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_alta_maestros);
    }

    //Metodo para el boton clientes btnAceptar2
    public void btnAceptar2_click(View view){
        Intent btnAceptar2_click = new Intent(this, MainActivity.class);

        startActivity(btnAceptar2_click);
    }

}
