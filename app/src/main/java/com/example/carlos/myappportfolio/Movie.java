package com.example.carlos.myappportfolio;

import com.google.gson.annotations.SerializedName;

/**
 * Created by carlos
 */
public class Movie {
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("poster_path")
    private String moviePoster;
    @SerializedName("overview")
    private String plotSynopsis;
    @SerializedName("vote_average")
    private String userRating;
    @SerializedName("release_date")
    private String releaseDate;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}