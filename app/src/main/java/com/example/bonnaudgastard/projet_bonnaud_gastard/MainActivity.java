package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.app.Activity;
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

import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
    private ChapterData data = new ChapterData();
    private ChapterViewModel chaptersViewModel;
    private VideoView videoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création du ViewModel
        chaptersViewModel = new ChapterViewModel(this);//ViewModelProviders.of(this).get(ChapterViewModel.class);

        //Création des boutons : on en fait par chapitre de la liste des chapitres
        Iterator<Chapter> chpIt = chaptersViewModel.getChapterListLiveData().getValue().iterator();
        while (chpIt.hasNext()) {
            Chapter chpCourant = chpIt.next();
            //on construit les infos du chapitre qui seront intégrées au bouton
            ChapterButton myButton = new ChapterButton(this, chpCourant);
            //L'intérieur du bouton, c'est le nom du chapitre
            myButton.setText(chpCourant.getName());
            LinearLayout ll = (LinearLayout) this.findViewById(R.id.buttonPanel);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //ajout du bouton à la vue
            ll.addView(myButton, lp);
            //définition de ce qu'il y a à faire au clic : mise à jour de la vidéo et de la webview
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chaptersViewModel.setCurrentChapter(myButton.getChapter());
                    majVideo();
                    majWebView();
                }
            });
        }
        //Création de la vue de la vidéo
        this.videoView = (VideoView) findViewById(R.id.videoView);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebPage());
        webView.getSettings().setJavaScriptEnabled(true);
        //on récupère l'URL du chapitre courant chargé plus haut
        webView.loadUrl(chaptersViewModel.getCurrentChapterLiveData().getValue().getUrl().toString());

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
                chaptersViewModel.setPositionTemporelle(0);
            }
        });

        chaptersViewModel.getPositionTemporelleLiveData().observe(this, new Observer<Integer>() {

            // update UI
            @Override
            public void onChanged(Integer position) {
                chaptersViewModel.setPositionTemporelle(position);

                //changer de chapitre
                /*Iterator<Chapter> chpIt = chaptersViewModel.getChapterListLiveData().getValue().iterator();
                Chapter chpALirePrecedent = new Chapter();
                while (chpIt.hasNext()) {
                    Chapter chpConsidere = chpIt.next();
                    // On garde l'ancien au cas où la nouvelle position temporelle serait antérieur au chapitre qu'on vient de récupérer
                    chpALirePrecedent = chpConsidere;
                    if (position> chpConsidere.getPosition()) {
                        chaptersViewModel.setCurrentChapter(chpALirePrecedent);
                        //majWebView();
                    }
                }*/

            }
        });


    }

    private void majWebView() {
        this.webView.loadUrl(chaptersViewModel.getCurrentChapterLiveData().getValue().getUrl().toString());
    }

    public void majVideo() {
        //videoView.seekTo(this.chaptersViewModel.getPositionTemporelleLiveData().getValue());
        videoView.seekTo(this.chaptersViewModel.getCurrentChapterLiveData().getValue().getPosition());
        videoView.start();
    }

    /**public void onClick(View videoView) {
     chaptersViewModel.setPositionTemporelleLiveData(position);

     }**/

    /**clickButton.setOnClickListener( new OnClickListener() {

    @Override public void onClick(View v) {
    // TODO Auto-generated method stub
     ***Do what you want with the click here***
    }
    });**/


}
