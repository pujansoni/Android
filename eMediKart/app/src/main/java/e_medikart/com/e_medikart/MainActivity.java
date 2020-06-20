package e_medikart.com.e_medikart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import e_medikart.com.e_medikart.fragment.About_Us;
import e_medikart.com.e_medikart.fragment.Categories;
import e_medikart.com.e_medikart.fragment.Customer_Support;
import e_medikart.com.e_medikart.fragment.Home;
import e_medikart.com.e_medikart.fragment.ProductFragment;
import e_medikart.com.e_medikart.utils.PreferenceManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        setUpHomeFragment();
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        findheader(headerview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void findheader(View headerview) {

        preferenceManager = new PreferenceManager();
        String userName = PreferenceManager.getprefUserFirstName(MainActivity.this);
        String userEmail = PreferenceManager.getprefUserEmail(MainActivity.this);

        TextView tvName = (TextView) headerview.findViewById(R.id.tv_userName);
        TextView tvEmail = (TextView) headerview.findViewById(R.id.tv_userEmail);
        tvName.setText("Welcome   " + userName);
        tvEmail.setText(userEmail);



    }

    private void setUpHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().add(R.id.content_frame, new Home()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (checkNavigationMenuItem() != 0) {
                navigationView.setCheckedItem(R.id.nav_home);
                Fragment fragment = new Home();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            } else {
                //super.onBackPressed();
                AlertDialog.Builder alertbuilder = new AlertDialog.Builder(MainActivity.this);
                alertbuilder.setTitle("eMediKart");
                alertbuilder.setMessage("Do you want to exit?");
                alertbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertbuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertbuilder.show();
            }
        }
    }

    private int checkNavigationMenuItem() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked())
                return i;
        }
        return -1;
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

       /* //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //creating e_medikart.com.e_medikart.fragment object
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new Home();
        } else if (id == R.id.nav_categories) {
            fragment = new Categories();
        } else if (id == R.id.nav_product) {
            fragment = new ProductFragment();
        } else if (id == R.id.nav_customer_support) {
            fragment = new Customer_Support();
        } else if (id == R.id.nav_about_us) {
            fragment = new About_Us();
        } else if (id == R.id.nav_logout){
            PreferenceManager.removeprefUserId(MainActivity.this, "USER_ID");
            PreferenceManager.removeprefUserFirstName(MainActivity.this, "USER_FIRSTNAME");
            PreferenceManager.removeprefUserLastName(MainActivity.this, "USER_LASTNAME");
            PreferenceManager.removeprefUserEmail(MainActivity.this, "USER_EMAIL");
            PreferenceManager.removeprefUserCheckEmail(MainActivity.this, "USER_CHECK_EMAIL");
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
