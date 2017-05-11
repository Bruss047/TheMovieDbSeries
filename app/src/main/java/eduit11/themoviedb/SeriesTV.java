package eduit11.themoviedb;

import android.text.Html;
import android.widget.ImageView;

/**
 * Created by Javi047 on 27/4/2017.
 */

public class SeriesTV {

    private String thumbnail;
    private String nombreReal;
    private String nombrePersonaje;
    private long id;
    private long order;
    private long gender;
    private String creditId;
    private String originalName;
    private String posterPath;
    private double voteAverage;



    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }



    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }



    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getNombrePersonaje() {

        return nombrePersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {

        this.nombrePersonaje = nombrePersonaje;
    }

    public String getNombreReal() {

        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {

        this.nombreReal = nombreReal;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return this.originalName;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }
}
