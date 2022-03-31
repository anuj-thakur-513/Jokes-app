package com.example.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;
import com.example.jokesapp.model.Joke;
import com.example.jokesapp.view.FavJokeViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavJokeListAdapter extends RecyclerView.Adapter<FavJokeViewHolder> {

    private ArrayList<Joke> mJokeList;
    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public FavJokeListAdapter(ArrayList<Joke> jokeList, Context context) {
        mJokeList = jokeList;
        mContext = context;
    }

    @NonNull
    @Override
    public FavJokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_joke_item, parent, false);
        return new FavJokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavJokeViewHolder holder, int position) {
        String jokeText = mJokeList.get(position).getJokeText();
        holder.getTxtFavJoke().setText(jokeText);

        holder.getImgShareButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Mama Joke!");
                intent.putExtra(Intent.EXTRA_TEXT,jokeText);
                mContext.startActivity(Intent.createChooser(intent,"Share Via"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJokeList.size();
    }


}
