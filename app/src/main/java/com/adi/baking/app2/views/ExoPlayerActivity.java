package com.adi.baking.app2.views;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adi.baking.app2.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerActivity extends AppCompatActivity {
    public static final String VIDEO_URL = "videoUrl";
//    private SimpleExoPlayer mExoPlayer;
    SimpleExoPlayer player;
    PlayerView videoFullScreenPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo_layout);


        // Initialize the player view.
        videoFullScreenPlayer = findViewById(R.id.playerView);


//        boolean isNewGame = !getIntent().hasExtra(REMAINING_SONGS_KEY);
//
//        // If it's a new game, set the current score to 0 and load all samples.
//        if (isNewGame) {
//            QuizUtils.setCurrentScore(this, 0);
//            mRemainingSampleIDs = Sample.getAllSampleIDs(this);
//            // Otherwise, get the remaining songs from the Intent.
//        } else {
//            mRemainingSampleIDs = getIntent().getIntegerArrayListExtra(REMAINING_SONGS_KEY);
//        }
//
//        // Get current and high scores.
//        mCurrentScore = QuizUtils.getCurrentScore(this);
//        mHighScore = QuizUtils.getHighScore(this);
//
//        // Generate a question and get the correct answer.
//        mQuestionSampleIDs = QuizUtils.generateQuestion(mRemainingSampleIDs);
//        mAnswerSampleID = QuizUtils.getCorrectAnswerID(mQuestionSampleIDs);
//
//        // Load the question mark as the background image until the user answers the question.
//        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
//                (getResources(), R.drawable.question_mark));
//
//        // If there is only one answer left, end the game.
//        if (mQuestionSampleIDs.size() < 2) {
//            QuizUtils.endGame(this);
//            finish();
//        }
//
//        // Initialize the buttons with the composers names.
//        mButtons = initializeButtons(mQuestionSampleIDs);
//
//        Sample answerSample = Sample.getSampleByID(this, mAnswerSampleID);
//        if (answerSample == null) {
//            Toast.makeText(this, getString(R.string.sample_not_found_error),
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(VIDEO_URL);
            // Initialize the player.
            Log.i("AA_", "onCreate: "+url);
            initializePlayer(Uri.parse(url));
        }
    }

    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        initializePlayer();
        if (player == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
//            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
//            videoFullScreenPlayer.setPlayer(player);

//            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//            TrackSelection.Factory videoTrackSelectionFactory =
//                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
//            TrackSelector trackSelector =
//                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            videoFullScreenPlayer.setPlayer(player);
            // Prepare the MediaSource.
//            String userAgent = Util.getUserAgent(this, "ClassicalMusicQuiz");
//            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
//                    this, userAgent), new DefaultExtractorsFactory(), null, null);

//            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//
//            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                    Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
//            // This is the MediaSource representing the media to be played.
//            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                    .createMediaSource(mUri);
//
//            player.prepare(videoSource);
//            player.setPlayWhenReady(true);
//            player.addListener(this);
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer-codelab", bandwidthMeter);
            Log.i("AA_EXO", "initializePlayer: "+mediaUri);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
//            player.addListener(this);

        }
    }

    private void initializePlayer() {
        if (player == null) {
            // 1. Create a default TrackSelector
//            LoadControl loadControl = new DefaultLoadControl(
//                    new DefaultAllocator(true, 16),
//                    10, 10,10,10
//             , -1, true);


        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
//
//    /**
//     * Release ExoPlayer.
//     */
//    private void releasePlayer() {
//        mExoPlayer.stop();
//        mExoPlayer.release();
//        mExoPlayer = null;
//    }

}
