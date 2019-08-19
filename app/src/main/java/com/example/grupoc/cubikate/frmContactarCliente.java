package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class frmContactarCliente extends AppCompatActivity {

    private String sIDclien;
    private String sIDmaestro;

    private String sLatitud;
    private String sLongitud;

    private String nombre;

    private TextView etNombre;


    public void btnBuscarMapa_click(View view){
        BuscarMapa();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_contactar_cliente);

        etNombre = (TextView)findViewById(R.id.lblNombreCliente);

        Bundle parametros = this.getIntent().getExtras();

        if(parametros !=null) {
            sIDclien = parametros.getString("IDcliente");
            sIDmaestro = parametros.getString("IDmaestro");
            sLatitud = parametros.getString("c_latitud");
            sLongitud = parametros.getString("c_longitud");


            nombre = parametros.getString("Nombre");
            etNombre.setText(nombre);
            //Toast.makeText(getApplicationContext(), sIDmaestro, Toast.LENGTH_LONG).show();
        }

        //Item = getIntent().getSerializableExtra("objetoData");
    }

    private void BuscarMapa() {
        Intent intent = new Intent(this, vMapsMaestros.class);
        intent.putExtra("c_nombre",nombre);
        intent.putExtra("c_latitud",sLatitud);
        intent.putExtra("c_longitud",sLongitud);

        startActivity(intent);
    }

}
