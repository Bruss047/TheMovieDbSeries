package eduit11.themoviedb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private SeriesAdapter seriesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView listview = (ListView) findViewById(R.id.listview);



        seriesAdapter = new SeriesAdapter(listaImagenes,MainActivity.this);
        listview.setAdapter(seriesAdapter);


        descargarImagenes();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SeriesTV seriesTV = (SeriesTV) listview.getItemAtPosition(position);

                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("id", seriesTV.getId());

                detalle.putExtra("original_name",seriesTV.getOriginalName());

                detalle.putExtra("vote_average",seriesTV.getVoteAverage());

                detalle.putExtra("poster_path",seriesTV.getPosterPath());





                startActivity(detalle);
            }
        });
    }





    private ArrayList<SeriesTV> listaImagenes = new ArrayList<>();

    private void descargarImagenes() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Cargando...");
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
                                    seriesTV.setOriginalName(resultados.getJSONObject(i).getString("original_name"));
                                    seriesTV.setPosterPath(resultados.getJSONObject(i).getString("poster_path"));
                                    seriesTV.setVoteAverage(resultados.getJSONObject(i).getDouble("vote_average"));




                            listaImagenes.add(seriesTV);



                        }




                        seriesAdapter.setImagenes(listaImagenes);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        );
    }
}
