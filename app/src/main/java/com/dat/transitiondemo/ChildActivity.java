package com.dat.transitiondemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

/**
 * Created by Dat on 24-Aug-17.
 */

public class ChildActivity extends AppCompatActivity {

    private static final String EXTRA_URL = "EXTRA_URL";

    @BindView(R.id.thumbnail)
    protected ImageView thumbnail;

    public static void start(@NonNull Context context, @NonNull String url,
        @Nullable ActivityOptionsCompat transition) {
        if (context instanceof ChildActivity) {
            return;
        }
        Intent intent = new Intent(context, ChildActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent, transition == null ? null : transition.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(thumbnail);
            getWindow().setSharedElementEnterTransition(slide);
            /*getWindow().setSharedElementEnterTransition(
                TransitionInflater.from(this).inflateTransition(R.transition.shared_element));*/
        }
        init();
    }

    private void init() {
        String url = getIntent().getStringExtra(EXTRA_URL);
        if (url != null && !url.isEmpty()) {
            Picasso.with(this).load(url).placeholder(R.drawable.place_holder).into(thumbnail);
        }
    }
}
