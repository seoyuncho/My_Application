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
    private OnItemClickListener listener;

    public UserPlaylistAdapter(ArrayList<UserPlaylist> userPlaylistArrayList) {
        this.userPlaylistArrayList = userPlaylistArrayList;
    }


    // Interface for item click handling
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Setter for item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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

        // Set click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
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
