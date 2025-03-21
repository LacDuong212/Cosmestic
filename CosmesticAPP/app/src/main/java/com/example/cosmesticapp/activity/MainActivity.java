package com.example.cosmesticapp.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cosmesticapp.Fragments.HomeFragment;
import com.example.cosmesticapp.Fragments.ProfileFragment;
import com.example.cosmesticapp.Fragments.SettingsFragment;
import com.example.cosmesticapp.Fragments.SupportFragment;
import com.example.cosmesticapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_bottommenu);

        // load button action
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // do something
            Snackbar.make(view, "Sorry, out of order!", Snackbar.LENGTH_INDEFINITE)
                    .setDuration(500).show();
        });

        // load fragment, default is HomeFragment
        botNav = findViewById(R.id.botNav);
        botNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (item.getItemId() == R.id.nav_support) {
                selectedFragment = new SupportFragment();
            } else if (item.getItemId() == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_mainContent, selectedFragment)
                        .commit();
            }

            return true;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_mainContent, new HomeFragment())
                    .commit();
        }

    }
}