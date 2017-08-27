package com.dat.transitiondemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dat on 24-Aug-17.
 */

public class ChildActivity extends AppCompatActivity {

    private static final String EXTRA_URL = "EXTRA_URL";
    private static final String EXTRA_TRANSITION_ITEM_NAME = "EXTRA_TRANSITION_ITEM_NAME";

    @BindView(R.id.thumbnail)
    protected ImageView thumbnail;

    public static void start(@NonNull Context context,
                             @NonNull String url,
                             @Nullable String transitionItemName,
                             @Nullable ActivityOptionsCompat transition) {
        if (context instanceof ChildActivity) {
            return;
        }
        Intent intent = new Intent(context, ChildActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TRANSITION_ITEM_NAME, transitionItemName);
        context.startActivity(intent, transition == null ? null : transition.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        supportPostponeEnterTransition();
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = getIntent().getStringExtra(EXTRA_TRANSITION_ITEM_NAME);
            thumbnail.setTransitionName(imageTransitionName);
        }
        init();
    }

    private void init() {
        String url = getIntent().getStringExtra(EXTRA_URL);
        if (url != null && !url.isEmpty()) {
            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.place_holder)
                    .noFade()
                    .into(thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }
    }

}
