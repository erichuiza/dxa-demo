package com.alliancedata.model.entity;

import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.RichText;
import com.sdl.webapp.common.api.model.entity.AbstractEntityModel;
import static com.sdl.webapp.common.api.mapping.semantic.config.SemanticVocabulary.SDL_CORE;

@SemanticEntity(
        entityName = "POCPromo",
        vocabulary = SDL_CORE,
        prefix = "m"
)
public class POCPromo extends AbstractEntityModel {

    @SemanticProperty("m:title")
    private String title;

    @SemanticProperty("m:description")
    private RichText description;

    @SemanticProperty("m:image")
    private POCImage image;

    public POCPromo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RichText getDescription() {
        return description;
    }

    public void setDescription(RichText description) {
        this.description = description;
    }

    public POCImage getImage() {
        return image;
    }

    public void setImage(POCImage image) {
        this.image = image;
    }
}
