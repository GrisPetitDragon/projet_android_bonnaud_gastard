package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;
import android.widget.Button;

public class ChapterButton extends android.support.v7.widget.AppCompatButton {
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    private Chapter chapter;
    public ChapterButton(Context context) {
        super(context);
    }

    public ChapterButton(Context context, Chapter chapter) {
        super(context);
        this.chapter = chapter;
    }

}
