package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackgroundColor(Color.BLACK);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();

        getUserFriends("id", new FriendCallback() {
            @Override
            public void onFriendsReceived(JSONArray friends) {
//                // Handle the received songs (songRows)
                Log.d("VolleyResponse", "Friends: " + friends.toString());
                bundle.putString("friends", friends.toString());
                firstFragment.setArguments(bundle); // Set arguments before fragment transactions
            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error
                Log.e("VolleyError", "Error: " + errorMessage);
            }
        });

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        String userAge = intent.getStringExtra("userAge");

        bundle.putString("userID", userID);
        bundle.putString("userPassword", userPassword);
        bundle.putString("userName", userName);
        bundle.putString("userAge", userAge);

        getUserSongs("id", new SongCallback() {
            @Override
            public void onSongsReceived(JSONArray songRows) {
                // Handle the received songs (songRows)
                Log.d("VolleyResponse", "Songs: " + songRows.toString());

            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error
                Log.e("VolleyError", "Error: " + errorMessage);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.player);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.player) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, secondFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, firstFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.person) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();
            return true;
        }
        return false;
    }

    public interface SongCallback {
        void onSongsReceived(JSONArray songRows);
        void onError(String errorMessage);
    }

    public interface FriendCallback {
        void onFriendsReceived(JSONArray friends);
        void onError(String errorMessage);
    }


    public void getUserSongs(String userID, final SongCallback callback) {
        String url = "http://143.248.218.164:3000/user-songs/" + userID;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Assuming the songs are stored as a JSONArray in the response
                            JSONArray songRows = response.getJSONArray("songs");
                            bundle.putString("songRows", songRows.toString());
                            firstFragment.setArguments(bundle);
                            secondFragment.setArguments(bundle);
                            thirdFragment.setArguments(bundle);
                            callback.onSongsReceived(songRows);
                        } catch (JSONException e) {
                            callback.onError("Error parsing JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Network error: " + error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public void getUserFriends(String userID, final FriendCallback callback) {
        String url = "http://143.248.218.164:3000/user-friends/" + userID;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Assuming the songs are stored as a JSONArray in the response
                            JSONArray friends = response.getJSONArray("friends");
                            bundle.putString("friends", friends.toString());
                            firstFragment.setArguments(bundle);
                            callback.onFriendsReceived(friends);
                        } catch (JSONException e) {
                            callback.onError("Error parsing JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Network error: " + error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}