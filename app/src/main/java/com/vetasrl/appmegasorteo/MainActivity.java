package com.vetasrl.appmegasorteo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    logInHelper lgHelper = new logInHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        setContentView(R.layout.activity_main);

        Context context = this.getApplicationContext();
        if(!lgHelper.checkLogIn(context)){
            Intent toLogin = new Intent(MainActivity.this,LogIn.class);
            startActivity(toLogin);
        }

        ImageButton noticiasBtn = (ImageButton)findViewById(R.id.noticiasBtn);
        ImageButton ganadoresBtn = (ImageButton)findViewById(R.id.ganadoresBtn);
        ImageButton envivoBtn = (ImageButton)findViewById(R.id.envivoBtn);
        ImageButton premiosBtn = (ImageButton)findViewById(R.id.premiosBtn);
        ImageButton redesBtn = (ImageButton)findViewById(R.id.redesBtn);
        ImageButton exit = (ImageButton)findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logOut(getApplicationContext());
            }
        });

        redesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Redes.class));
            }
        });

        noticiasBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Noticias.class));
            }
        });

        premiosBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Premios.class));
            }
        });

        envivoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EnVivo.class));
             }
        });

        ganadoresBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Ganadores.class));
            }
        });
    }

    public void logOut(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("AppMegaSorteo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("usr", "");
        editor.commit();
        startActivity(new Intent(MainActivity.this,LogIn.class));
        finish();
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
