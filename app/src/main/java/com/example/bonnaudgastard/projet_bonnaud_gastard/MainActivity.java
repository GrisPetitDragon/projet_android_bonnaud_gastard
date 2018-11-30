package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<ButtonsData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        data = fill_with_data();

        horizontalAdapter=new HorizontalAdapter(data, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);


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
        /**videoView.observe(this,
        position -> {
            position;
            videoView.seekTo(position);
            // update UI
        });**/
        //Une fois la vidéo chargée, on la traite
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }



    public List<ButtonsData> fill_with_data() {

        List<ButtonsData> data = new ArrayList<>();

        data.add(new ButtonsData(0, "Bouton 1"));
        data.add(new ButtonsData(1, "Bouton 2"));
        data.add(new ButtonsData(2, "Bouton 3"));
        data.add(new ButtonsData(0, "Bouton 1"));
        data.add(new ButtonsData(1, "Bouton 2"));
        data.add(new ButtonsData(2, "Bouton 3"));


        return data;
    }



    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        List<ButtonsData> horizontalList = Collections.emptyList();
        Context context;


        public HorizontalAdapter(List<ButtonsData> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView txtview;
            public MyViewHolder(View view) {
                super(view);
                imageView=(ImageView) view.findViewById(R.id.imageview);
                txtview=(TextView) view.findViewById(R.id.txtview);
            }
        }



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.imageView.setImageResource(horizontalList.get(position).buttonId);
            holder.txtview.setText(horizontalList.get(position).txt);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position).txt.toString();
                    Toast.makeText(MainActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });

        }


        @Override
        public int getItemCount()
        {
            return horizontalList.size();
        }
    }
}
