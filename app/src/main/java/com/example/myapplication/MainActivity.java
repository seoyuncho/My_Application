package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;




public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.person);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        String userAge = intent.getStringExtra("userAge");

        bundle.putString("userID", userID);
        bundle.putString("userPassword", userPassword);
        bundle.putString("userName", userName);
        bundle.putString("userAge", userAge);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        thirdFragment.setArguments(bundle);

        if (item.getItemId() == R.id.person) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, firstFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, secondFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.settings) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();
            return true;
        }
        return false;
    }
}