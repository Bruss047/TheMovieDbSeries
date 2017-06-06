package eduit11.themoviedb.Actividades;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;
import eduit11.themoviedb.Adapters.SeasonAdapter;
import eduit11.themoviedb.R;
import eduit11.themoviedb.SeriesTV;
import eduit11.themoviedb.Temporadas;

public class TemporadasActivity extends AppCompatActivity {


    private SeasonAdapter seasonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporadas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Temporadas");
        getSupportActionBar().setIcon(R.drawable.temporadas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listview = (ListView) findViewById(R.id.listr);


        seasonAdapter = new SeasonAdapter(temp, TemporadasActivity.this);

        listview.setAdapter(seasonAdapter);


        RescuestTemporadas();
    }


    private ArrayList<Temporadas> temp = new ArrayList<>();

    public void RescuestTemporadas() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Cargando...");
        progress.setCancelable(false);
        progress.show();


        Bundle extras = getIntent().getExtras();


        long sId = extras.getLong("id");

        String cadena = Long.toString(sId);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://api.themoviedb.org/3/tv/" + cadena + "?api_key=f766dd682018c893fe56d669309cec33&language=es-ES", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                if (statusCode == 200) {

                    progress.dismiss();

                    try {

                        JSONArray resultados = responseBody.getJSONArray("seasons");

                        for (int i = 0; i < resultados.length(); i++) {

                            Temporadas temporadas = new Temporadas();

                            if (i == 0 && resultados != null) {

                                continue;


                            }

                            temporadas.setId(resultados.getJSONObject(i).getInt("id"));
                            temporadas.setAir_date(resultados.getJSONObject(i).getString("air_date"));
                            temporadas.setEpisode_count(resultados.getJSONObject(i).getInt("episode_count"));
                            temporadas.setPoster_path(resultados.getJSONObject(i).getString("poster_path"));
                            temporadas.setSeason_number(resultados.getJSONObject(i).getInt("season_number"));


                            temp.add(temporadas);
                            seasonAdapter.setCast(temp);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }
}
