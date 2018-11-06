package com.example.rav.exoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);

        String vidAddress = "https://www.ksivslogan.pw/files/cantlose/1060557289_avc_1080p.mp4";
        Uri vidUri = Uri.parse(vidAddress);

        vidView.setVideoURI(vidUri);
        vidView.start();
    }
}
