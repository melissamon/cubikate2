package com.example.grupoc.cubikate;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainUserActivity extends AppCompatActivity implements LocationListener {

    public static String urlServer = "https://uniacc.000webhostapp.com/cubikate/searchServices.php?";

    public String esp = new String();
    public String req = new String();
    public Double longitud;
    public Double latitud;
    private String ubi;
    private LocationManager locationManager;
    private Button search;
    private EditText input_Necesidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_user);

        TextView especialidad = (TextView) findViewById(R.id.tv_Especialidad);
        TextView necesidad = (TextView) findViewById(R.id.tv_Necesidad);
        input_Necesidad = (EditText) findViewById(R.id.et_Necesidad);

        Spinner selEspecialidad = (Spinner) findViewById(R.id.sp_Especialidad);
        //Creo Array para almacenar valor y tomo el array de "Especialidad" que definí en STRINGS => Mucho más simple de gestionar para cambios e incorporación
        //de nuevas especialidades a futuro
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mainuser_Especialidad, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selEspecialidad.setAdapter(adapter);

        selEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Traigo la especialidad seleccionada
                esp = adapterView.getItemAtPosition(i).toString();
                //Depuración

            }//end onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }//end onNothingSelected

        });//end setOnItemSelectedListener

        //verificar Permisos de la aplicación

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //El usuario no ha especificado los persmisos: Se solicitan
            ActivityCompat.requestPermissions(this,new String[]{

                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION

            },1000); //end requestPermission()

        } else {

            //Error al obtener permisos

        }//end if-else

        //Obtener Ubicacion: Latitud y Longitud
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria(); //Me permite definir un criterio para seleccionar al mejor proveedor de ubicacion

        ubi = locationManager.getBestProvider(criteria, false);

        Location location = locationManager.getLastKnownLocation(ubi);
        locationManager.requestLocationUpdates(ubi, 15000, 1, this);

        if (location != null) {

            onLocationChanged(location);

        } else {

            Toast.makeText(getBaseContext(), "Error al Obtener Ubicación => Linea 80;MainUserActivity", Toast.LENGTH_SHORT).show();

        }//end if-else


        this.search = (Button) findViewById(R.id.bt_Buscar);
        this.search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //AQUI CODIGO
                //Generar las variables para confecciones URL

                String url_temporal = new String();
                url_temporal = urlServer+"esp="+esp+"?req="+input_Necesidad.getText()+"?lat="+latitud+"?long="+longitud;
                Log.d("URL TEMPORAL:", url_temporal);

                //Llamada al JSON entregando parametros del usuario
                getJSON(url_temporal);

            }//end onClick

        });//end setOnClickListener

    }//end onCreate

    @Override
    public void onLocationChanged(Location location) {

        longitud = location.getLongitude();
        latitud = location.getLatitude();
        Log.d("Longitud:", longitud.toString());
        Log.d("Latitud:", latitud.toString());

    }//end onLocationChanged()

    @Override

    public void onStatusChanged(String s, int i, Bundle bundle) {

    }//end onstatusChanged

    @Override

    public void onProviderEnabled(String s) {

    }//end onProviderEnabled

    @Override

    public void onProviderDisabled(String s) {

    }//end onProviderDisabled

    // Metodo getJSON
    public void getJSON(final String urlCubikateServer) {

        //Clase GetJson
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute(){

                super.onPreExecute();

            }//end onPreExecution()


            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                //Toast.makeText(this.getApplicationContext(), s, Toast.LENGTH_SHORT).show();

            }//end onPostExecute()

            @Override
            protected String doInBackground(Void... voids) {

                try {

                    URL url = new URL(urlCubikateServer);
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while ((json=bufferedReader.readLine()) != null) {

                        sb.append(json +"\n");

                    }//end while

                    return sb.toString().trim();

                } catch (Exception e) {

                    return null;

                }//end try-cath

            }//end doInBackgroud

        }//end class GetJSON

        //Obtiene JSON desde URL en servidor
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }//end method getJSON

}//end MainUserActivity

