


package eduit11.themoviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eduit11.themoviedb.R;
import eduit11.themoviedb.SeriesTV;

/**
 * Created by garispe on 27/4/17.
 */

public class SeriesAdapter extends BaseAdapter {

    private ArrayList<SeriesTV> listaImagenes = new ArrayList<>();
    private Context context;

    public SeriesAdapter(ArrayList<SeriesTV> listaImaenes, Context context) {

        this.listaImagenes = listaImaenes;
        this.context = context;


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
        vote_average.setText("Voto Popular Medio: " + String.valueOf(imagen.getVoteAverage()));

        ImageView imgView = (ImageView) view.findViewById(R.id.ff);


        String URL = "https://image.tmdb.org/t/p/original";


        Picasso.with(context)
                .load(URL + imagen.getPosterPath().toString()).resize(250, 250).centerCrop()
                .into(imgView);


        return view;
    }


}

