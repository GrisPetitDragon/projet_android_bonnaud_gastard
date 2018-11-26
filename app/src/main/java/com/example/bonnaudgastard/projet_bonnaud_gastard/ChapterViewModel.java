package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

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
    String json;

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
     * @param context
     * @param json
     */
    private void initApp(Context context, String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String content = obj.getString("content");
            JSONObject chapters = new JSONObject(content);

            
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void initApp(Context context){
        String json = this.loadJson(context);
        this.initApp(context,json);
    }

    private List<Chapter> readJson() {
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
 * **/
