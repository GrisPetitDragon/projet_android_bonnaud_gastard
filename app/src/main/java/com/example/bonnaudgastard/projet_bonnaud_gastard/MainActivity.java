package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création du ViewModel
        ChapterViewModel chaptersViewModel = ViewModelProviders.of(this).get(ChapterViewModel.class);

        //Création de la vue
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        //Récupération de la vidéo
        String url = "https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4";
        Uri videoUri = Uri.parse(url);
        videoView.setVideoURI(videoUri);

        //Ajout des éléments de contrôle
        MediaController mediaController = new MediaController(this);
        //disposition sur la vidéo
        mediaController.setAnchorView(videoView);
        //ajout à la vidéo
        videoView.setMediaController(mediaController);

        //Passage au premier plan
        videoView.requestFocus();

        //Une fois la vidéo chargée, on la traite
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        chaptersViewModel.initApp(this);





    }





}
