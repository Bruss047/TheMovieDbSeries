package eduit11.themoviedb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.snowdream.android.widget.SmartImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by garispe on 27/4/17.
 */

public class SeriesAdapter extends BaseAdapter {

    private ArrayList<SeriesTV> listaImagenes = new ArrayList<>();
    private Context context;

    public SeriesAdapter (ArrayList<SeriesTV> listaImaenes, Context context){

        this.listaImagenes= listaImaenes;
        this.context= context;


    }


    @Override
    public int getCount() {
        return listaImagenes.size();
    }

    @Override
    public SeriesTV getItem(int position) {
        return listaImagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaImagenes.get(position).getId();
    }

    public void setImagenes(ArrayList<SeriesTV> imagenes) {

        this.listaImagenes = imagenes;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


    View view;



        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        } else {
            view = convertView;
        }

        SeriesTV imagen = getItem(position);



        TextView titulo = (TextView) view.findViewById(R.id.tvTitulo);
        titulo.setText(imagen.getOriginalName());






        TextView vote_average = (TextView) view.findViewById(R.id.vote_average);
        vote_average.setText("Voto poular medio: " + String.valueOf(imagen.getVoteAverage()));

        ImageView imgView = (ImageView) view.findViewById(R.id.ff);


        String URL = "https://image.tmdb.org/t/p/original" ;




                Picasso.with(context)
                        .load(URL+ imagen.getPosterPath().toString()).resize(300,300).centerCrop()
                        .into(imgView);


            return view;
        }



    }













