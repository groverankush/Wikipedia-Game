
package com.ankushgrover.letswiki.data.model.article;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobileview {

    @SerializedName("languagecount")
    @Expose
    private Integer languagecount;
    @SerializedName("displaytitle")
    @Expose
    private String displaytitle;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("descriptionsource")
    @Expose
    private String descriptionsource;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("thumb")
    @Expose
    private Thumb thumb;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;

    public Integer getLanguagecount() {
        return languagecount;
    }

    public void setLanguagecount(Integer languagecount) {
        this.languagecount = languagecount;
    }

    public String getDisplaytitle() {
        return displaytitle;
    }

    public void setDisplaytitle(String displaytitle) {
        this.displaytitle = displaytitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionsource() {
        return descriptionsource;
    }

    public void setDescriptionsource(String descriptionsource) {
        this.descriptionsource = descriptionsource;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Thumb getThumb() {
        return thumb;
    }

    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
