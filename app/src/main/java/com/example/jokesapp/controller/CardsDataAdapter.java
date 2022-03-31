package com.example.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.jokesapp.R;
import com.example.jokesapp.model.Joke;

public class CardsDataAdapter extends ArrayAdapter<String> {

    private JokeLikeListener mJokeLikeListener;
    private Joke mJoke;

    private Context mContext;
    private boolean clicked = false;

    private SharedPreferences mSharedPreferences;

    public CardsDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext = context;

        mJokeLikeListener = (JokeLikeListener) context;

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        //supply the layout for your card
        TextView jokeTextView = (contentView.findViewById(R.id.content));
        ImageButton likeButton = (contentView.findViewById(R.id.like_button));
        ImageButton shareButton = (contentView.findViewById(R.id.share_button));
        jokeTextView.setText(getItem(position));

        if(mSharedPreferences.contains(getItem(position))){
            likeButton.setImageResource(R.drawable.like_filled);
            clicked = true;
        } else {
            clicked = false;
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked){
                    clicked = true;
                    likeButton.setImageResource(R.drawable.like_filled);

                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(likeButton);

                    mJoke = new Joke(getItem(position), true);
                } else {
                    clicked = false;
                    likeButton.setImageResource(R.drawable.like_empty);

                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(likeButton);

                    mJoke = new Joke(getItem(position), false);
                }
                mJokeLikeListener.jokeIsLiked(mJoke);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareBody = jokeTextView.getText().toString();
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Mama Joke!");
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                jokeTextView.getContext().startActivity(Intent.createChooser(intent,"Share Via"));
            }
        });

        return contentView;
    }
}
