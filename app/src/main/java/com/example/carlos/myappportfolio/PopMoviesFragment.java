package com.example.carlos.myappportfolio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PopMoviesFragment extends Fragment {

    private MovieAdapter movieAdapter;

    public PopMoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        movieAdapter =
                new MovieAdapter(getActivity(), new ArrayList<Movie>());

        View rootView = inflater.inflate(R.layout.fragment_pop_movies, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.list_view_movies);
        gridView.setAdapter(movieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie movie = movieAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                intent.putExtra("original_title", movie.getOriginalTitle());
                intent.putExtra("poster_path", movie.getMoviePoster());
                intent.putExtra("overview", movie.getPlotSynopsis());
                intent.putExtra("release_date", movie.getReleaseDate());
                intent.putExtra("vote_average", movie.getUserRating());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMovies();
    }

    private void updateMovies() {
        FetchMoviesTask weatherTask = new FetchMoviesTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(getString(R.string.pref_sort_order),
                getString(R.string.pref_default_sort));
        weatherTask.execute(sortOrder);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";

        final String API_KEY = "###";

        @Override
        protected List doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            MovieObject movieObject;

            try {
                final String API_KEY_PARAM = "api_key";
                final String SORT_BY_PARAM = "sort_by";

                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .appendQueryParameter(SORT_BY_PARAM, params[0])
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                Reader reader = new InputStreamReader(inputStream);

                Gson gson = new Gson();

                movieObject = gson.fromJson(reader, MovieObject.class);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return movieObject.getResults();

        }

        @Override
        protected void onPostExecute(List<Movie> result) {
            if (result != null) {
                movieAdapter.clear();
                for (Movie movie : result) {
                    movieAdapter.add(movie);
                }
            }
        }
    }
}