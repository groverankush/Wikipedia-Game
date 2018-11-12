package com.ankushgrover.letswiki.data.source;

import com.ankushgrover.letswiki.data.model.title.RandomTitleResponse;
import com.ankushgrover.letswiki.data.source.remote.WikiDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public class DataManager {

    private static DataManager instance;
    private WikiDataSource wikiDataSource;

    private DataManager() {
        wikiDataSource = initDataSource();
    }

    private WikiDataSource initDataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WikiDataSource.class);
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public WikiDataSource getWikiDataSource() {
        return wikiDataSource;
    }
}
