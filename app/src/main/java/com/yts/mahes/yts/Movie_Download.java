package com.yts.mahes.yts;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yts.mahes.model.yts.MovieResponse;
import com.yts.mahes.rest.ApiClient;
import com.yts.mahes.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie_Download extends AppCompatActivity {

    ArrayList<String> quality;
    ArrayList<String> genre;
    List<String> uris;
    Uri url;
    ImageView mDownload, movieImage,mYoutube;
    TextView mMovieName, mYear, mRating, mRatingValue, mGenre, mGenrevalue, mMPARating,
            mMPARatingValue, mRunTime, mRuntimeValue, mQuality, mQualityValue, mSynopsis, mBrief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__download);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Yts Browser");

        mMovieName = (TextView) findViewById(R.id.moviename);
        mYear = (TextView) findViewById(R.id.year);
        mRating = (TextView) findViewById(R.id.Rating);
        mRatingValue = (TextView) findViewById(R.id.ratingvalue);
        mGenre = (TextView) findViewById(R.id.genre);
        mGenrevalue = (TextView) findViewById(R.id.genrevalue);
        mMPARating = (TextView) findViewById(R.id.MPArating);
        mMPARatingValue = (TextView) findViewById(R.id.MPAratingvalue);
        mRunTime = (TextView) findViewById(R.id.Runtime);
        mRuntimeValue = (TextView) findViewById(R.id.runtimevalue);
        mQuality = (TextView) findViewById(R.id.Quality);
        mQualityValue = (TextView) findViewById(R.id.qualityvalue);
        mSynopsis = (TextView) findViewById(R.id.synopsis);
        mBrief = (TextView) findViewById(R.id.brief);
        mDownload = (ImageView) findViewById(R.id.download);
        movieImage = (ImageView) findViewById(R.id.movieImage);
        mYoutube = (ImageView) findViewById(R.id.youtube);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getListMovie();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Intent mGetIntent = getIntent();
                String title = mGetIntent.getStringExtra("moviename").toUpperCase();
                String year = mGetIntent.getStringExtra("year");
                String rating = mGetIntent.getStringExtra("rating");
                String mpa_rating = mGetIntent.getStringExtra("mpa_rating");
                String runtime = mGetIntent.getStringExtra("runtime");
                String summary = mGetIntent.getStringExtra("summary");
                uris = mGetIntent.getStringArrayListExtra("url");
                final String trailer = mGetIntent.getStringExtra("trailer");
                for (String uri : uris) {
                    if (uri != null) {
                        url = Uri.parse(uri);
                    }
                }
                quality = mGetIntent.getStringArrayListExtra("Quality");
                final StringBuilder s = new StringBuilder();
                for (int i = 0; i < quality.size(); i++) {
                    s.append(quality.get(i));
                    if (i < quality.size() - 1) {
                        s.append(" / ");
                    }
                }

                genre = mGetIntent.getStringArrayListExtra("genre");
                final StringBuilder g = new StringBuilder();
                for (int i = 0; i < genre.size(); i++) {
                    g.append(genre.get(i));
                    if (i < genre.size() - 1) {
                        g.append((" / "));
                    }
                }

                String image = mGetIntent.getStringExtra("image");
                Glide.with(getApplicationContext())
                        .load(Uri.parse(image))
                        .centerCrop()
                        .bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                        .into(movieImage);
                mMovieName.setText(title);
                mYear.setText(year);
                mRatingValue.setText(rating);
                mMPARatingValue.setText(mpa_rating);
                mGenrevalue.setText(g.toString());
                mRuntimeValue.setText(runtime + " min");
                mQualityValue.setText(s.toString());
                mBrief.setText(summary);
                if (mpa_rating.equals("PG")) {
                    mMPARatingValue.setText("Parental Guidance");
                } else if (mpa_rating.equals("PG-13")) {
                    mMPARatingValue.setText("Parents Cautioned");
                } else if (mpa_rating.equals("R")) {
                    mMPARatingValue.setText("Restricted");
                } else {
                    mMPARatingValue.setText("Adults Only");
                }
                mDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        downloadTorrent();
                    }
                });
                mYoutube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailer));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("URL", "Error", t);
            }
        });
    }

    private void downloadTorrent() {

        final AlertDialog bulider = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.download_quality, null);
        bulider.setView(dialogview);
        bulider.setTitle("Slect the Quality");

        final ListView qualities = (ListView) dialogview.findViewById(R.id.downloadList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quality);
        qualities.setAdapter(adapter);
        qualities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uris.get(position)));
                request.setTitle("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uris.get(position)));
                startActivity(intent);
                bulider.dismiss();
            }
        });
        bulider.show();

    }
}


