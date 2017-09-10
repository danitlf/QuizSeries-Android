package com.example.dani.quizseries.models;

import java.io.Serializable;

/**
 * Created by dani on 20/08/17.
 */

public class Serie implements Serializable {
    private String name;
    private String id;
    private String cor;

    public Serie(String name, String id) {
        this.name = name;
        this.id = id;

    }

    public Serie(String name, String id, String cor) {
        this.name = name;
        this.id = id;
        this.cor = cor;

    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Serie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
