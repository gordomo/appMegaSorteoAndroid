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

public class Noticias extends AppCompatActivity {

    ListView list;
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();
    ArrayList<String> subtitulos = new ArrayList<String>();
    ArrayList<String> cuerpos = new ArrayList<String>();
    ArrayList<String> imagenes = new ArrayList<String>();

    logInHelper lgHelper = new logInHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        String url = lgHelper.getRemoteUrlForNews();
        final RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resp = new JSONObject(response);
                            if (resp.getString("result").matches("ok")) {

                                JSONArray news = resp.getJSONArray("news");

                                for(int i = 0; i < news.length(); i++){

                                    JSONObject noticia = news.getJSONObject(i);

                                    ids.add(noticia.getString("id"));
                                    titulos.add(noticia.getString("titulo"));
                                    subtitulos.add(noticia.getString("subtitulo"));
                                    imagenes.add(lgHelper.getBaseUrl() + noticia.getString("url_thumb"));
                                    cuerpos.add(noticia.getString("cuerpo"));

                                }

                                NewsList adapter = new NewsList(Noticias.this, titulos, subtitulos, cuerpos, imagenes, ids);
                                list=(ListView)findViewById(R.id.news_list);
                                list.setAdapter(adapter);

                                list.setClickable(true);
                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                                        Intent intent = new Intent(getBaseContext(), Noticia.class);
                                        intent.putExtra("titulo", ((TextView) view.findViewById(R.id.titulo)).getText().toString());
                                        intent.putExtra("subtitulo", ((TextView) view.findViewById(R.id.subtitulo)).getText().toString());
                                        intent.putExtra("imagen", ((WebView) view.findViewById(R.id.newImg)).getUrl());
                                        intent.putExtra("cuerpo", ((TextView) view.findViewById(R.id.cuerpo)).getText().toString());
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
