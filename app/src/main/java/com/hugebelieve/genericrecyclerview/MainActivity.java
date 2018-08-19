package com.hugebelieve.genericrecyclerview;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.hugebelieve.genericrecyclerview.Gists.GenericRecyclerViewAdapter;
import com.hugebelieve.genericrecyclerview.databinding.HomeRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Pair<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Snackbar.make(view, "Contact me on bishal@hugebelieve.com", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //This is a temporary data set to fill our recycler view
        data = new ArrayList<>();
        //Lets fill it with some data from IMDB
        addRecyclerviewData(data);

        //Function to initiate the RecyclerView
        recyclerViewInIt();
    }

    public void recyclerViewInIt(){
        //Now get ref of the recycler view
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);

        //Lets set some default layout for our recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Our main content of recycler view adapter starts here

        //Lets name our new adapter for recycler view
        GenericRecyclerViewAdapter genericAdapter;

        //Lets initialise it
        genericAdapter = new GenericRecyclerViewAdapter();
        //Set the child layout you need
        genericAdapter.setChildLayout(R.layout.home_recyclerview);
        //An interface to dynamically pass the recyclerview item count
        genericAdapter.setDataSizeInterface(new GenericRecyclerViewAdapter.DataSizeInterface() {
            @Override
            public int ReturnDataSetSizeForAdapter() {
                return data.size();
            }
        });
        //Finally interface to get a feedback after each child view is binded to UI
        genericAdapter.setRecyclerViewBinding(new GenericRecyclerViewAdapter.RecyclerViewBinder() {
            @Override
            public void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder) {
                //This method gets called when each row our recycler view gets binded with the UI
                //First we have to get binding variable for our recycler view itself
                //HomeRecyclerviewBinding gets auto created by Android Data Binding Library
                HomeRecyclerviewBinding homeRecyclerviewBinding = (HomeRecyclerviewBinding) bindingToUse;

                //We can also have the position of the current recycler view row item
                int holderPosition = holder.getAdapterPosition();

                //Initialise all the items inside recycler view as per your needs
                initialiseEachRowItem(homeRecyclerviewBinding, holderPosition);

                //That's it
            }
        });

        //lastly lets set this adapter to our recycler view
        recyclerView.setAdapter(genericAdapter);
    }

    public void initialiseEachRowItem(HomeRecyclerviewBinding homeRecyclerviewBinding, int holderPosition){
        //Lets get appropriate data from our data set
        Pair<String,String> dataForCurrentRow = data.get(holderPosition);

        //Lets set these values in UI inside recycler view row item
        homeRecyclerviewBinding.setPosition(holderPosition); //This can be useful for other data binding actions
        homeRecyclerviewBinding.title.setText(dataForCurrentRow.first);
        homeRecyclerviewBinding.description.setText(dataForCurrentRow.second);
    }


    public void addRecyclerviewData(List<Pair<String,String>> data){
        data.add(new Pair<String, String>("The Meg (2018)",
                "Slender Man (2018), " +
                        "Jonas Taylor must confront his fears to save those trapped in a sunken submersible."));
        data.add(new Pair<String, String>("Slender Man (2018)",
                "In a small town in Massachusetts, a group of friends, fascinated by the internet lore of the Slender Man, " +
                        "attempt to prove that he doesn't actually exist - until one of them mysteriously goes missing."));
        data.add(new Pair<String, String>("Dog Days (2018)",
                "Dog Days follows a group of interconnected people in " +
                        "Los Angeles who are brought together by their lovable canine counterparts."));
        data.add(new Pair<String, String>("Madeline's Madeline (2018)",
                "A theater director's latest project takes on a life of its own " +
                        "when her young star takes her performance too seriously."));
        data.add(new Pair<String, String>("A Prayer Before Dawn (2017)",
                "The true story of an English boxer incarcerated in one of Thailand's most notorious prisons " +
                        "as he fights in Muay Thai tournaments to earn his freedom."));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent newIntent = null;
        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_vertical) {
            newIntent =  new Intent(this,VerticalViewActivity.class);
        } else if (id == R.id.nav_horizontal) {
            newIntent =  new Intent(this,HorizontalViewActivity.class);
        } else if (id == R.id.nav_grid) {
            newIntent =  new Intent(this,GridViewActivity.class);
        }

        if(newIntent!=null){
            startActivity(newIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
