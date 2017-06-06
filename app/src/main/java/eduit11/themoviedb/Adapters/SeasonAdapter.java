package eduit11.themoviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduit11.themoviedb.R;
import eduit11.themoviedb.Temporadas;

/**
 * Created by Javi047 on 29/5/2017.
 */

public class SeasonAdapter extends BaseAdapter {


    private ArrayList<Temporadas> temp = new ArrayList<>();
    private Context context;


    public SeasonAdapter(ArrayList<Temporadas> temp, Context context) {

        this.context = context;
        this.temp = temp;

    }


    @Override
    public int getCount() {
        return temp.size();
    }

    @Override
    public Object getItem(int position) {
        return temp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return temp.get(position).getId();
    }

    public void setCast(ArrayList<Temporadas> cast) {

        this.temp = cast;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;


        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temporadas, parent, false);

        } else {
            view = convertView;
        }

        Temporadas tempora = (Temporadas) getItem(position);


        TextView air = (TextView) view.findViewById(R.id.airdate);
        air.setText("Fecha de Emisión: " + tempora.getAir_date());

        TextView ecount = (TextView) view.findViewById(R.id.ecount);
        ecount.setText("Cantidad de Episodios: " + String.valueOf(tempora.getEpisode_count()));

        TextView snumber = (TextView) view.findViewById(R.id.snumber);
        snumber.setText("Temporada Número: " + String.valueOf(tempora.getSeason_number()));


        return view;


    }
}
