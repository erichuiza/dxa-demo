package com.alliancedata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Page {
    @JsonProperty("title")
    public String title;

    @JsonProperty("presentations")
    private List<AbstractModel> presentations;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AbstractModel> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<AbstractModel> presentations) {
        this.presentations = presentations;
    }
}
