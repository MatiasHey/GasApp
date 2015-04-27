package com.gasapp.hey.gas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by tinoper on 27/04/15.
 */
public class Intro extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //TODO check network connection

    //TODO check if user was logged in
    //Si el usuario ya se registró no es necesario
}
