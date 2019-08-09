package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

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

    public void btnMarcarUbicacionMaestro_click(View view){
        MarcarUbicacionMaestro();
    }
    public void btnBuscarCliente_click(View view){
        BuscarCliente();
    }
    public void btnEvaluarCliente_click(View view){
        EvaluarCliente();
    }

    private void MarcarUbicacionMaestro() {
        //Leer del GPS del maestro

        //Gardar en la BD la ubicacion del maestro


    }

    private void BuscarCliente() {
        Intent btnBuscarCliente_click = new Intent(this, frmBuscarCliente.class);
        startActivity(btnBuscarCliente_click);
    }

    private void EvaluarCliente() {
        Intent btnEvaluarCliente_click = new Intent(this, frmEvaluarCliente.class);
        startActivity(btnEvaluarCliente_click);
    }
}
