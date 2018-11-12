
package com.ankushgrover.letswiki.data.model.title;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("article")
    @Expose
    private String article;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
