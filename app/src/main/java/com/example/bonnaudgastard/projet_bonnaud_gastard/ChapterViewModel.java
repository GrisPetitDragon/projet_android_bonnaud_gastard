package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.VideoView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ChapterViewModel extends ViewModel {
    private final static Logger LOGGER = Logger.getLogger(ViewModel.class.getName());

    private List<Chapter> chapterList;

    private MutableLiveData<Integer> positionTemporelleLiveData;

    public MutableLiveData<Integer> getPositionTemporelleLiveData() {
        return positionTemporelleLiveData;
    }

    public void setPositionTemporelleLiveData(Integer nvellePositionTemporelle) {
        this.positionTemporelleLiveData.setValue(nvellePositionTemporelle);
    }

    public MutableLiveData<List<Chapter>> getChapterListLiveData() {
        return chapterListLiveData;
    }

    public void setChapterListLiveData(MutableLiveData<List<Chapter>> chapterListLiveData) {
        this.chapterListLiveData = chapterListLiveData;
    }

    public MutableLiveData<Chapter> getCurrentChapterLiveData() {
        return currentChapterLiveData;
    }

    public void setCurrentChapterLiveData(MutableLiveData<Chapter> currentChapterLiveData) {
        this.currentChapterLiveData = currentChapterLiveData;
    }

    //on utilise Mutable pour que les LiveData ne soient modifiables que depuis ce ViewModel
    private MutableLiveData<List<Chapter>> chapterListLiveData =
            new MutableLiveData<>();

    //On sauvegarde à part le chapitre courant, car c'est lui qui est le plus à même de changer au cours de l'utilisation de l'application
    private MutableLiveData<Chapter> currentChapterLiveData =
            new MutableLiveData<>();

    private VideoView videoView;

    String json;

    public ChapterViewModel(Context context) {
        super();

        List<Chapter> chapterList = new LinkedList<Chapter>();

        //lecture du JSON
        String json = loadJson(context);
        //Création de la liste des chapitres
        chapterList = initChapterList(context, json);
        //Mise à disposition des observateurs
        chapterListLiveData.setValue(chapterList);
        // On récupère le premier chapitre

        Chapter currentChapter = chapterListLiveData.getValue().get(0);
        // On la transmet aux observateurs comme étant le chapitre courant
       currentChapterLiveData.setValue(currentChapter);

        //on fixe le point de départ de la vidéo à 0
        positionTemporelleLiveData = new MutableLiveData<Integer>();
        positionTemporelleLiveData.setValue(currentChapterLiveData.getValue().getPosition());


    }

    /**
     * Change la valeur du chapitre courant pour l'ensemble des observateurs du chapitre courant
     *
     * @param newCurrentChapter
     */
    private void setCurrentChapter(Chapter newCurrentChapter) {
        //on utilise postValue pour que ce soit asynchrone, autrement ça va bloquer l'appli;
        currentChapterLiveData.postValue(newCurrentChapter);
    }


    /**
     * Charge le JSON en tant que String
     *
     * @param context
     * @return
     */
    private String loadJson(Context context) {

        try {
            InputStream is = context.getAssets().open("chapters.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Problème lors de la lecture du fichier JSON");

        }
        return json;

    }


    /**
     * Initialise les boutons et l'url de la page web
     *
     * @param context
     * @param json
     */
    private List<Chapter> initChapterList(Context context, String json) {
        List<Chapter> chapList = new LinkedList<Chapter>();
        try {


            JSONObject obj = new JSONObject(json);
            String content = obj.getString("content");
            JSONObject chapters = new JSONObject(content);

            Iterator<String> chapterIterator = chapters.keys();
            JSONObject jsonObject;
            while (chapterIterator.hasNext()) {
                String currentKey = (String) chapterIterator.next();

                jsonObject = chapters.getJSONObject(currentKey);

                Chapter chapter = new Chapter();
                Log.i("INFO", chapter.toString());
                chapter.setName(jsonObject.getString("name"));
                try {
                    chapter.setUrl(new URL(jsonObject.getString("url")));
                } catch (MalformedURLException e) {
                    Log.e("ERREUR", "URL mal formatée");
                    e.printStackTrace();
                }
                chapter.setPosition(jsonObject.getInt("position"));

                //on ajoute le nouveau chapitre à la liste
                chapList.add(chapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chapList;

    }

    /**
     * Sets the videoView to the new position
     *
     * @param position
     */
    public void majVideo(int position) {
        videoView.seekTo(position);
    }

    public List<Chapter> getChapterList() {
        return this.chapterList;
    }

    public void setVideoView(VideoView videoView) {
        this.videoView = videoView;
    }
}

