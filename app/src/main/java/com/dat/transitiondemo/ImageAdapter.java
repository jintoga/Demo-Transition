package com.dat.transitiondemo;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dat on 24-Aug-17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> data = new ArrayList<>();
    private ClickListener clickListener;

    public ImageAdapter(List<String> images, @NonNull ClickListener clickListener) {
        data.addAll(images);
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    public void setData(@NonNull List<String> urls) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.clear();
        this.data.addAll(urls);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (data != null && !data.isEmpty()) {
            final String url = data.get(position);
            if (url != null) {
                holder.bindData(url);
                ViewCompat.setTransitionName(holder.thumbnail, String.valueOf(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onItemClicked(url, holder.thumbnail);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(@NonNull final String url) {
            Picasso.with(itemView.getContext())
                    .load(url)
                    .into(thumbnail);
        }
    }

    interface ClickListener {
        void onItemClicked(@NonNull String url, @NonNull View view);
    }
}
