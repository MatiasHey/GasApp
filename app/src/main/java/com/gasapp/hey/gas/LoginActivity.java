package com.gasapp.hey.gas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;


public class LoginActivity extends Activity {

    String TAG="DEMOLOGIN";
    public TextView tv1;
    public String login;
    public EditText userId,password;
    public Button sendLogin;

    private SharedPreferences prefs;
    private String prefName = "GasAppPrefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userId=(EditText)findViewById(R.id.editUserID);
        password=(EditText)findViewById(R.id.editPassword);
        sendLogin=(Button)findViewById(R.id.buttonSendLogin);


        tv1=(TextView)findViewById(R.id.loginError);
        tv1.setText("testing");



        //establecer en el listener del boton de login
        //Controlar que usuario y password esten ingresados y sino mostrar cartel de que faltan
        //Si ambos campos fueron ingresados entonces si ejecutar un login

        sendLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Enter Username",
                            Toast.LENGTH_SHORT).show();
                }else if (password.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Enter Password",
                            Toast.LENGTH_SHORT).show();
                }else{
                    DoLOGINPOST mDoLOGINPOST = new DoLOGINPOST(getApplicationContext(),userId.getText().toString(),password.getText().toString());
                    mDoLOGINPOST.execute("");
                    Log.v(TAG,"segun este valor largar nueva actividad o no"+login);

                }

            }
        });

        //TODO prever que se hacer en el caso de reset password
        //TODO prever un boton de clear
        //TODO prever un boton de agregar


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


    public class DoLOGINPOST extends AsyncTask<String,Void,Boolean> {

        String TAG="DoLOGINPOST";
        Context mContext=null;
        public String strID;
        public String userToSearch,strUserToSearch,strUser;
        public String passwordToSearch,strPasswordToSearch;
        Exception exception=null;




        DoLOGINPOST(Context context, String userToSearch, String passwordToSearch){
            mContext=context;
            strUserToSearch = userToSearch;
            strPasswordToSearch=passwordToSearch;

        }


        @Override
        protected Boolean doInBackground(String... arg0) {

            try{

                //Setup the parameters
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("field1", strUserToSearch));
                nameValuePairs.add(new BasicNameValuePair("field2", strPasswordToSearch));
                Log.v(TAG, "usuario a consultar" + strUserToSearch + strPasswordToSearch);

                //Create the HTTP request
                HttpParams httpParameters = new BasicHttpParams();

                //Setup timeouts
                HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
                HttpConnectionParams.setSoTimeout(httpParameters, 15000);

                HttpClient httpclient = new DefaultHttpClient(httpParameters);
                HttpPost httppost = new HttpPost("http://www.responsivehelp.com/androidDevelopment/login.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();

                String result = EntityUtils.toString(entity);

                // Create a JSON object from the request response
                JSONObject jsonObject = new JSONObject(result);

                //Retrieve the data from the JSON object
                strID=jsonObject.getString("id");
                Log.v(TAG,"ID recuperado"+strID);
                strUser=jsonObject.getString("name");

                if (strID.toString().equals("Unknown")){
                    login="off";
                }
                else if (strID=="null") {
                    login="off";
                }else {
                    login = "on";
                }
                if (login=="on"){
                    //movernos a actividad principal
                    Intent main= new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                }else{
                    // proponer la misma actividad LoginActivity y mostrar un cartel de ingreso fallido;
                    Toast.makeText(getApplicationContext(), "Login fallido", Toast.LENGTH_LONG).show();
                    userId.setText("");
                    password.setText("");
                }

                    //TODO se pueden traer mas campos para aportar a la logica del usuario
                    //Por ejemplo ultimo acceso,o si debe cambiar la contraseña
                    //TODO capaz que guardar aca en SharedPreferences para usarla en el resto de la app
                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("id",strID);
                    editor.putString("user",strUser);
                    editor.commit();




            }catch (Exception e){
                Log.e(TAG, "Error:", e);
                exception = e;
            }

            if(exception != null){
                Log.e(TAG, exception.getMessage());
            }


            return true;
        }

        @Override
        protected void onPostExecute(Boolean valid){
            Log.v(TAG,login);
            tv1.setText("Logged");
            if (login!="on"){
                Toast.makeText(getApplicationContext(), "Login fallido", Toast.LENGTH_LONG).show();
                userId.setText("");
                password.setText("");

            }
            //TODO segun el valor de la variable mostrar una cosa u otra
            //TODO borrar el valor de las casillas





        }

    }



}
