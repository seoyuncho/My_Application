package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendPlaylistAdapter extends RecyclerView.Adapter<FriendPlaylistAdapter.ViewHolder> {

    private List<PlaylistItem> friendLists;

    public FriendPlaylistAdapter(List<PlaylistItem> friendLists) {
        this.friendLists = friendLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistItem playlistItem = friendLists.get(position);
        holder.friendTextView.setText(playlistItem.getFriends());
        holder.playlistNameTextView.setText(playlistItem.getPlaylistName());
        holder.playlistImageView.setImageResource(playlistItem.getPlaylistImageRes());
    }

    @Override
    public int getItemCount() {
        return friendLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView friendTextView;
        TextView playlistNameTextView;
        ImageView playlistImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendTextView = itemView.findViewById(R.id.friendTextView);
            playlistNameTextView = itemView.findViewById(R.id.playlistNameTextView);
            playlistImageView = itemView.findViewById(R.id.playlistImageView);
        }
    }
}
