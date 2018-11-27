package com.example.bonnaudgastard.projet_bonnaud_gastard;

import java.net.URL;
import java.sql.Timestamp;

class Chapter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    private String position;
    private URL url;
}
