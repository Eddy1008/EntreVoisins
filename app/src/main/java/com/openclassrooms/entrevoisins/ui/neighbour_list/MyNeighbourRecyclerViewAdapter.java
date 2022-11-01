package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        // Ajout Eddy 20/10
        holder.mNeighbourAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LISTNEIGHBOURACTIVITY", "onClick: Clic sur le portrait de : " + neighbour.getName() + " photo : " + neighbour.getAvatarUrl());
                Intent intent = new Intent(view.getContext(), NeighbourDetailActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putSerializable("NEIGHBOUR_OBJECT", neighbour);
                //myBundle.putString("NEIGHBOUR_PHOTO_URL", neighbour.getAvatarUrl());
                //myBundle.putString("NEIGHBOUR_NAME", neighbour.getName());
                //myBundle.putString("NEIGHBOUR_ADDRESS", neighbour.getAddress());
                //myBundle.putString("NEIGHBOUR_PHONE", neighbour.getPhoneNumber());
                //myBundle.putString("NEIGHBOUR_FACEBOOK", neighbour.getName());
                //myBundle.putString("NEIGHBOUR_DESCRIPTION", neighbour.getAboutMe());

                intent.putExtra("BUNDLE_NEIGHBOUR_CLICKED", myBundle);
                view.getContext().startActivity(intent);
            }
        });
        // Fin ajout Eddy 01/11

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
