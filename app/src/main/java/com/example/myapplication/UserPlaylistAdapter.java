// UserPlaylistAdapter.java

package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserPlaylistAdapter extends RecyclerView.Adapter<UserPlaylistAdapter.PlaylistViewHolder> {

    private ArrayList<UserPlaylist> userPlaylistArrayList;

    public UserPlaylistAdapter(ArrayList<UserPlaylist> userPlaylistArrayList) {
        this.userPlaylistArrayList = userPlaylistArrayList;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_playlist, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        UserPlaylist userPlaylist = userPlaylistArrayList.get(position);
        holder.songNameTextView.setText(userPlaylist.getSongName());
        holder.singerTextView.setText(userPlaylist.getSinger());
    }

    @Override
    public int getItemCount() {
        return userPlaylistArrayList.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView songNameTextView;
        TextView singerTextView;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            songNameTextView = itemView.findViewById(R.id.songNameTextView);
            singerTextView = itemView.findViewById(R.id.singerTextView);
        }
    }
}
