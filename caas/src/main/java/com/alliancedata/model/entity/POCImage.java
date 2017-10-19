package com.alliancedata.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.entity.MediaItem;
import com.sdl.webapp.common.exceptions.DxaException;
import com.sdl.webapp.common.markup.html.HtmlElement;

@SemanticEntity(
        entityName = "ImageObject",
        vocabulary = "http://schema.org/",
        prefix = "s",
        public_ = true
)
public class POCImage extends MediaItem {

    @JsonProperty("alt")
    @SemanticProperty("s:name")
    private String alt;

    @JsonProperty("title")
    @SemanticProperty("s:title")
    private String title;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public HtmlElement toHtmlElement(String s) throws DxaException {
        return null;
    }

    @Override
    public HtmlElement toHtmlElement(String s, double v, String s1, int i) throws DxaException {
        return null;
    }

    @Override
    public HtmlElement toHtmlElement(String s, double v, String s1, int i, String s2) throws DxaException {
        return null;
    }
}
