package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendPlaylistAdapter adapter;
//    private List<PlaylistItem> friendPlaylists;
    private List<PlaylistItem> friendList;

    public Bundle bundle;

    private RecyclerView recyclerView2;
    private UserPlaylistAdapter userPlaylistAdapter;
    private ArrayList<PlaylistItem> userPlaylistArrayList;

    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
//        friendPlaylists = generateSamplePlaylists(); // Replace with your data retrieval logic
        if (getArguments() != null) {
            bundle = getArguments();
            friendList = getFriendList();
            adapter = new FriendPlaylistAdapter(friendList);

            // Set click listener for RecyclerView items
            adapter.setOnItemClickListener(new FriendPlaylistAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Handle item click, e.g., load user playlist for the clicked friend
                    // Implement your logic to load user playlist based on userId
                    // For example, you can start a new fragment or activity to display the user playlist
                    // You might want to pass the userId to the new fragment or activity
                    // For simplicity, let's just log the userId for now
                    PlaylistItem clickedPlaylist = friendList.get(position);
                    // Create a new instance of SecondFragment and pass the data using a Bundle
                    SecondFragment secondFragment = new SecondFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("userID", clickedPlaylist.getFriends()); // Adjust the key and value accordingly
                    secondFragment.setArguments(bundle2);

                    // Navigate to SecondFragment
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, secondFragment); // R.id.fragment_container is the container view in your layout
                    fragmentTransaction.addToBackStack(null); // Optional: Add to back stack if you want to navigate back
                    fragmentTransaction.commit();
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        } else {
            // getArguments() is null, you may want to handle this case or retry
            Log.e("FirstFragment", "Arguments are null, data not available yet.");
        }
    }

    private ArrayList<PlaylistItem> getFriendList() {
        ArrayList<PlaylistItem> friendList = new ArrayList<>();
        // Replace with your logic to fetch friend playlists
        if (bundle != null) {
            String friends = bundle.getString("friends");
            try {
                // Parse the JSON array string
                JSONArray friendsData = new JSONArray(friends);

                // Iterate through the playlistData and create UserPlaylist objects
                for (int i = 0; i < friendsData.length(); i++) {
                    JSONObject friendInfo = friendsData.getJSONObject(i);
                    friendList.add(new PlaylistItem(friendInfo.getString("friends"), "playlist", R.drawable.ic_music_foreground));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
            }
        }
        return friendList;
    }
}
