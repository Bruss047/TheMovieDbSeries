package eduit11.themoviedb;

/**
 * Created by Javi047 on 29/5/2017.
 */

public class Temporadas {

    private int id;
    private String air_date;
    private int episode_count;
    private String poster_path;
    private int season_number;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }
}
