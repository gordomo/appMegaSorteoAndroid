package com.vetasrl.appmegasorteo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_FOR_INTERNET = 1;
    final logInHelper lgHelper = new logInHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {

                lgHelper.mostrarToast(getApplicationContext(), "Error: Necesitamos estos permisos para corroborar tu usuario");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_FOR_INTERNET );

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_FOR_INTERNET );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        final Button botonIngresar = (Button)findViewById(R.id.boton_ingresar);
        final Button botonRegistrarse = (Button)findViewById(R.id.boton_registrarse);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final ProgressBar progressBar4 = (ProgressBar)findViewById(R.id.progressBar4);

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText mail = (EditText)findViewById(R.id.mail);

                progressBar4.setVisibility(View.VISIBLE);
                botonIngresar.setEnabled(false);
                botonRegistrarse.setEnabled(false);

                final Context context = getApplicationContext();

                if(mail.getText().toString().matches(""))
                {
                    lgHelper.mostrarToast(context, "Complete todos los campos");
                    progressBar4.setVisibility(View.INVISIBLE);
                    botonIngresar.setEnabled(true);
                    botonRegistrarse.setEnabled(true);
                    return;
                }
                if(!mail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                    lgHelper.mostrarToast(context, "email no válido");
                    progressBar4.setVisibility(View.INVISIBLE);
                    botonIngresar.setEnabled(true);
                    botonRegistrarse.setEnabled(true);
                    return;
                }

                String url = lgHelper.getRemoteUrlForLogIn(mail.getText().toString());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject resp = new JSONObject(response);
                                    if (resp.getString("found") == "true") {
                                        final SharedPreferences sharedPref = context.getSharedPreferences("AppMegaSorteo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("usr", resp.getJSONObject("usr").toString());
                                        editor.commit();
                                        Intent intent = new Intent(LogIn.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        lgHelper.mostrarToast(context, "Usuario No Encontrado");
                                    }
                                    //resp.getJSONObject("usr").get("doc")
                                } catch (JSONException e) {
                                    lgHelper.mostrarToast(context, "Error en el servicio: " + e.getMessage());
                                }
                                progressBar4.setVisibility(View.INVISIBLE);
                                botonIngresar.setEnabled(true);
                                botonRegistrarse.setEnabled(true);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        lgHelper.mostrarToast(context, "Error: " + error);
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText mail = (EditText)findViewById(R.id.mail);

                progressBar4.setVisibility(View.VISIBLE);
                botonIngresar.setEnabled(false);
                botonRegistrarse.setEnabled(false);

                final Context context = getApplicationContext();

                if(mail.getText().toString().matches(""))
                {
                    lgHelper.mostrarToast(context, "Complete todos los campos");
                    progressBar4.setVisibility(View.INVISIBLE);
                    botonIngresar.setEnabled(true);
                    botonRegistrarse.setEnabled(true);
                    return;
                }
                if(!mail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                    lgHelper.mostrarToast(context, "email no válido");
                    progressBar4.setVisibility(View.INVISIBLE);
                    botonIngresar.setEnabled(true);
                    botonRegistrarse.setEnabled(true);
                    return;
                }
                String url = lgHelper.getRemoteUrlForRegistrarse(mail.getText().toString());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject resp = new JSONObject(response);
                                    if (resp.getString("result").matches("ok")) {
                                        final SharedPreferences sharedPref = context.getSharedPreferences("AppMegaSorteo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("usr", resp.getJSONObject("usr").toString());
                                        editor.commit();
                                        Intent intent = new Intent(LogIn.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        lgHelper.mostrarToast(context, "Error: " + resp.getString("message"));
                                    }
                                    //resp.getJSONObject("usr").get("doc")
                                } catch (JSONException e) {
                                    lgHelper.mostrarToast(context, "Error en el servicio: " + e.getMessage());
                                }
                                progressBar4.setVisibility(View.INVISIBLE);
                                botonIngresar.setEnabled(true);
                                botonRegistrarse.setEnabled(true);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        lgHelper.mostrarToast(context, "Error: " + error);
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FOR_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
//                    lgHelper.mostrarToast(getApplicationContext(), "permitido");

                } else {

                    lgHelper.mostrarToast(getApplicationContext(), "Lo sentimos, pero sin permisos para utilizar internet, no podemos iniciar la app");

                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3500); // As I am using LENGTH_LONG in Toast
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
