package eduit11.themoviedb.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import cz.msebera.android.httpclient.Header;
import eduit11.themoviedb.Adapters.SeriesAdapter;
import eduit11.themoviedb.R;
import eduit11.themoviedb.SeriesTV;


public class MainActivity extends AppCompatActivity {

    private SeriesAdapter seriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Series Más Populares");
        getSupportActionBar().setIcon(R.drawable.tvv);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final ListView listview = (ListView) findViewById(R.id.listview);


        seriesAdapter = new SeriesAdapter(listaSeries, MainActivity.this);
        listview.setAdapter(seriesAdapter);


        descargarImagenes();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SeriesTV seriesTV = (SeriesTV) listview.getItemAtPosition(position);

                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("id", seriesTV.getId());

                detalle.putExtra("original_name", seriesTV.getOriginalName());

                detalle.putExtra("vote_average", seriesTV.getVoteAverage());

                detalle.putExtra("poster_path", seriesTV.getPosterPath());


                startActivity(detalle);
            }
        });
    }


    private ArrayList<SeriesTV> listaSeries = new ArrayList<>();

    private void descargarImagenes() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("CARGANDO...");
        progress.setCancelable(false);
        progress.show();


        AsyncHttpClient client = new AsyncHttpClient();


        client.get("https://api.themoviedb.org/3/discover/tv?sort_by=popularity.desc&api_key=f766dd682018c893fe56d669309cec33", null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                        if (statusCode == 200) {

                            progress.dismiss();

                            try {

                                JSONArray resultados = responseBody.getJSONArray("results");

                                for (int i = 0; i < resultados.length(); i++) {

                                    SeriesTV seriesTV = new SeriesTV();

                                    seriesTV.setId(resultados.getJSONObject(i).getLong("id"));
                                    seriesTV.setPosterPath(resultados.getJSONObject(i).getString("poster_path"));
                                    seriesTV.setOriginalName(resultados.getJSONObject(i).getString("original_name"));

                                    seriesTV.setVoteAverage(resultados.getJSONObject(i).getDouble("vote_average"));


                                    listaSeries.add(seriesTV);

                                    Collections.sort(listaSeries, new Comparator<SeriesTV>() {

                                        @Override
                                        public int compare(SeriesTV s1, SeriesTV s2) {

                                            return Double.compare(s2.getVoteAverage(), (s1.getVoteAverage()));
                                        }

                                    });

                                }

                                seriesAdapter.setImagenes(listaSeries);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }

        );
    }
}