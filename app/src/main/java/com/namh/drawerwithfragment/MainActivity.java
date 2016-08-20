package com.namh.drawerwithfragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.namh.drawerwithfragment.frag.OneFragment;
import com.namh.drawerwithfragment.frag.ThreeFragment;
import com.namh.drawerwithfragment.frag.TwoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OneFragment.OnFragmentInteractionListener,
        TwoFragment.OnFragmentInteractionListener,
        ThreeFragment.OnFragmentInteractionListener{



    ////////////////////////////////////////////////////////////
    //
    //
    //  Overrides
    //
    //
    ////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        _setDrawer();

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


    ////////////////////////////////////////////////////////////
    //
    //
    //  methods
    //
    //
    ////////////////////////////////////////////////////////////

    private void _setDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void replaceWithTwoFragment() {
        TwoFragment fragment = null;
        String fragmentName = "twofrag";

        try {
            fragment = TwoFragment.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .addToBackStack(fragmentName)
                .commit();
    }

    private void replaceWithThreeFragment() {
        ThreeFragment fragment = null;
        String fragmentName = "twofrag";

        try {
            fragment = ThreeFragment.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .addToBackStack(fragmentName)
                .commit();
    }


    ////////////////////////////////////////////////////////////
    //
    //
    //  Implements
    //
    //
    ////////////////////////////////////////////////////////////

    //-------------------------------------------- OneFragment.OnFragmentInteractionListener
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTwoFragmentInteraction(Uri uri) {

    }

    @Override
    public void onThreeFragmentInteraction(Uri uri) {

    }


    //-------------------------------------------- NavigationView.OnNavigationItemSelectedListener
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            replaceWithTwoFragment();

        } else if (id == R.id.nav_gallery) {

            replaceWithThreeFragment();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
