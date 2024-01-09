package com.example.myapplication;

import com.gauravk.audiovisualizer.BuildConfig;

public class UserPlaylist {
    private String songName;
    private String singer;
    public UserPlaylist(String songName, String singer) {
        this.songName = songName;
        this.singer = singer;
    }

    public String getSongName() { return songName; }
    public String getSinger() { return singer;}
    public String getSongPath(int rawResourceId) {
        String path = "android.resource://" + BuildConfig.APPLICATION_ID + "/" + rawResourceId;
        return path;
    }
}
