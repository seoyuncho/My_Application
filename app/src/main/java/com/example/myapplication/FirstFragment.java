package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendPlaylistAdapter adapter;
    private List<PlaylistItem> friendPlaylists;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        friendPlaylists = generateSamplePlaylists(); // Replace with your data retrieval logic

        adapter = new FriendPlaylistAdapter(friendPlaylists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private List<PlaylistItem> generateSamplePlaylists() {
        List<PlaylistItem> playlists = new ArrayList<>();
        // Replace with your logic to fetch friend playlists
        // Sample data
        playlists.add(new PlaylistItem("Friend 1 Playlist", R.drawable.ic_music_foreground));
        playlists.add(new PlaylistItem("Friend 2 Playlist", R.drawable.ic_music_foreground));
        playlists.add(new PlaylistItem("Friend 3 Playlist", R.drawable.ic_music_foreground));
        return playlists;
    }
}
