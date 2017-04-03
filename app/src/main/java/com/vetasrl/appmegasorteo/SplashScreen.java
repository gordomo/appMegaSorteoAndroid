package com.vetasrl.appmegasorteo;

/**
 * Created by mmori on 31/1/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen  extends Activity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        final logInHelper lgHelper = new logInHelper();

        //preparo el intent para ir al MainActivity
        intent = new Intent(SplashScreen.this,LogIn.class);

        //chequeo si ya estamos logueados para crear el intent hacia el main
        if(lgHelper.checkLogIn(getApplicationContext())) {
            intent = new Intent(SplashScreen.this,MainActivity.class);
        }

        //sino, espero 3 segundos y voy al intent correspondiente
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    startActivity(intent);
                }
            }
        };

        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
