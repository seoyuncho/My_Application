package com.example.myapplication;

import java.util.List;

public class Playlist {
    private String playlistName;
    private List<String> songs;
    private String owner;

    public Playlist(String playlistName, List<String> songs) {
        this.playlistName = playlistName;
        this.songs = songs;
        this.owner = owner;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<String> getSongs() {
        return songs;
    }

    public String getOwner() {
        return owner;
    }
}
