package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        // Eddy 03/11
        mTabLayout.setupWithViewPager(mViewPager);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new NeighbourFragment(), "My Neighbours");
        mPagerAdapter.addFragment(new FavoriteNeighbourFragment(), "Favorites");
        mViewPager.setAdapter(mPagerAdapter);
        // fin

        setSupportActionBar(mToolbar);
        //mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        //mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    // Ajout EDDY 13/10
    /**
     * Used to navigate to this activity
     * @param activity

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, ListNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
     */
    // Fin ajout 13/10
}
