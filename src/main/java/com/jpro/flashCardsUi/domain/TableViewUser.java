package com.jpro.flashCardsUi.domain;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TableViewUser {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private Button button;

    public TableViewUser(Long id, String name, String email) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.button = new Button();
        button.setText("DELETE");
    }

    public Long getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "MappedUser{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", button=" + button +
                '}';
    }
}
