package com.example.grupoc.cubikate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class frmMaestros extends AppCompatActivity {

    private TextView etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_maestros);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String nombre = parametros.getString("nombre");
            etNombre = (TextView)findViewById(R.id.lblNombreMaestro);

            etNombre.setText("¡ Bienvenido " + nombre + " !");
        }

    }
}
