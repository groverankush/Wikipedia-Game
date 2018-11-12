package com.ankushgrover.letswiki.data.source.remote;

import com.ankushgrover.letswiki.data.model.article.ArticleResponse;
import com.ankushgrover.letswiki.data.model.title.RandomTitleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public interface WikiDataSource {


    @GET
    Call<RandomTitleResponse> fetchArticleList(@Url String url);

    @GET("?action=mobileview&format=json&prop=id|text|sections|languagecount|displaytitle|description|image|thumb&sections=all&sectionprop=toclevel|level|line|anchor&thumbwidth=720&maxage=7200&smaxage=7200&uselang=en")
    Call<ArticleResponse> fetchArticle(@Query("page") String title);


/*    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Single<PhotoResult> fetchPhotos(@Query("api_key") String apiKey,
                                    @Query("text") String searchTerm,
                                    @Query("page") int page);*/
}
