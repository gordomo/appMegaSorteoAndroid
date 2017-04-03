package com.vetasrl.appmegasorteo;

import android.app.Application;

/**
 * Created by mmori on 6/2/2017.
 */

public class Globals extends Application {
    private String news;

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

}
