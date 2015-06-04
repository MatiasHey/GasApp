package com.gasapp.hey.gas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;

/**
 * Created by tinoper on 27/04/15.
 */
public class Pedido extends ActionBarActivity {

    private ImageButton btnPedido;
    public static String TAG="GasApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realizar_pedido);

        btnPedido=(ImageButton)findViewById(R.id.btnPedido);

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Entro al boton..");
                Intent goTOMisDirecciones = new Intent(Pedido.this, ConfirmacionPedido.class);
                startActivity(goTOMisDirecciones);
            }
        });

        //Custom Spinner para desplegar las opciones del pedido.

        Spinner pedidoDireccion = (Spinner)findViewById(R.id.PedidoDir);
        Spinner pedidoPago = (Spinner)findViewById(R.id.PedidoPago);
        Spinner pedidoServ = (Spinner)findViewById(R.id.PedidoServ);
        Spinner pedidoCant = (Spinner)findViewById(R.id.PedidoCant);

        String[] dirList = getResources().getStringArray(R.array.dirList);
        String[] pagoList = getResources().getStringArray(R.array.pagoList);
        String[] servList = getResources().getStringArray(R.array.servicioList);
        String[] cantList = getResources().getStringArray(R.array.cantList);
        //pedidoDireccion.setAdapter(new MyCustomAdapter(Pedido.this, R.layout.row, dirList));

        class MyCustomAdapter extends ArrayAdapter<String>{

            String[] laLista;

            public MyCustomAdapter(Context context, int textViewResourceId,
                                   String[] objects) {
                super(context, textViewResourceId, objects);

                laLista = objects;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
// TODO Auto-generated method stub
                return getCustomView(position, convertView, parent);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
                return getCustomView(position, convertView, parent);
            }

            public View getCustomView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
//return super.getView(position, convertView, parent);

                LayoutInflater inflater=getLayoutInflater();
                View row=inflater.inflate(R.layout.row, parent, false);
                TextView label=(TextView)row.findViewById(R.id.pedido_opcion);
                label.setText(laLista[position]);

                ImageView icon=(ImageView)row.findViewById(R.id.icon);

                switch (laLista[position]){
                    case "Mi Casa":
                        icon.setImageResource(R.drawable.casa_ico);
                        break;
                    case "Mi Trabajo":
                        icon.setImageResource(R.drawable.trabajo_ico);
                        break;
                    case "Efectivo":
                        icon.setImageResource(R.drawable.cash_ico);
                        break;
                    case "Tarjeta":
                        icon.setImageResource(R.drawable.cred_ico);
                        break;
                    case "Otro":
                        icon.setImageResource(R.drawable.pago_ico);
                        break;
                    case "Express 30\'":
                        icon.setImageResource(R.drawable.express_ico);
                        break;
                    case "Normal":
                        icon.setImageResource(R.drawable.delivery_ico);
                        break;
                    case "Semanal":
                        icon.setImageResource(R.drawable.semanal_ico);
                        break;
                    default:
                        icon.setImageResource(R.drawable.garrafa_ico);
                        break;
                }

                return row;
            }
        }

        pedidoDireccion.setAdapter(new MyCustomAdapter(Pedido.this, R.layout.row, dirList));
        pedidoPago.setAdapter(new MyCustomAdapter(Pedido.this, R.layout.row, pagoList));
        pedidoServ.setAdapter(new MyCustomAdapter(Pedido.this, R.layout.row, servList));
        pedidoCant.setAdapter(new MyCustomAdapter(Pedido.this, R.layout.row, cantList));


        //TODO si se pone toda la info del pedido en MainActivity esta clase se puede borrar
    }



}
