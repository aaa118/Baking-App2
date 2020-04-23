package com.adi.baking.app2.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adi.baking.app2.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerActivity extends AppCompatActivity {
    public static final String VIDEO_URL = "videoUrl";
    private static final String PLAYER_POSITION = "playerPosition";
    public static final String STATE = "state";
    SimpleExoPlayer player;
    PlayerView videoFullScreenPlayer;
    long playerPosition;
    boolean playerState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo_layout);
        // Initialize the player view.
        videoFullScreenPlayer = findViewById(R.id.playerView);

        if (savedInstanceState != null) {
            playerState = savedInstanceState.getBoolean(STATE);
            playerPosition = savedInstanceState.getLong(PLAYER_POSITION);
        }
        else {
            playerState = true;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            checkIntentBeforeInit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23) {
            checkIntentBeforeInit();
        }
    }

    private void checkIntentBeforeInit() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(VIDEO_URL);
            // Initialize the player.
            initializePlayer(Uri.parse(url));
        }
    }

    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            videoFullScreenPlayer.setPlayer(player);
            // Prepare the MediaSource.
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer-codelab", bandwidthMeter);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);
            player.prepare(mediaSource);
            player.seekTo(playerPosition);
            player.setPlayWhenReady(playerState);

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        playerPosition = player.getCurrentPosition();
        outState.putLong(PLAYER_POSITION, playerPosition);
        playerState = player.getPlayWhenReady();
        outState.putBoolean(STATE, playerState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

}
