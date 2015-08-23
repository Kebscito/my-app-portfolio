package com.example.carlos.myappportfolio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w500/";

    List<Movie> movies = new ArrayList<Movie>();

    public MovieAdapter(Activity context, List<Movie> movies) {
        super(context, 0, movies);
        this.movies = movies;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movies, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_movies);

        Picasso.with(getContext()).load(BASE_IMAGE_URL + movie.getMoviePoster()).into(imageView);

        return convertView;
    }
}