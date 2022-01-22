package com.sanjeevani.adapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements MediaPlayer.OnCompletionListener {
    ProgressBar spiiner;
    ImageView fullScreenOp;
    FrameLayout frameLayout;

    VideoView vw;
    ArrayList<Integer> videolist = new ArrayList<>();
    int currvideo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        frameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));




        vw = (VideoView)findViewById(R.id.vidvw);

        vw.setMediaController(new MediaController(this));
        vw.setLayoutParams(new FrameLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
        vw.setOnCompletionListener(this);

        // video name should be in lower case alphabet.
        videolist.add(R.raw.venice);
        videolist.add(R.raw.count);
        videolist.add(R.raw.birds);
        videolist.add(R.raw.countryside);

        setVideo(videolist.get(0));
    }

    public void setVideo(int id)
    {
        String uriPath
                = "android.resource://"
                + getPackageName() + "/" + id;
        Uri uri = Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }

    public void onCompletion(MediaPlayer mediapalyer)
    {
//        AlertDialog.Builder obj = new AlertDialog.Builder(this);
//        obj.setTitle("Playback Finished!");
//        obj.setIcon(R.mipmap.ic_launcher);
//        MyListener m = new MyListener();
//        obj.setPositiveButton("Replay", m);
//        obj.setNegativeButton("Next", m);
//        obj.setMessage("Want to replay or play next video?");
//        obj.show();
        ++currvideo;
        if (currvideo == videolist.size())
            currvideo = 0;
        setVideo(videolist.get(currvideo));
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which == -1) {
                vw.seekTo(0);
                vw.start();
            }
            else {
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo = 0;
                setVideo(videolist.get(currvideo));
            }
        }
    }
}