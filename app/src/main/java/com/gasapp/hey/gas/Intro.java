package com.gasapp.hey.gas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tinoper on 27/04/15.
 */
public class Intro extends ActionBarActivity{

    public static String TAG="GasApp";
    private SharedPreferences prefs;
    private String prefName = "GasAppPrefs";
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        //check network connection
        //If works go to MainActivity
        //If not display a message and finish();
        if (isNetworkOnline()) {
            Log.v(TAG, "Conexión a internet existente");

            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            id = prefs.getString("id", "noID");
            /*if (id == "noID") {
                Log.v(TAG, "no se encontro Usuario registrado");
                // ir a actividad de registro
                Intent goToRegistroUsuario = new Intent(Intro.this, AgregarUsuario.class);
                startActivity(goToRegistroUsuario);

            } else {
                Log.v(TAG, "Usuario registrado numero: " + id);
                // ir a actividad principal
                Intent goTOMainActivity = new Intent(Intro.this, MainActivity.class);
                startActivity(goTOMainActivity);

            }*/
            Intent goTOMainActivity = new Intent(Intro.this, MainActivity.class);
            startActivity(goTOMainActivity);
        } else {
            Log.v(TAG, "Sin conexión a internet");
            Toast.makeText(this, "sin conexion", Toast.LENGTH_LONG).show();

            //finish();

        }
    }

    public boolean isNetworkOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

    }

}
