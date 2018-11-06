package com.example.rav.exoplayer;

import android.media.MediaCodec;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class MainActivity extends AppCompatActivity {

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videoURL = "https://www.avenview.com/test/MKBHD.mp4";

    DefaultHttpDataSourceFactory dataSourceFactory2;
    ExtractorsFactory extractorsFactory2;
    MediaSource mediaSource2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
            //exoPlayer.setVideoScalingMode(MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

            exoPlayerView.setPlayer(exoPlayer);
            //LoopingMediaSource loopingSource = new LoopingMediaSource(mediaSource);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

            exoPlayer.addListener(new ExoPlayer.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                    if (playbackState == ExoPlayer.STATE_READY){
                        dataSourceFactory2 = new DefaultHttpDataSourceFactory("exoplayer_video");
                        extractorsFactory2 = new DefaultExtractorsFactory();
                        mediaSource2 = new ExtractorMediaSource(Uri.parse("https://www.avenview.com/test/MKBHD2.mp4"), dataSourceFactory2, extractorsFactory2, null, null);
                    }

                    if (playbackState == ExoPlayer.STATE_ENDED){
                        exoPlayer.prepare(mediaSource2);
                        exoPlayer.setPlayWhenReady(true);
                    }
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {

                }

                @Override
                public void onPositionDiscontinuity() {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }
            });

        }catch (Exception e){
            Log.e("MainAcvtivity"," exoplayer error "+ e.toString());
        }

    }
}