package com.vetasrl.appmegasorteo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ganadores extends AppCompatActivity {

    ListView list;
    ArrayList<String> textos = new ArrayList<String>();
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<String> subtitulos = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();
    ArrayList<String> imagenes = new ArrayList<String>();

    logInHelper lgHelper = new logInHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganadores);

        String url = lgHelper.getRemoteUrlForWinners();
        final RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resp = new JSONObject(response);
                            if (resp.getString("result").matches("ok")) {

                                JSONArray news = resp.getJSONArray("ganadores");

                                for(int i = 0; i < news.length(); i++){

                                    JSONObject ganadores = news.getJSONObject(i);

                                    ids.add(ganadores.getString("id"));
                                    titulos.add(ganadores.getString("titulo"));
                                    subtitulos.add(ganadores.getString("subtitulo"));
                                    textos.add(ganadores.getString("texto"));
                                    imagenes.add(lgHelper.getBaseUrl() + ganadores.getString("url_thumb"));
                                }

                                GanadoresList adapter = new GanadoresList(Ganadores.this, titulos, subtitulos, textos, imagenes, ids);
                                list=(ListView)findViewById(R.id.ganadores_list);
                                list.setAdapter(adapter);

                                list.setClickable(true);
                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent(getBaseContext(), Noticia.class);
                                        intent.putExtra("titulo", ((TextView) view.findViewById(R.id.tituloGanador)).getText().toString());
                                        intent.putExtra("subtitulo", ((TextView) view.findViewById(R.id.subtituloGanador)).getText().toString());
                                        intent.putExtra("imagen", ((WebView) view.findViewById(R.id.ganadorImg)).getUrl());
                                        intent.putExtra("cuerpo", ((TextView) view.findViewById(R.id.textoGanador)).getText().toString());
                                        startActivity(intent);


                                    }
                                });


                            } else {
                                lgHelper.mostrarToast(getApplicationContext(), resp.getString("result"));
                            }
                            //resp.getJSONObject("usr").get("doc")
                        } catch (JSONException e) {
                            lgHelper.mostrarToast(getApplicationContext(), "Error en el servicio");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lgHelper.mostrarToast(getApplicationContext(), "Error: " + error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
