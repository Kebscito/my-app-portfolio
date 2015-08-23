package com.example.carlos.myappportfolio;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailMovieActivityFragment extends Fragment {

    private static final String LOG_TAG = DetailMovieActivityFragment.class.getSimpleName();

    final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w500/";

    private String originalTitle;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String voteAverage;

    public DetailMovieActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        Intent intent = getActivity().getIntent();

        if (intent != null) {

            originalTitle = intent.getStringExtra("original_title");
            posterPath = intent.getStringExtra("poster_path");
            overview = intent.getStringExtra("overview");
            releaseDate = intent.getStringExtra("release_date");
            voteAverage = intent.getStringExtra("vote_average");

            ((TextView) rootView.findViewById(R.id.detail_title))
                    .setText(originalTitle);
            ImageView imageView = ((ImageView) rootView.findViewById(R.id.detail_poster_image));
            Picasso.with(getActivity().getApplicationContext()).load(BASE_IMAGE_URL + posterPath).into(imageView);
            ((TextView) rootView.findViewById(R.id.detail_overview))
                    .setText(overview);
            ((TextView) rootView.findViewById(R.id.detail_release_date))
                    .setText(releaseDate.substring(0, 4));
            ((TextView) rootView.findViewById(R.id.detail_vote_average))
                    .setText(voteAverage.replace(".0","") + "/10");

        }

        return rootView;
    }
}