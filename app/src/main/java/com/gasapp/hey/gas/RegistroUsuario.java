package com.gasapp.hey.gas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by tinoper on 02/05/15.
 */
public class RegistroUsuario extends ActionBarActivity {

    private SharedPreferences prefs;
    private String prefName = "GasAppPrefs";

    private String strID,strUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_inicial);


        //TODO pedir datos
            //Referencias a los controles
            strID="099666666";
            strUserName="Jon Doe";

        //TODO onclick del boton reset
        //vacias los controles reinicializados

        //TODO onclick del boton registro

            //TODO chequear que usuario no exista

        //TODO ingresar datos en base remota

        //TODO marcar la direccion ingresada como direccion por defecto

        //TODO guardar los datos del usuario en las preferencias
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id",strID);
        editor.putString("user",strUserName);
        editor.commit();

    }


}
