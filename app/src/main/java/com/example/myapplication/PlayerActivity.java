package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {
    MediaPlayer player;
    TextView titleView;
    Button playButton, appStartButton;
    SeekBar seekBar;
    boolean checkplay = false;
    boolean firstcheckplay = false;
    Intent intent;
    ThirdFragment thirdFragment;

    class t1 extends Thread{
        public void run(){
            while(checkplay){
                seekBar.setProgress(player.getCurrentPosition());
            }
        }
    } // 쓰레드를 이용하기 위해 쓰레드 클래스를 생성하자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        intent = getIntent();
        String songname = intent.getStringExtra("songname");


        // 음악 제목
        titleView = findViewById(R.id.titleView);
        titleView.setText(songname);
        // 음악 탐색바
        seekBar = findViewById(R.id.seekBar);

        // Play 버튼. 다시 누르면 Pause 버튼으로 변경
        playButton = findViewById(R.id.playButton);

        // MediaPlayer 음악 재생 관련 객체
        player = MediaPlayer.create(this, R.raw.congratulations);


        player = MediaPlayer.create(this, R.raw.congratulations);
        appStartButton = findViewById(R.id.appStartButton);
        appStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 클릭했을 때 MainActivity로 전환하는 코드
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        seekBar.setMax(player.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean check) {
                if (check) {
                    player.seekTo(progress);
                }
                if (seekBar.getMax()==progress) { //끌어서 마지막으로 놓으면 멈추게 해야 한다.
                    checkplay = false;
                    player.stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {

                checkplay = false;
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekbar) {
                if (firstcheckplay) {
                    checkplay = true;
                    player.seekTo(seekBar.getProgress());
                    player.start();
                    new t1().start();
                } else {
                    checkplay = true;
                    player.seekTo(seekBar.getProgress());
                }
            }
        });
        // 여기까지 seekbar 구현

    }// oncreate의 끝

    @Override
    public void onBackPressed() {
        if (intent.getStringExtra("start") == "ThirdFragment") {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();
        } else if (intent.getStringExtra("start") == "FirstFragment") {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();
        }else {
            super.onBackPressed();
        }
    }

    /**
     * 이 버튼을 누르면 현재 위치에서 5초 뒤로 이동한다.
     * @param view
     */

    public void rewindAction(View view) {

        player.seekTo(player.getCurrentPosition() - 5000);
        seekBar.setProgress(player.getCurrentPosition());// 일시정지 상태에서 이 버튼 눌러도 seekbar가 상태를 반영하게 함.
    }


    ////////////////////////////////////////////////////////////////////////////
    /**
     * 재생 중이 아니면 음악을 재생하고, 재생중이면 음악을 중지한다.
     * @param view
     */

    public void playAction(View view) {
        if (!player.isPlaying()) { // 진행중이 아닐 때
            player.start();
            playButton.setBackgroundResource(R.drawable.stop_icon);
            new t1().start();
            checkplay = true; // seekbar를 그릴 쓰레드를 반복해야함
            firstcheckplay = true; // 정지상태에서 씨크바 드래그드롭으로 조작 시 내가 의도하지 않은 결과가 나온다.
            // (시작 정지 버튼이 꼬이게 됨) firstcheckplay를 도입해서 그걸 해결했음.

        } else { // 진행중일 때
            player.pause();
            playButton.setBackgroundResource(R.drawable.play_icon);
            checkplay = false; // seekbar 쓰레드도 정지함
            firstcheckplay = false; // 정지상태에서 씨크바 드래그드롭으로 조작 시 내가 의도하지 않은 결과가 나온다.
            // (시작 정지 버튼이 꼬이게 됨) firstcheckplay를 도입해서 그걸 해결했음.
        }
    }
    //////////////////////////////////////////////////////////////////


    /**
     * 이 버튼을 누르면 현재 위치에서 5초 앞으로 이동한다.
     * @param view
     */
    public void forwardAction(View view) {
        player.seekTo(player.getCurrentPosition() + 5000);
        seekBar.setProgress(player.getCurrentPosition());// 일시정지 상태에서 이 버튼 눌러도 seekbar가 상태를 반영하게 함.
    }

}