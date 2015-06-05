package com.gasapp.hey.gas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by tinoper on 27/04/15.
 */
public class Historial extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_pedidos);
        //TODO ejecutar consulta de pedidos pero directo al servidor sin pasar por la info local
    }


}
