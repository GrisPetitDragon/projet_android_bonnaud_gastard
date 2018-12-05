package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity{
    private ChapterData data = new ChapterData();
    private ChapterViewModel chaptersViewModel;
    private VideoView videoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //data.observe(this, this);

        //Création du ViewModel
        //Toast.makeText(this, "This is my Toast message!", Toast.LENGTH_LONG).show();
        chaptersViewModel = new ChapterViewModel(this);//ViewModelProviders.of(this).get(ChapterViewModel.class);

        //Création de la vue
        this.videoView = (VideoView) findViewById(R.id.videoView);
        // et enregistrement de cette vue dans le ViewModel
        chaptersViewModel.setVideoView(videoView);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebPage());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://en.wikipedia.org/wiki/Big_Buck_Bunny");

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
                chaptersViewModel.setPositionTemporelleLiveData(0);
            }
        });

        chaptersViewModel.getPositionTemporelleLiveData().observe(this, new Observer<Integer>() {
            // update UI
            @Override
            public void onChanged(Integer position) {
                chaptersViewModel.setPositionTemporelleLiveData(position);
                //majVideo();
                //majWebView(position);
                //majChapitreCourant(position);
            }
        });


    }

    public void majVideo (){
        videoView.seekTo(this.chaptersViewModel.getPositionTemporelleLiveData().getValue());
    }

    /**public void onClick(View videoView) {
        chaptersViewModel.setPositionTemporelleLiveData(position);

    }**/


}
