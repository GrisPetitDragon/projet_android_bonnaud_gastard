package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Observer {
    ChapterData data = new ChapterData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.observe(this, this);
    }

    @Override
    public void onChanged(@Nullable Object o) {

    }
}
