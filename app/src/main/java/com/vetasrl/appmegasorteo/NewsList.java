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

public class NewsList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> titulos;
    private final ArrayList<String> subtitulos;
    private final ArrayList<String> cuerpos;
    private final ArrayList<String> imagenes;
    private final ArrayList<String> ids;

    public NewsList(Activity context, ArrayList<String> titulos, ArrayList<String> subtitulos, ArrayList<String> cuerpos, ArrayList<String> imagenes, ArrayList<String> ids) {
        super(context, R.layout.new_list_item, titulos);
        this.context = context;
        this.titulos = titulos;
        this.subtitulos = subtitulos;
        this.cuerpos = cuerpos;
        this.imagenes = imagenes;
        this.ids = ids;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.new_list_item, null, true);

        WebView img = (WebView) rowView.findViewById(R.id.newImg);
        TextView titulo = (TextView) rowView.findViewById(R.id.titulo);
        TextView subtitulo = (TextView) rowView.findViewById(R.id.subtitulo);
        TextView cuerpo = (TextView) rowView.findViewById(R.id.cuerpo);
        TextView newId = (TextView) rowView.findViewById(R.id.newId);

        img.loadUrl(imagenes.get(position));
        titulo.setText(titulos.get(position));
        subtitulo.setText(subtitulos.get(position));
        cuerpo.setText(cuerpos.get(position));
        newId.setText(ids.get(position));

        return rowView;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        // TODO Auto-generated method stub
        return true;
    }
}

