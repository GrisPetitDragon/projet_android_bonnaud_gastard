package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChapterViewModel extends ViewModel {
    private List<Chapter> chapterList;

    /**
     * Initialise les boutons et l'url de la page web
     * @param context
     * @param json
     */
    private void initApp(Context context, String json) {
        try {
            JSONObject obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * Charge le JSON en tant que String
     * @param context
     * @return
     */
    private String loadJson(Context context){
        String json;

        try {
            InputStream is = context.getAssets().open("chapters.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
