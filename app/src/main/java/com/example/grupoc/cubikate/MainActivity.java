package com.example.grupoc.cubikate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo para el boton clientes btnCliente
    public void btnclientes_click(View view){
        Intent btnclientes_click = new Intent(this, frmAltaUsuario.class);

        startActivity(btnclientes_click);
    }

    //Metodo para el boton maestros btnMaestro
    public void btnMaestros_click(View view){
        Intent btnMaestros_click = new Intent(this, frm_alta_maestros.class);

        startActivity(btnMaestros_click);
    }

    //Metodo para el boton BuscarMAestro btnBuscarMaestro
    public void btnBuscarMaestro_click(View view){
        Intent btnBuscarMaestro_click = new Intent(this, frmBuscarMaestro.class);

        startActivity(btnBuscarMaestro_click);
    }


}
