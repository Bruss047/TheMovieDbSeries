package eduit11.themoviedb.Actividades;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import eduit11.themoviedb.Adapters.CastAdapter;
import eduit11.themoviedb.R;
import eduit11.themoviedb.SeriesTV;

public class DetalleActivity extends AppCompatActivity {

    private CastAdapter castAdapter;
    private Context context;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Casting");
        getSupportActionBar().setIcon(R.drawable.casting);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//muestra el icono
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//activa la flecha back.


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);


        final ListView listview = (ListView) findViewById(R.id.viewlist);
        boton = (Button) findViewById(R.id.boton);


        castAdapter = new CastAdapter(reparto, DetalleActivity.this);
        listview.setAdapter(castAdapter);


        descargarReparto();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(DetalleActivity.this, TemporadasActivity.class);
                Bundle extras = getIntent().getExtras();
                a.putExtra("id", extras.getLong("id"));

                startActivity(a);


            }
        });


    }

    ;


    private ArrayList<SeriesTV> reparto = new ArrayList<>();


    private void descargarReparto() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Cargando...");
        progress.setCancelable(false);
        progress.show();


        Bundle extras = getIntent().getExtras();

        String titulo = extras.getString("original_name");

        String thumbnail = extras.getString("poster_path");

        double vt = extras.getDouble("vote_average");

        String voto = Double.toString(vt);


        TextView serie = (TextView) findViewById(R.id.titulo);
        serie.setText(titulo);


        ImageView thumb = (ImageView) findViewById(R.id.thumb);


        String URLe = "https://image.tmdb.org/t/p/original";

       /* Picasso.with(context)
                .load(URLe + thumbnail)
                .into(thumb);*/


        ImageLoader.getInstance().displayImage(URLe + thumbnail, thumb);


        TextView promedio = (TextView) findViewById(R.id.average);
        promedio.setText("Voto Promedio: " + voto);

        long sId = extras.getLong("id");

        String cadena = Long.toString(sId);


        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://api.themoviedb.org/3/tv/" + cadena + "/credits?api_key=f766dd682018c893fe56d669309cec33", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                if (statusCode == 200) {

                    progress.dismiss();

                    try {

                        JSONArray resultados = responseBody.getJSONArray("cast");

                        for (int i = 0; i < resultados.length(); i++) {

                            SeriesTV seriesTV = new SeriesTV();


                            seriesTV.setId(resultados.getJSONObject(i).getLong("id"));
                            seriesTV.setNombrePersonaje(resultados.getJSONObject(i).getString("character"));
                            seriesTV.setNombreReal(resultados.getJSONObject(i).getString("name"));
                            seriesTV.setThumbnail(resultados.getJSONObject(i).getString("profile_path"));
                            //seriesTV.setVoteAverage(resultados.getJSONObject(i).getDouble("vote_average"));


                            reparto.add(seriesTV);
                        }

                        castAdapter.setCast(reparto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}

