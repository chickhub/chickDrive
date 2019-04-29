package com.android.chickdrive.chicksdrive.uiActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
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

import com.android.chickdrive.chicksdrive.R;
import com.android.chickdrive.chicksdrive.fragments.HomeFragment;
import com.android.chickdrive.chicksdrive.fragments.MoreFragment;
import com.android.chickdrive.chicksdrive.fragments.RatesFragment;
import com.android.chickdrive.chicksdrive.fragments.RatesSearchFragment;
import com.android.chickdrive.chicksdrive.utils.Constants;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {

    // widgets
    private TabLayout tabLayout;
    public static TextView tv_appBar_title;
    public static ImageView img_filter;

    private ColorStateList colors;
    private Boolean exit = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static Toolbar toolbar;
    public static ActionBarDrawerToggle toggle;
    public static NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));
        navigationView.setItemIconTintList(null);


        //init custom views
        init();
        //adding tabs
        addTabs(tabLayout);

        this.setTitle("");

        tabLayout.setOnTabSelectedListener(this);

        changeStateColorTab(tabLayout, colors);
        tabLayout.getTabAt(0).select();

    }

    private void addTabs(TabLayout tabLayout) {

        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setText("Rates").setIcon(R.drawable.ic_rate));
        tabLayout.addTab(tabLayout.newTab().setText("Search").setIcon(R.drawable.ic_search));
        tabLayout.addTab(tabLayout.newTab().setText("MyListing").setIcon(R.drawable.ic_my_listings));
        tabLayout.addTab(tabLayout.newTab().setText("More").setIcon(R.drawable.ic_more));
        tabLayout.setSmoothScrollingEnabled(true);
    }

    private void init() {

        tabLayout = findViewById(R.id.tabLayout);
        tv_appBar_title = findViewById(R.id.tv_appBar_title);
        img_filter = findViewById(R.id.img_filter);


    }

    private void changeStateColorTab(TabLayout tabLayout, ColorStateList colors) {

        if (Build.VERSION.SDK_INT >= 23) {
            colors = getResources().getColorStateList(R.color.tab_icon, getTheme());
        } else {
            colors = getResources().getColorStateList(R.color.tab_icon);
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable icon = tab.getIcon();

            if (icon != null) {
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTintList(icon, colors);
            }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
        /* else {

         *//* String title="Exit";
                String message="Are you sure you want to exit ?";
                exitDialog(title,message);*//*


            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }

        }*/
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

        if (id == R.id.messages) {

            // Handle the camera action
        } else if (id == R.id.edit_profile) {


        } else if (id == R.id.ads) {


        } else if (id == R.id.about_us) {


        } else if (id == R.id.contact_us) {


        } else if (id == R.id.share_app) {


        } else if (id == R.id.rate_app) {


        } else if (id == R.id.sign_out) {


            logOutDialog("LOGOUT", "ARE YOU SURE YOU WANT TO LOGOUT");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        Fragment fragment;
        switch (tab.getPosition()) {
            case 0:

                tv_appBar_title.setText("Home");
                img_filter.setVisibility(View.GONE);
                fragment = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().addToBackStack("Home");
                ft.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Home");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

                break;

            case 1:

                tv_appBar_title.setText("Today's Rates");
                img_filter.setVisibility(View.VISIBLE);
                fragment = new RatesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

                break;

            case 2:
              /*  tv_appBar_title.setText("Today's Rates");
                img_filter.setVisibility(View.VISIBLE);
                fragment = new RatesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();*/
                break;
            case 3:
               /* tv_appBar_title.setText("Today's Rates");
            img_filter.setVisibility(View.VISIBLE);
            fragment = new RatesFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();*/
                break;
            case 4:

                tv_appBar_title.setText("More");
                img_filter.setVisibility(View.GONE);
                fragment = new MoreFragment();
                FragmentTransaction MoreFragmenttransaction = getSupportFragmentManager().beginTransaction();
                MoreFragmenttransaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("More");
                MoreFragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                MoreFragmenttransaction.commit();

                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

        Fragment fragment;
        switch (tab.getPosition()) {
            case 0:

                img_filter.setVisibility(View.GONE);

                tv_appBar_title.setText("Home");
                fragment = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

                break;
            case 1:

                tv_appBar_title.setText("Today's Rates");
                fragment = new RatesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
                ;
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

                break;

            case 2:
             /*   tv_appBar_title.setText("Today's Rates");
                img_filter.setVisibility(View.VISIBLE);
                fragment = new RatesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();*/
                break;
            case 3:
             /*   tv_appBar_title.setText("Today's Rates");
            img_filter.setVisibility(View.VISIBLE);
            fragment = new RatesFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("Today's Rates");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();*/
                break;

            case 4:

                tv_appBar_title.setText("More");
                img_filter.setVisibility(View.GONE);
                fragment = new MoreFragment();
                FragmentTransaction MoreFragmenttransaction = getSupportFragmentManager().beginTransaction();
                MoreFragmenttransaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack("More");
                MoreFragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                MoreFragmenttransaction.commit();

                break;
        }

    }

    public void logOutDialog(String title, String message) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        sharedPreferences = getSharedPreferences(Constants.MY_PREF_LOGIN, Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        editor.commit();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); //
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
