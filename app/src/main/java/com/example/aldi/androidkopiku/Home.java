package com.example.aldi.androidkopiku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aldi.androidkopiku.Common.Common;
import com.example.aldi.androidkopiku.Interface.ItemClickListener;
import com.example.aldi.androidkopiku.ViewHolder.MenuViewHolder;
import com.example.aldi.androidkopiku.model.Category;
import com.example.aldi.androidkopiku.model.Ranking;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;
    RecyclerView menu;
    RecyclerView.LayoutManager layoutManager;
    TextView fullname;
    ImageView imageView;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.action_category:
                        selectedFragment = CategoryFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectedFragment = RankingFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();
                return true;

            }
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,CategoryFragment.newInstance());
        transaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
