package com.example.grupoc.cubikate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_user);

        TextView especialidad = (TextView) findViewById(R.id.tv_Especialidad);
        TextView necesidad = (TextView) findViewById(R.id.tv_Necesidad);
        EditText input_Necesidad = (EditText) findViewById(R.id.et_Necesidad);
        Spinner selEspecialidad = (Spinner) findViewById(R.id.sp_Especialidad);


        getJSON("https://uniacc.000webhostapp.com/cubikate/setServiceRequest.php");

    }//end onCreate

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

