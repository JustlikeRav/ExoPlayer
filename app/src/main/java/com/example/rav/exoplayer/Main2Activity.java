package com.example.rav.exoplayer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(
                new YouTubePlayerInitListener() {

                    @Override
                    public void onInitSuccess(
                            final YouTubePlayer initializedYouTubePlayer) {

                        initializedYouTubePlayer.addListener(
                                new AbstractYouTubePlayerListener() {
                                    @Override
                                    public void onReady() {
                                        initializedYouTubePlayer.loadVideo("vvMF6bhZscQ", 0);
                                    }

                                    @Override
                                    public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
                                        super.onStateChange(state);
                                        if(state.ordinal() == 2) { // 2 is when the video ends
                                            initializedYouTubePlayer.play();
                                        }
                                    }
                                });
                    }


                }, true);

    }
}
