package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.camera2.CameraExtensionSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserPlaylistAdapter userPlaylistAdapter2;
    private ArrayList<UserPlaylist> userPlaylistArrayList;
    public Bundle bundle;
    public JSONArray songRows;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bundle = getArguments();
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        String userID = bundle.getString("userID");
        getUserSongs(userID, new MainActivity.SongCallback() {
            @Override
            public void onSongsReceived(JSONArray songRows) {
                // Handle the received songs (songRows)
                Log.d("VolleyResponse", "Songs: " + songRows.toString());

                recyclerView = view.findViewById(R.id.recyclerView2);
                userPlaylistArrayList = getUserPlaylist(songRows);

                userPlaylistAdapter2 = new UserPlaylistAdapter(userPlaylistArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(userPlaylistAdapter2);

                // Set item click listener for the recyclerView

                userPlaylistAdapter2.setOnItemClickListener(new UserPlaylistAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // Handle item click here
                        // You can get the clicked UserPlaylist object from the userPlaylistArrayList
                        UserPlaylist clickedPlaylist = userPlaylistArrayList.get(position);

                        // PlayerActivity 호출
                        Intent intent = new Intent(getActivity(),PlayerActivity.class);
                        intent.putExtra("start", "SecondFragment");
                        intent.putExtra("songname", clickedPlaylist.getSongName());
                        intent.putExtra("singer", clickedPlaylist.getSinger());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error
                Log.e("VolleyError", "Error: " + errorMessage);
            }
        });

//
//        recyclerView = view.findViewById(R.id.recyclerView);
//        userPlaylistArrayList = getUserPlaylist(songRows); // 이 부분은 네트워크나 로컬 DB에서 데이터를 가져오는 로직을 추가해야 할 거야



        return view;
    }

    public ArrayList<UserPlaylist> getUserPlaylist(JSONArray songRows) {
        // 여기서 네트워크나 로컬 DB에서 데이터를 가져오는 로직을 구현해야 해
        ArrayList<UserPlaylist> playlists = new ArrayList<>();

        String songrows = songRows.toString();
        try {
            // Parse the JSON array string
            JSONArray playlistData = new JSONArray(songrows);

            // Iterate through the playlistData and create UserPlaylist objects
            for (int i = 0; i < playlistData.length(); i++) {
                JSONObject songInfo = playlistData.getJSONObject(i);
                playlists.add(new UserPlaylist(songInfo.getString("songname"), songInfo.getString("singer")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error
        }

        return playlists;
    }

    public void getUserSongs(String userID, final MainActivity.SongCallback callback) {
        String url = "http://143.248.218.237:3000/user-songs/" + userID;

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
//                            bundle.putString("songRows", songRows.toString());
//                            secondFragment.setArguments(bundle);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}
