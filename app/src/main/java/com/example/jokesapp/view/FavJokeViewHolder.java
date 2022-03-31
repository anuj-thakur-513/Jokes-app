package com.example.jokesapp.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;

public class FavJokeViewHolder extends RecyclerView.ViewHolder {

    private TextView txtFavJoke;
    private ImageButton imgShareButton;

    public FavJokeViewHolder(@NonNull View itemView) {
        super(itemView);

        txtFavJoke = itemView.findViewById(R.id.txtFavJoke);
        imgShareButton = itemView.findViewById(R.id.shareButtonFavListItem);
    }

    public TextView getTxtFavJoke() {
        return txtFavJoke;
    }

    public ImageButton getImgShareButton() {
        return imgShareButton;
    }
}
