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
 * Created by Javi047 on 28/4/2017.
 */

public class CastAdapter extends BaseAdapter {


    private ArrayList<SeriesTV> Cast = new ArrayList<>();
    private Context context;

    public CastAdapter(ArrayList<SeriesTV> listaImaenes, Context context) {

        this.Cast = listaImaenes;
        this.context = context;


    }


    @Override
    public int getCount() {
        return Cast.size();
    }

    @Override
    public SeriesTV getItem(int position) {
        return Cast.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Cast.get(position).getId();
    }

    public void setCast(ArrayList<SeriesTV> cast) {

        this.Cast = cast;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view;


        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.casting, parent, false);

        } else {
            view = convertView;
        }

        SeriesTV casting = getItem(position);


        TextView nombrepersonaje = (TextView) view.findViewById(R.id.nombrePersonaje);
        nombrepersonaje.setText(casting.getNombrePersonaje());


        TextView nombreReal = (TextView) view.findViewById(R.id.nombreReal);
        nombreReal.setText(casting.getNombreReal());

        ImageView thumbNail = (ImageView) view.findViewById(R.id.foto);


        String URL = "https://image.tmdb.org/t/p/original";


        Picasso.with(context)
                .load(URL + casting.getThumbnail().toString()).resize(300, 300).centerCrop()
                .into(thumbNail);


        return view;
    }


}
