
package com.ankushgrover.letswiki.data.model.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleResponse {

    @SerializedName("mobileview")
    @Expose
    private Mobileview mobileview;

    public Mobileview getMobileview() {
        return mobileview;
    }

    public void setMobileview(Mobileview mobileview) {
        this.mobileview = mobileview;
    }

}
