package com.example.myapplication;

public class PlaylistItem {
    private String friends;
    private String playlistName;
    private int like;
//    private String playlistName;
    private int playlistImageRes;


    public PlaylistItem(String friends, String playlistName, int playlistImageRes) {
        this.friends = friends;
        this.playlistName = playlistName;
        this.like = like;
        this.playlistImageRes = playlistImageRes;
    }

    public String getFriends() {
        return friends;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public int getLike() {
        return like;
    }

    public int getPlaylistImageRes() {
        return playlistImageRes;
    }
}