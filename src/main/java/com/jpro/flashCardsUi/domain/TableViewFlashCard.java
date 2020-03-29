package com.jpro.flashCardsUi.domain;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewFlashCard {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty language;
    private SimpleStringProperty flashCardProgress;

    public TableViewFlashCard(Long id, String name, Language language, FlashCardProgress flashCardProgress) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.language = new SimpleStringProperty(language.toString());
        this.flashCardProgress = new SimpleStringProperty(flashCardProgress.toString());
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getFlashCardProgress() {
        return flashCardProgress.get();
    }

    public SimpleStringProperty flashCardProgressProperty() {
        return flashCardProgress;
    }

    public void setFlashCardProgress(String flashCardProgress) {
        this.flashCardProgress.set(flashCardProgress);
    }
}
