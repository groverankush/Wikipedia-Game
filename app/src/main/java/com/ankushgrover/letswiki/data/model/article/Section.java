
package com.ankushgrover.letswiki.data.model.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("toclevel")
    @Expose
    private Integer toclevel;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("line")
    @Expose
    private String line;
    @SerializedName("anchor")
    @Expose
    private String anchor;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getToclevel() {
        return toclevel;
    }

    public void setToclevel(Integer toclevel) {
        this.toclevel = toclevel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
