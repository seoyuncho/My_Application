// ThirdFragment.java

package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserPlaylistAdapter userPlaylistAdapter;
    private ArrayList<UserPlaylist> userPlaylistArrayList;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        return view;
    }

    private ArrayList<UserPlaylist> getUserPlaylist() {
        // 여기서 네트워크나 로컬 DB에서 데이터를 가져오는 로직을 구현해야 해


        // 아래는 임시 데이터 예시야. 실제 데이터를 가져오는 로직으로 대체해야 해
        ArrayList<UserPlaylist> playlists = new ArrayList<>();
        playlists.add(new UserPlaylist("Song1", "Singer1"));
        playlists.add(new UserPlaylist("Song2", "Singer2"));
        playlists.add(new UserPlaylist("Song3", "Singer3"));
        return playlists;
    }

    private void showUserInfoDialog() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_user_info, null);

        // 여기서 네트워크나 로컬 DB에서 유저 정보를 가져와서 설정해야 함
        // 임시 데이터 예시
        String userID = "user123";
        String userPassword = "password123";
        String userName = "John Doe";
        String userAge = "25";

        TextView textViewUserID = dialogView.findViewById(R.id.textViewUserID);
        TextView textViewUserPassword = dialogView.findViewById(R.id.textViewUserPassword);
        TextView textViewUserName = dialogView.findViewById(R.id.textViewUserName);
        TextView textViewUserAge = dialogView.findViewById(R.id.textViewUserAge);

        textViewUserID.setText("ID: " + userID);
        textViewUserPassword.setText("Password: " + userPassword);
        textViewUserName.setText("Name: " + userName);
        textViewUserAge.setText("Age: " + userAge);

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
