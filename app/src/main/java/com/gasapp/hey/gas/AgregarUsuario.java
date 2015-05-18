package com.gasapp.hey.gas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinoper on 11/05/15.
 */
public class AgregarUsuario extends ActionBarActivity {

    String TAG="GasApp";
    private SharedPreferences prefs;
    private String prefName = "GasAppPrefs";
    Button siguiente;
    EditText id,uname;
    InputStream is=null;
    String line=null;
    String result=null;
    public List<String> ListID;
    int code, count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_inicial);

        getIDPOST mgetIDPOST = new getIDPOST(null,"whatever");
        mgetIDPOST.execute("");

        siguiente = (Button) findViewById(R.id.btnRegSiguiente);
        id= (EditText) findViewById(R.id.etRegTel);
        uname = (EditText) findViewById(R.id.etRegNombre);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(uname.getText().toString().equals(""))
                    Toast.makeText(getBaseContext(), "Digite su nombre",
                            Toast.LENGTH_SHORT).show();

                else if(id.getText().toString().equals(""))
                    Toast.makeText(getBaseContext(), "Digite su numero de movil",
                            Toast.LENGTH_SHORT).show();


                else {

                    count = 0;

                    for(int i=0; i<ListID.size(); i++) {
                        //TODO borrar f(ListID.get(i).toString().equals(id.getText().toString())) {
                        if(ListID.get(i).equals(id.getText().toString())) {

                            count ++;
                        }
                    }

                    if(count != 0) {

                        Toast.makeText(getBaseContext(), "This ID is already " +
                                "taken by Someone", Toast.LENGTH_LONG).show();
                        id.setText("");
                    }
                    else {



                            addUserPOST mAddUserPOST = new addUserPOST(null,id.getText().toString(),uname.getText().toString(),id.getText().toString());
                            mAddUserPOST.execute("");


                    }
                }


            }
        });

    }


    public class getIDPOST extends AsyncTask<String,Void,List<String>> {
        String TAG="getIDPOST";
        Context mContext=null;
        public String strUserToSearch;
        public String temp;

        getIDPOST(Context context, String userToSearch){
            mContext=context;
            strUserToSearch = userToSearch;

        }

        @Override
        protected List<String> doInBackground(String... arg0) {
            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                Log.v(TAG, "httpClient ejecutado");
                HttpPost httppost = new HttpPost("http://www.responsivehelp.com/androidDevelopment/GetID.php");
                Log.v(TAG,"httppost ejecutado");
                HttpResponse response = httpclient.execute(httppost);
                Log.v(TAG,"response ejecutado");
                HttpEntity entity = response.getEntity();
                //is pasa a contener el resultado de la consulta sin formato legible
                is = entity.getContent();
            }
            catch(Exception e)
            {
                Log.e("Fail 1", e.toString());
                Toast.makeText(getApplicationContext(), "Invalid IP Address GET ID",
                        Toast.LENGTH_LONG).show();
            }

            try
            {
                //Se construye un StringBuilder para obtener los datos en un formato legible devolviendo el String result
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
            }
            catch(Exception e)
            {
                Log.e("Fail 2", e.toString());
            }

            try
            {
                //instanciamos JSONArray con el resultado en String
                JSONArray jarray = new JSONArray(result);
                //instanciamos JSONObject pero sin contenido
                JSONObject jobject = null;

                //instanciamos List<String>
                ListID = new ArrayList<String>();

                //recorriendo el jarray q contiene el resultado lo pasamos a formato jobject para poder ponerlo en la List<Sting>
                //pero solo del campo con nombre id
                for(int i=0; i<jarray.length(); i++) {

                    jobject = jarray.getJSONObject(i);
                    ListID.add(jobject.getString("id"));
                    Log.v(TAG,"Elemento 1 de lista : "+ListID.get(i).toString());
                }
                temp=ListID.get(0).toString();
            }
            catch(Exception e)
            {
                Log.e("Fail 3", e.toString());
            }


            return ListID;
        }

    }

    public class addUserPOST extends AsyncTask<String,Void,Boolean>{

        Context mContext=null;
        public String strID,strUname,strPwd;
        Exception exception=null;
        public String flag;

        addUserPOST(Context context,String idToAdd,String userToAdd,String pwdToAdd){
            mContext=context;
            strID=idToAdd;
            strUname=userToAdd;
            //strPwd=pwdToAdd;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("id", id.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("name", uname.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("pass", "password"));

            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.responsivehelp.com/androidDevelopment/add.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }
            catch(Exception e)
            {
                Log.e("Fail 1", e.toString());
                Toast.makeText(getApplicationContext(), "Invalid IP Address",
                        Toast.LENGTH_LONG).show();
            }

            try
            {
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
            }
            catch(Exception e)
            {
                Log.e("Fail 2", e.toString());
            }

            try
            {
                JSONObject jobject = new JSONObject(result);
                code=(jobject.getInt("code"));

                if(code==1)
                {
                    Toast.makeText(getBaseContext(), "Added Successfully",
                            Toast.LENGTH_SHORT).show();

                    flag="Added";


                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    //---save the values in the EditText view to preferences---
                    editor.putString("id", id.getText().toString());
                    editor.putString("name", uname.getText().toString());

                    //---saves the values---
                    editor.commit();
                    //TODO mensaje avisando de que se registró el usuario
                   /* Intent i = new Intent(AddMe.this, MainActivity.class);
                    startActivity(i);*/
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Sorry, Try Again",
                            Toast.LENGTH_LONG).show();
                }
            }
            catch(Exception e)
            {
                Log.e("Fail 3", e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean valid){
           /* if (flag.equals("Added")){
                Log.v(TAG,"Usuario Agregado onPOST");
            }*/
            Log.v("DEMOLOGIN","Usuario Agregado onPOST"+flag);
            Intent i = new Intent(AgregarUsuario.this, MainActivity.class);
            startActivity(i);
        }
    }




}
