package com.itb.itbbar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.itb.itbbar.Fragments.BottomNav_Frags.Events_Fragment;
import com.itb.itbbar.Fragments.MenuFragment;
import com.itb.itbbar.Fragments.BottomNav_Frags.Reservations_Fragment;
import com.itb.itbbar.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar2;
    TextView toolbar_title;
    FrameLayout container;
    Fragment selectedFragment = null;
    BottomNavigationView bottomNavigationView;
    NavigationView navigation_view;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    RecyclerView rv_navigation_list;
    private List<String> navList = new ArrayList<>();
    private ImageView toolbar_menu_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        toolbar_menu_button.setOnClickListener(this);
        loadFragment(new Reservations_Fragment());
        setBottomNavigation();

    }

    private void setBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_reservation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new Reservations_Fragment());
    }


    private void initViews() {
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Pipe & Barrel");
        toolbar_menu_button = findViewById(R.id.toolbar_menu_button);
        toolbar_menu_button.setVisibility(View.VISIBLE);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        container = findViewById(R.id.container);
        setSupportActionBar(toolbar2);
        drawerLayout = findViewById(R.id.drawerLayout);

       /* navList = Arrays.asList(this.getResources().getStringArray(R.array.navigation_list));
        navigationAdapter =  new NavigationAdapter(this,navList);
        rv_navigation_list.setAdapter(navigationAdapter);
        navigationAdapter.setClickOnInterface2(new NavigationAdapter.itemCLickListener() {
            @Override
            public void listeners(int position) {
                if (position==0){
                  drawerLayout.closeDrawers();
                }else if(position==1){
                    Navigation.findNavController(container).navigate(R.id.action_main_Fragment_to_offers_Fragment);
//                    Intent intent = new Intent(getApplicationContext(), .class);
//                    startActivity(intent);
                }else if(position==2){
//                    Intent intent = new Intent(getBaseContext(), OrderHistory.class);
//                    startActivity(intent);
                }else if(position==3){
//                    Intent intent = new Intent(getApplicationContext(), MyAddressFragment.class);
//                    startActivity(intent);
                }else if(position==4){
//                    Intent intent = new Intent(getApplicationContext(), OrderHistory.class);
//                    startActivity(intent);
                }
                else if(position==5){
//                    Intent intent = new Intent(getApplicationContext(), NotificationFragment.class);
//                    startActivity(intent);
                }
                else if(position==6){
//                    Intent intent = new Intent(getApplicationContext(), Help_and_Support.class);
//                    startActivity(intent);
                }
                else if(position==7){
//                    Intent intent = new Intent(getApplicationContext(), OrderHistory.class);
//                    startActivity(intent);
                }

            }
        });
*/
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_menu_button: {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_nav_reservation:
                selectedFragment = new Reservations_Fragment();
                loadFragment(selectedFragment);
                return true;

            case R.id.bt_nav_menu:
                selectedFragment = new MenuFragment();
                loadFragment(selectedFragment);
                return true;

            case R.id.bt_nav_events:
                selectedFragment = new Events_Fragment();
                loadFragment(selectedFragment);
                return true;
        }
        return false;

    }
}