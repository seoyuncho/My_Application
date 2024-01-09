package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

public class SecondFragment extends Fragment {

    Button btnprev, btnnext, btnplay, btnff, btnfr;
    TextView txtsname, txtsstart, txtsstop;
    SeekBar seekmusic;
    BarVisualizer visualizer;
    ImageView imageView;

    String sname;
    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<UserPlaylist> mySongs;
    Thread updateseekbar;
    public Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        btnprev = view.findViewById(R.id.btnprev);
        btnnext = view.findViewById(R.id.btnnext);
        btnplay = view.findViewById(R.id.playbtn);
        btnff = view.findViewById(R.id.btnff);
        btnfr = view.findViewById(R.id.btnfr);
        txtsname = view.findViewById(R.id.txtsn);
        txtsstart = view.findViewById(R.id.txtsstart);
        txtsstop = view.findViewById(R.id.txtsstop);
        seekmusic = view.findViewById(R.id.seekbar);
        visualizer = view.findViewById(R.id.blast);
        imageView = view.findViewById(R.id.imageview);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        bundle = getArguments();
//        mySongs = bundle.getParcelableArrayList("songs");
//        String songName = mySongs.get(0).getSongName();
        position = 0;
        txtsname.setSelected(true);
        int rawResourceId = R.raw.congratulations;
//        Uri uri = Uri.parse(mySongs.get(position).getSongPath(rawResourceId));
//        sname = mySongs.get(position).getSongName();
        txtsname.setText(sname);

//        //mediaPlayer = MediaPlayer.create(requireContext(), uri);
////        mediaPlayer.start();
////
////        updateseekbar = new Thread() {
////            @Override
////            public void run() {
////                int totalDuration = mediaPlayer.getDuration();
////                int currentposition = 0;
////
////                while (currentposition < totalDuration) {
////                    try {
////                        sleep(500);
////                        currentposition = mediaPlayer.getCurrentPosition();
////                        seekmusic.setProgress(currentposition);
////                    } catch (InterruptedException | IllegalStateException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        };
////
////        seekmusic.setMax(mediaPlayer.getDuration());
////        updateseekbar.start();
////        seekmusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary
//        seekmusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(seekBar.getProgress());
//            }
//        });
//
//        String endTime = createTime(mediaPlayer.getDuration());
//        txtsstop.setText(endTime);
//
//        final Handler handler = new Handler();
//        final int delay = 1000;
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                String currentTime = createTime(mediaPlayer.getCurrentPosition());
//                txtsstart.setText(currentTime);
//                handler.postDelayed(this, delay);
//            }
//        }, delay);
//
//        btnplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaPlayer.isPlaying()) {
//                    btnplay.setBackgroundResource(R.drawable.ic_play_foreground);
//                    mediaPlayer.pause();
//                } else {
//                    btnplay.setBackgroundResource(R.drawable.ic_stop_foreground);
//                    mediaPlayer.start();
//                }
//            }
//        });
//
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                btnnext.performClick();
//            }
//        });
//
//        int audiosessionId = mediaPlayer.getAudioSessionId();
//        if (audiosessionId != -1) {
//            visualizer.setAudioSessionId(audiosessionId);
//        }
//
//        btnnext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                position = ((position + 1) % mySongs.size());
//                UserPlaylist nextSong = mySongs.get(position);
//                int rawResourceId = R.raw.congratulations;
//                Uri u = Uri.parse(nextSong.getSongPath(rawResourceId));
//                mediaPlayer = MediaPlayer.create(requireContext(), u);
//                sname = nextSong.getSongName();
//                txtsname.setText(sname);
//                mediaPlayer.start();
//                btnplay.setBackgroundResource(R.drawable.ic_stop_foreground);
//                startAnimation(imageView);
//                int audiosessionId = mediaPlayer.getAudioSessionId();
//                if (audiosessionId != -1) {
//                    visualizer.setAudioSessionId(audiosessionId);
//                }
//            }
//        });
//
//        btnprev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
//                UserPlaylist prevSong = mySongs.get(position);
//                Uri u = Uri.parse(prevSong.getSongPath(rawResourceId));
//                mediaPlayer = MediaPlayer.create(requireContext(), u);
//                sname = prevSong.getSongName();
//                txtsname.setText(sname);
//                mediaPlayer.start();
//                btnplay.setBackgroundResource(R.drawable.ic_stop_foreground);
//                startAnimation(imageView);
//                int audiosessionId = mediaPlayer.getAudioSessionId();
//                if (audiosessionId != -1) {
//                    visualizer.setAudioSessionId(audiosessionId);
//                }
//            }
//        });
//
//        btnff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
//                }
//            }
//        });
//
//        btnfr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
//                }
//            }
//        });
//
        return view;
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            requireActivity().onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onDestroy() {
//        if (visualizer != null) {
//            visualizer.release();
//        }
//        super.onDestroy();
//    }
//
//    public void startAnimation(View view) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
//        animator.setDuration(1000);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(animator);
//        animatorSet.start();
//    }
//
//    public String createTime(int duration) {
//        String time = "";
//        int min = duration / 1000 / 60;
//        int sec = duration / 1000 % 60;
//
//        time += min + ":";
//
//        if (sec < 10) {
//            time += "0";
//        }
//        time += sec;
//
//        return time;
//    }
}