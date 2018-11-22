package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ChapterViewModel extends ViewModel {
    private List<Chapter> chapterList;



    /**
     * Récupère le fichiers JSON qui décrit les chapitres et en fait une liste utilisable par notre application
     */
    private List <Chapter> readJson() {
        List<Chapter> list = new LinkedList<Chapter>();

        //On récupère le contenu

        //Cet objet va nous permettre de récupérer les informations
        //JSONObject lesChapitres =new JSONObject(jsonString);

        //for (int i=0;i<lesChapitres.length();i++)
          //  chapitreJson = lesChapitres[i];
            //String aJsonString = jObject.getString("STRINGNAME");

        return list;
    }
}
