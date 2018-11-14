package com.ankushgrover.letswiki.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.util.Log;

import com.ankushgrover.letswiki.data.model.CompleteArticle;
import com.ankushgrover.letswiki.data.model.article.ArticleResponse;
import com.ankushgrover.letswiki.data.model.article.Mobileview;
import com.ankushgrover.letswiki.data.model.title.Article;
import com.ankushgrover.letswiki.data.model.title.RandomTitleResponse;
import com.ankushgrover.letswiki.data.source.DataManager;
import com.ankushgrover.letswiki.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public class GameViewModel extends ViewModel {

    private static final String TAG = GameViewModel.class.getSimpleName();

    public MutableLiveData<CompleteArticle> article = new MutableLiveData<>();


    public void getTitleList() {

        long days = 7 * 24 * 60 * 60 * 1000;
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        String date = df.format(new Date(System.currentTimeMillis() - days));


        Call<RandomTitleResponse> call = DataManager.getInstance().getWikiDataSource().fetchArticleList("https://wikimedia.org/api/rest_v1/metrics/pageviews/top/en.wikipedia.org/all-access/" + date);
        call.enqueue(new Callback<RandomTitleResponse>() {
            @Override
            public void onResponse(Call<RandomTitleResponse> call, Response<RandomTitleResponse> response) {
                Log.d(TAG, "onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    String title = null;
                    for (Article a : response.body().getItems().get(0).getArticles()) {
                        if (!a.getArticle().contains("Search") && !a.getArticle().contains("Main_Page")) {
                            title = a.getArticle();
                            break;
                        }
                    }
                    getCompleteArticle(title);
                } else {
                    requestFailed(null);
                }

            }

            @Override
            public void onFailure(Call<RandomTitleResponse> call, Throwable t) {
                requestFailed(t);
            }
        });
    }

    private void getCompleteArticle(String title) {
        if (TextUtils.isEmpty(title))
            requestFailed(null);
        Call<ArticleResponse> call = DataManager.getInstance().getWikiDataSource().fetchArticle(title);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    parseData(response.body().getMobileview());
                } else {
                    requestFailed(null);
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

            }
        });
    }

    private void parseData(Mobileview body) {
        // Parse Image and Title
        CompleteArticle a = new CompleteArticle(body.getDisplaytitle(), body.getThumb().getUrl());

        // Parse Complete text
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5 && i < body.getSections().size(); i++)
            builder.append(Jsoup.clean(body.getSections().get(0).getText(), Whitelist.none()));

        a.setCompleteText(builder.toString());

        // Extract words.
        a.setWords(Utils.splitWords(a.getCompleteText()));

        // Generate random indexes for random words.
        a.setMissingWordIndexes(new HashSet<>());
        while (a.getMissingWordIndexes().size() < 10) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, a.getWords().length);
            a.getMissingWordIndexes().add(randomIndex);
        }

        article.setValue(a);
    }

    private void requestFailed(Throwable throwable) {
        Log.e(TAG, throwable == null ? "Request Failed" : throwable.getMessage());
        article.setValue(null);
    }
}
