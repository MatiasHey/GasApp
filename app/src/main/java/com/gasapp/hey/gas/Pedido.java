package com.gasapp.hey.gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by tinoper on 27/04/15.
 */
public class Pedido extends ActionBarActivity {

    private Button btnPedido;
    public static String TAG="GasApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realizar_pedido);

        btnPedido=(Button)findViewById(R.id.btnPedido);

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Entro al boton..");
                Intent goTOMisDirecciones = new Intent(Pedido.this, ConfirmacionPedido.class);
                startActivity(goTOMisDirecciones);
            }
        });


        //TODO si se pone toda la info del pedido en MainActivity esta clase se puede borrar
    }



}
