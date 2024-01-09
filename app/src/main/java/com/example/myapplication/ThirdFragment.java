// ThirdFragment.java

package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserPlaylistAdapter userPlaylistAdapter;
    private ArrayList<UserPlaylist> userPlaylistArrayList;
    public Bundle bundle;
    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bundle = getArguments();
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        userPlaylistArrayList = getUserPlaylist(); // 이 부분은 네트워크나 로컬 DB에서 데이터를 가져오는 로직을 추가해야 할 거야

        userPlaylistAdapter = new UserPlaylistAdapter(userPlaylistArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userPlaylistAdapter);

        view.findViewById(R.id.settingsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserInfoDialog();
            }
        });

        // Set item click listener for the recyclerView
        userPlaylistAdapter.setOnItemClickListener(new UserPlaylistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here
                // You can get the clicked UserPlaylist object from the userPlaylistArrayList
                UserPlaylist clickedPlaylist = userPlaylistArrayList.get(position);

                // PlayerActivity 호출
                Intent intent = new Intent(getActivity(),PlayerActivity.class);
                intent.putExtra("start", "ThirdFragment");
                intent.putExtra("songname", clickedPlaylist.getSongName());
                intent.putExtra("singer", clickedPlaylist.getSinger());
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }

    private ArrayList<UserPlaylist> getUserPlaylist() {
        // 여기서 네트워크나 로컬 DB에서 데이터를 가져오는 로직을 구현해야 해
        ArrayList<UserPlaylist> playlists = new ArrayList<>();

        if (bundle != null) {
            String songRows = bundle.getString("songRows");
            try {
                // Parse the JSON array string
                JSONArray playlistData = new JSONArray(songRows);

                // Iterate through the playlistData and create UserPlaylist objects
                for (int i = 0; i < playlistData.length(); i++) {
                    JSONObject songInfo = playlistData.getJSONObject(i);
                    playlists.add(new UserPlaylist(songInfo.getString("songname"), songInfo.getString("singer")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
            }
        }
        return playlists;
    }

    private void showUserInfoDialog() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_user_info, null);

        if (bundle != null) {
            String userID = bundle.getString("userID");
            String userPassword = bundle.getString("userPassword");
            String userName = bundle.getString("userName");
            String userAge = bundle.getString("userAge");

            TextView textViewUserID = dialogView.findViewById(R.id.textViewUserID);
            TextView textViewUserPassword = dialogView.findViewById(R.id.textViewUserPassword);
            TextView textViewUserName = dialogView.findViewById(R.id.textViewUserName);
            TextView textViewUserAge = dialogView.findViewById(R.id.textViewUserAge);

            textViewUserID.setText("ID: " + userID);
            textViewUserPassword.setText("Password: " + userPassword);
            textViewUserName.setText("Name: " + userName);
            textViewUserAge.setText("Age: " + userAge);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView)
                .setTitle("User Information")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
