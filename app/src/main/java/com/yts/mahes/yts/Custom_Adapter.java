package com.yts.mahes.yts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yts.mahes.model.yts.Movie;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by mahes on 11-Feb-17.
 */

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;
    private int rowLayout;

    public Custom_Adapter(List<Movie> movies, int rowLayout, Context context) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Custom_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(rowLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final StringBuilder b = new StringBuilder();
        for (int i = 0; i < movies.get(position).getGenre().size(); i++) {
            b.append(movies.get(position).getGenre().get(i));
            if (i < movies.get(position).getGenre().size() - 1) {
                b.append(" / ");
            }
        }

        final List<String> genre = new ArrayList<String>(Arrays.asList(b.toString()));

        holder.movie_name.setText(movies.get(position).getTitle());
        holder.genre.setText(b.toString());
        holder.year.setText(movies.get(position).getYear().toString());
        holder.duration.setText(movies.get(position).getRuntime().toString() + " min");
        holder.rating.setText(movies.get(position).getRating().toString());

        Glide.with(context)
                .load(movies.get(position).getMediumImage())
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> quality = new ArrayList<String>();
                List<String> url = new ArrayList<String>();

                Intent intent = new Intent(view.getContext(), Movie_Download.class);
                intent.putExtra("moviename", movies.get(position).getTitle());
                intent.putExtra("year", String.valueOf(movies.get(position).getYear()));
                intent.putStringArrayListExtra("genre", (ArrayList<String>) genre);
                intent.putExtra("rating", String.valueOf(movies.get(position).getRating()));
                intent.putExtra("runtime", String.valueOf(movies.get(position).getRuntime()));
                intent.putExtra("mpa_rating", String.valueOf(movies.get(position).getMpa_rating()));
                intent.putExtra("summary", String.valueOf(movies.get(position).getSummary()));
                intent.putExtra("image",String.valueOf(movies.get(position).getMediumImage()));
                intent.putExtra("trailer",String.valueOf(movies.get(position).getYt_trailer_code()));

                for (int i = 0; i < movies.get(position).getTorrents().size(); i++) {
                    quality.add(movies.get(position).getTorrents().get(i).getQuality());
                }
                intent.putStringArrayListExtra("Quality", (ArrayList<String>) quality);

                for (int i = 0; i < movies.get(position).getTorrents().size(); i++) {
                    url.add(movies.get(position).getTorrents().get(i).getUrl());
                }
                intent.putStringArrayListExtra("url", (ArrayList<String>) url);
                view.getContext().startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mMovieLayout;
        public TextView movie_name;
        public TextView genre;
        public TextView year;
        public TextView duration;
        public ImageView image;
        public TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);
            mMovieLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
            movie_name = (TextView) itemView.findViewById(R.id.movie_name);
            genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.year);
            duration = (TextView) itemView.findViewById(R.id.duration);
            image = (ImageView) itemView.findViewById(R.id.image);
            rating = (TextView) itemView.findViewById(R.id.rating);
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

