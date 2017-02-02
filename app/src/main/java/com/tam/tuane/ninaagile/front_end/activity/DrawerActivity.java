package com.tam.tuane.ninaagile.front_end.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.adaptors.ItemsCard;
import com.tam.tuane.ninaagile.front_end.fragments.Engine;
import com.tam.tuane.ninaagile.front_end.fragments.MakeOrder;
import com.tam.tuane.ninaagile.front_end.fragments.Payment_method;
import com.tam.tuane.ninaagile.front_end.fragments.Welcome;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Engine.onConnectActivity
        {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Engine.onConnectActivity onConnectActivity;
    private Engine engineAdaptor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        engineAdaptor = new Engine();
        engineAdaptor.setOnConnectActivity(this);

       /* Button btn = (Button)findViewById(R.id.Btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawerActivity.this, "Work Please", Toast.LENGTH_SHORT).show();
            }
        });*/



        Fragment fragment = new MakeOrder();
        getSupportFragmentManager().
                beginTransaction().replace(R.id.framelayout,
                fragment).commit();


        // adding a fragment !!
       /* Welcome fragment = new Welcome();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, fragment).commit();*/





    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(DrawerActivity.this, "Hello view ?" + i +" "+ view.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);
            if (f instanceof Payment_method) {//the fragment on which you want to handle your back press
                 clean();
            }else{
                super.onBackPressed();
            }
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_clean) {
            clean();
        } else if (id == R.id.nav_location) {
             Help();
        } else if (id == R.id.nav_Terms_Of_Use) {
            termsofUse();
        } else if (id == R.id.nav_privacy_policy) {
            privacypolicy();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void privacypolicy() {

        startActivity(new Intent(DrawerActivity.this, PrivacyPolicy.class));
    }

    private void termsofUse() {
        startActivity(new Intent(DrawerActivity.this, TermsActivity.class));
    }

    private void Help() {
        startActivity(new Intent(DrawerActivity.this, Help.class));
    }

    private void clean() {

        FragmentManager manager =getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        MakeOrder fragment = new MakeOrder();
        getSupportFragmentManager().
                beginTransaction().replace(R.id.framelayout,
                fragment).commit();
    }


    @Override
    public void removeBackStack() {



      /*  FragmentManager manager =getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }*/

    }


}
