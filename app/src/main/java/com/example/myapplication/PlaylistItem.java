package com.example.myapplication;

public class PlaylistItem {
    private String playlistName;
    private int playlistImageRes;

    public PlaylistItem(String playlistName, int playlistImageRes) {
        this.playlistName = playlistName;
        this.playlistImageRes = playlistImageRes;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public int getPlaylistImageRes() {
        return playlistImageRes;
    }
}