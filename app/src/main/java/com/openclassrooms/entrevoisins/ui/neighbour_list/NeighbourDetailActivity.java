package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.lang.annotation.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.neighbour_detail_photo)
    ImageView mNeighbourDetailPhoto;
    @BindView(R.id.textview_neighbour_name_portrait)
    TextView mTextViewPortraitName;
    @BindView(R.id.textview_neighbour_name)
    TextView mTextviewNeighbourName;
    @BindView(R.id.textview_address)
    TextView mTextviewAddress;
    @BindView(R.id.textview_phone_number)
    TextView mTextviewPhoneNumber;
    @BindView(R.id.textview_facebook_link)
    TextView mTextviewFacebookLink;
    @BindView(R.id.textViewAboutMe)
    TextView mTextViewAboutMe;
    @BindView(R.id.textViewAboutMeInfo)
    TextView mTextviewAboutMeInfo;
    @BindView(R.id.button_add_favorite)
    FloatingActionButton addFavoriteButton;

    private Neighbour neighbour;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();
        mTextViewAboutMe.setText("A Propos de moi :");

        getNeighbourDataFromBundle();
    }

    @OnClick(R.id.button_previous_page)
    void previousPage() { finish(); }

    @OnClick(R.id.button_add_favorite)
    void addToFavoriteList() {
        if (neighbour.isFavorite()) {
            neighbour.setFavorite(false);
            addFavoriteButton.setImageResource(R.drawable.ic_baseline_star_is_favorite_false);
        } else {
            neighbour.setFavorite(true);
            addFavoriteButton.setImageResource(R.drawable.ic_baseline_star_is_favorite_true);
        }
        mApiService.updateNeighbour(neighbour);
    }

    void setAddFavoriteButtonAppearance() {
        if(neighbour.isFavorite()) {
            addFavoriteButton.setImageResource(R.drawable.ic_baseline_star_is_favorite_true);
        } else {
            addFavoriteButton.setImageResource(R.drawable.ic_baseline_star_is_favorite_false);
        }
    }

    void getNeighbourDataFromBundle() {
        Intent intent = getIntent();
        Bundle myBundle = intent.getBundleExtra("BUNDLE_NEIGHBOUR_CLICKED");
        neighbour = (Neighbour) myBundle.get("NEIGHBOUR_OBJECT");

        String facebookLink = "www.facebook.fr/" + neighbour.getName();
        mTextViewPortraitName.setText(neighbour.getName());
        mTextviewNeighbourName.setText(neighbour.getName());
        mTextviewAddress.setText(neighbour.getAddress());
        mTextviewPhoneNumber.setText(neighbour.getPhoneNumber());
        mTextviewFacebookLink.setText(facebookLink);
        mTextviewAboutMeInfo.setText(neighbour.getAboutMe());
        Glide.with(NeighbourDetailActivity.this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mNeighbourDetailPhoto);

        setAddFavoriteButtonAppearance();
    }
}
