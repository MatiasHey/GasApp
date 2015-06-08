package com.gasapp.hey.gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {

    private ImageButton btnPedido,btnMisDirecciones,btnHistorial,btnFormasDePago;
    public static String TAG="GasApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones);

        btnPedido=(ImageButton)findViewById(R.id.btPedido);
        btnMisDirecciones=(ImageButton)findViewById(R.id.btnMisDirecciones);
        btnHistorial=(ImageButton)findViewById(R.id.btnHistorial);
        btnFormasDePago=(ImageButton)findViewById(R.id.btnFormasDePago);

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Entro al boton..");
                Intent goTOPedido = new Intent(MainActivity.this, Pedido.class);
                startActivity(goTOPedido);
            }
        });

        btnMisDirecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Entro al boton..");
                Intent goTOMisDirecciones = new Intent(MainActivity.this, AgregarUsuario.class);
                startActivity(goTOMisDirecciones);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Entro al boton..");
                Intent goTOHistorial = new Intent(MainActivity.this, Historial.class);
                startActivity(goTOHistorial);
            }
        });

        btnFormasDePago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Entro al boton..");
                Intent goTOPago = new Intent(MainActivity.this, FormasPago.class);
                startActivity(goTOPago);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void verRegistroUsuario (View view){
        setContentView(R.layout.registro_inicial);
    }

    public void verRealizarPedido (View view){
        setContentView(R.layout.realizar_pedido);
    }

    public void verMenu (View view){
        setContentView(R.layout.menu_opciones);
    }

    public void realizarPedido (View view){
        setContentView(R.layout.confirmar_pedido);
    }

}
