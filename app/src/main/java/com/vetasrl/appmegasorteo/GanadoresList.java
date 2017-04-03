package com.vetasrl.appmegasorteo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mmori on 6/2/2017.
 */

public class GanadoresList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> textos;
    private final ArrayList<String> titulos;
    private final ArrayList<String> subtitulos;
    private final ArrayList<String> ids;
    private final ArrayList<String> imagenes;

    public GanadoresList(Activity context, ArrayList<String> titulos, ArrayList<String> subtitulos, ArrayList<String> textos, ArrayList<String> imagenes, ArrayList<String> ids) {
        super(context, R.layout.ganadores_list_item, textos);
        this.context = context;
        this.textos = textos;
        this.titulos = titulos;
        this.subtitulos = subtitulos;
        this.imagenes = imagenes;
        this.ids = ids;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.ganadores_list_item, null, true);

        TextView texto = (TextView) rowView.findViewById(R.id.textoGanador);
        TextView titulo = (TextView) rowView.findViewById(R.id.tituloGanador);
        TextView subtitulo = (TextView) rowView.findViewById(R.id.subtituloGanador);
        WebView ganadorImg = (WebView) rowView.findViewById(R.id.ganadorImg);
        TextView id = (TextView) rowView.findViewById(R.id.ganadorId);

        texto.setText(textos.get(position));
        titulo.setText(titulos.get(position));
        subtitulo.setText(subtitulos.get(position));
        ganadorImg.loadUrl(imagenes.get(position));
        id.setText(ids.get(position));

        return rowView;
    }

}

