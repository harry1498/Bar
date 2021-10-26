package com.itb.itbbar.Fragments;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itb.itbbar.Fragments.BottomNav_Frags.Events_Fragment;
import com.itb.itbbar.Fragments.BottomNav_Frags.Reservations_Fragment;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.AboutFragment;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.Admin;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.Help_and_Support;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.MyAddressFragment;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.NotificationFragment;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.OrderHistory;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.Profile;
import com.itb.itbbar.Fragments.Nav_Drawer_Frags.Speciality;
import com.itb.itbbar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.itb.itbbar.Utils.AppUtils;

public class Main_Fragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ImageView toolbar_menu_button;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button toolbar_back_button;
    private TextView nav_header_editProfile, toolbar_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initialisation(view);
        settingUpBottomNav();
        settingUpNavDrawer();
        onClickActions();

        return view;
    }

    private void settingUpNavDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        toolbar_back_button.setVisibility(View.GONE);
                        fragment = new Reservations_Fragment();
                        break;
                    case R.id.nav_order_history:
                        setVisible();
                        setToolbarTitle("Order History");
                        fragment = new OrderHistory();
                        break;
                    case R.id.nav_address:
                        setVisible();
                        setToolbarTitle("My Address");
                        fragment = new MyAddressFragment();
                        break;

                    case R.id.nav_speciality:
                        setVisible();
                        setToolbarTitle("Speciality");
                        fragment = new Speciality();
                        break;
                    case R.id.nav_notification:
                        setVisible();
                        setToolbarTitle("Notification");
                        fragment = new NotificationFragment();
                        break;
                    case R.id.nav_help:
                        setVisible();
                        setToolbarTitle("Help & Support");
                        fragment = new Help_and_Support();
                        break;
                    case R.id.nav_about:

                        setVisible();
                        setToolbarTitle("About");
                        fragment = new AboutFragment();
                        break;
                    case R.id.nav_admin:
                        setVisible();
                        setToolbarTitle("Admin");
                        fragment = new Admin();
                        break;

                }
                // it closes the navigation drawer after a click
                drawerLayout.closeDrawer(GravityCompat.START);
                return loadFragment(fragment);
            }
        });

    }

    private void onClickActions() {
        toolbar_menu_button.setOnClickListener(this);
        toolbar_back_button.setOnClickListener(this);
        nav_header_editProfile.setOnClickListener(this);
    }

    private void settingUpBottomNav() {
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_reservation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new Reservations_Fragment());
    }

    private void initialisation(View view) {
        toolbar_menu_button = view.findViewById(R.id.toolbar_menu_button);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        navigationView = view.findViewById(R.id.mainFragment_navigationView);
        drawerLayout = view.findViewById(R.id.drawerLayout);

        toolbar_back_button = view.findViewById(R.id.toolbar_back_button);
        toolbar_title = view.findViewById(R.id.toolbar_title);

        View headerView = navigationView.getHeaderView(0);
        nav_header_editProfile = headerView.findViewById(R.id.nav_header_editProfile);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.bt_nav_reservation:
                fragment = new Reservations_Fragment();
                toolbar_back_button.setVisibility(View.GONE);
                break;

            case R.id.bt_nav_menu:
                AppUtils.showToast(getContext(), "Menu");
                break;

            case R.id.bt_nav_events:
                fragment = new Events_Fragment();
                setToolbarTitle("Events");
                setVisible();
                break;
        }
        return loadFragment(fragment);
    }

    private void setToolbarTitle(String string) {
        toolbar_title.setText(string);
    }

    private void setVisible() {
        toolbar_back_button.setVisibility(View.VISIBLE);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.bottomNavigation_container, fragment)
                    .commit();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_menu_button:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.toolbar_back_button:
                toolbar_title.setText(R.string.app_name);
                toolbar_back_button.setVisibility(View.GONE);
                bottomNavigationView.setSelectedItemId(R.id.bt_nav_reservation);
                break;

            case R.id.nav_header_editProfile:
                loadFragment(new Profile());
                toolbar_back_button.setVisibility(View.VISIBLE);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }
}