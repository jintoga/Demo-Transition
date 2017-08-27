package com.dat.transitiondemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ImageAdapter.ClickListener {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    private ImageAdapter adapter;

    @Override
    public void onItemClicked(@NonNull String url, @NonNull View view) {
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        ActivityOptionsCompat optionsCompat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("selectedImage");
            optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view,
                view.getTransitionName());
        }
        ChildActivity.start(this, url, optionsCompat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            getWindow().setSharedElementExitTransition(slide);
        }
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.setData(DummyGenerator.getImages());
    }

    private void init() {
        StaggeredGridLayoutManager layoutManager =
            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(
            StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(
            new SpacesItemDecoration((int) getResources().getDimension(R.dimen.space)));
        recyclerView.setHasFixedSize(false);
        adapter = new ImageAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
