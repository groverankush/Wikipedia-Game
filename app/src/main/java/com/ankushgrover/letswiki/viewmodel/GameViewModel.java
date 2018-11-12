package com.ankushgrover.letswiki.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.ankushgrover.letswiki.data.model.CompleteArticle;
import com.ankushgrover.letswiki.data.model.article.ArticleResponse;
import com.ankushgrover.letswiki.data.model.title.Article;
import com.ankushgrover.letswiki.data.model.title.RandomTitleResponse;
import com.ankushgrover.letswiki.data.source.DataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public class GameViewModel extends ViewModel {

    private static final String TAG = GameViewModel.class.getSimpleName();

    private MutableLiveData<CompleteArticle> article;

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
                    for (Article a : response.body().getItems().get(0).getArticles()) {
                        if (!a.getArticle().contains("Search") && !a.getArticle().contains("Main_Page")) {
                            CompleteArticle temp = new CompleteArticle(a.getArticle());
                            article.setValue(temp);
                            break;
                        }
                    }
                    getCompleteArticle();
                } else {
                    article.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<RandomTitleResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                article.setValue(null);
            }
        });
    }

    public void getCompleteArticle() {
        Call<ArticleResponse> call = DataManager.getInstance().getWikiDataSource().fetchArticle(article.getValue().getTitle());
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful()) {
                    // TODO Parse data and add to the LiveData.
                } else {
                    article.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

            }
        });
    }

}
