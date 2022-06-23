package com.example.healthtemiapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class SquatLevel1 extends AppCompatActivity {

    private String videoId = "q6hBSSfokzY";
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView level1= (YouTubePlayerView) findViewById(R.id.youtube_player_view1);
    //getLifecycle().addObserver(level1);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat_level1);
        final String[] videoIds = {"q6hBSSfokzY", "RJ5iWuUhiy4"};
        Button button1 = (Button) findViewById(R.id.btnbeginner);
        Button button2 = (Button) findViewById(R.id.btnintermediate);
        Button button3 = (Button) findViewById(R.id.btnexpert);
        //YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        ///getLifecycle().addObserver(level1);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)

            {
                videoId ="RJ5iWuUhiy4";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        level1.addYouTubePlayerListener((new AbstractYouTubePlayerListener() {
            @Override

            public void onReady(@NonNull YouTubePlayer youTubePlayer2)
            {

                youTubePlayer = youTubePlayer2;
                youTubePlayer.cueVideo(videoId, 0);
            }
        }));








        //button2.setOnClickListener(new View.OnClickListener()
        //{
        //    @Override
        //    public void onClick(View view)
        //    {
        //    }
        //});
        //button3.setOnClickListener(new View.OnClickListener()
        //{
          //  @Override
          //  public void onClick(View view)
          //  {
          //  }
        //});
    }
}