package com.example.myapplication;

public class UserPlaylist {
    private String songName;
    private String singer;
    public UserPlaylist(String songName, String singer) {
        this.songName = songName;
        this.singer = singer;
    }

    public String getSongName() { return songName; }
    public String getSinger() { return singer;}
}
