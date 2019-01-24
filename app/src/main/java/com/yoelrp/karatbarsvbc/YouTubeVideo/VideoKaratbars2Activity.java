package com.yoelrp.karatbarsvbc.YouTubeVideo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.yoelrp.karatbarsvbc.R;

/**
 * Created by YoelRP on 14/12/2018.
 */

public class VideoKaratbars2Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener{

    YouTubePlayerView youTubePlayerView;
    String ClaveYouTube="AIzaSyDOujXslrxyf-23HeQcXi4XEGJ_qwT1tks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos_karatbars);

        youTubePlayerView=(YouTubePlayerView) findViewById(R.id.youtube_video_karatbars);
        youTubePlayerView.initialize(ClaveYouTube,this);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

    }




    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fueRestaurado) {
        if (!fueRestaurado){
            youTubePlayer.cueVideo("dzmncWg7sb0");
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else{
            String error="Error al inicializar YouTube"+youTubeInitializationResult.toString();
            Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
        }

    }

    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        if (resultcode==1){

            getYoutubePlayerProvider().initialize(ClaveYouTube,this);

        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return youTubePlayerView;
    }


    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
