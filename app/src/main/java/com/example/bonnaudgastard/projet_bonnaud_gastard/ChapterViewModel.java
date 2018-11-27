package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

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

    //on utilise Mutable pour que les LiveData ne soient modifiables que depuis ce ViewModel
    private MutableLiveData<List<Chapter>> chapterListLiveData =
            new MutableLiveData<>();
    private MutableLiveData<Chapter> currentChapterLiveData =
            new MutableLiveData<>();

    String json;

    private ChapterViewModel() {
        List<Chapter> chapterList = new LinkedList<Chapter>();


        currentChapterLiveData.postValue(chapterList);

        // On récupère le premier chapitre
        Chapter currentChapter = chapterListLiveData.getValue().get(0);
        // On la transmet aux observateurs
        MutableLiveData<Chapter> liveChapter = new MutableLiveData<Chapter>();
        currentChapterLiveData.postValue(currentChapter);


    }


    private void setCurrentChapter(Chapter newCurrentChapter) {
        //on utilise postValue pour que ce soit asynchrone, autrement ça va bloquer l'appli;
        chapterListLiveData.postValue(newCurrentChapter);
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
    private void initChapterList(Context context, String json) {
        try {

            JSONObject obj = new JSONObject(json);
            String content = obj.getString("content");
            JSONObject chapters = new JSONObject(content);

            Iterator<String> chapterIterator = chapters.keys();
            while (chapterIterator.hasNext()) {

                String jsonString = chapterIterator.next();
                Chapter chapter = new Chapter();

                JSONObject jsonObject = new JSONObject(jsonString);
                chapter.setName(jsonObject.getString("name"));
                try {
                    chapter.setUrl(new URL(jsonObject.getString("url")));
                } catch (MalformedURLException e) {
                    Log.e("ERREUR", "URL mal formatée");
                    e.printStackTrace();
                }
                chapter.setPosition(jsonObject.getString("position"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * Method that can be called from the outside e.g. by the activity without bothering with the JSON
     *
     * @param context
     */
    public void initApp(Context context) {
        String json = this.loadJson(context);
        this.initChapterList(context, json);
    }

    public List<Chapter> getChapterList() {
        return this.chapterList;
    }
}
/**
 * }
 * List<Chapter> list = new LinkedList<Chapter>();
 * <p>
 * //On récupère le contenu
 * //Cet objet va nous permettre de récupérer les informations
 * <p>
 * //JSONObject lesChapitres =new JSONObject(jsonString);
 * <p>
 * //for (int i=0;i<lesChapitres.length();i++)
 * //  chapitreJson = lesChapitres[i];
 * //String aJsonString = jObject.getString("STRINGNAME");
 * <p>
 * return list;
 * }
 **/
