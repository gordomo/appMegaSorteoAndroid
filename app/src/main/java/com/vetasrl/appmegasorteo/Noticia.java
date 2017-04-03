package com.vetasrl.appmegasorteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class Noticia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        String subtituloString = getIntent().getStringExtra("subtitulo");
        String tituloString = getIntent().getStringExtra("titulo");
        String curpoString = getIntent().getStringExtra("cuerpo");
        String imagenString = getIntent().getStringExtra("imagen");

        TextView subtitulo = (TextView)findViewById(R.id.subtitulo);
        TextView titulo = (TextView)findViewById(R.id.titulo);
        TextView cuerpo = (TextView)findViewById(R.id.cuerpoNoticia);
        WebView img = (WebView) findViewById(R.id.imagen);

        subtitulo.setText(subtituloString);
        titulo.setText(tituloString);
        cuerpo.setText(curpoString);
        img.loadDataWithBaseURL(null, "<html><head></head><body><table style=\"width:100%;background-color: #798188;height:100%;\"><tr><td style=\"text-align:center\"><img src=\"" + imagenString + "\"></td></tr></table></body></html>", "html/css", "utf-8", null);

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
