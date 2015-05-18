package com.gasapp.hey.gas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by tinoper on 27/04/15.
 */
public class ConfirmacionPedido extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_pedido);
        //TODO Mas allá de mostrar la confirmación requiere un ok de la contraparte? O solo queda pendiente la notificacion
    }

}
