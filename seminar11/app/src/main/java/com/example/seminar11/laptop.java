package com.example.seminar11;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class laptop {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String model;
    private float pret;
    private int memorie;
    private boolean esteSalvat;

    public laptop() {
    }

    public laptop(String model, float pret, int memorie, boolean esteSalvat) {
        this.model = model;
        this.pret = pret;
        this.memorie = memorie;
        this.esteSalvat = esteSalvat;
    }

    public laptop(String model, float pret, int memorie) {
        this.model = model;
        this.pret = pret;
        this.memorie = memorie;
        setEsteSalvat(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getMemorie() {
        return memorie;
    }

    public void setMemorie(int memorie) {
        this.memorie = memorie;
    }

    public boolean isEsteSalvat() {
        return esteSalvat;
    }

    public void setEsteSalvat(boolean esteSalvat) {
        this.esteSalvat = esteSalvat;
    }

    @Override
    public String toString() {
        return "laptop{" +
                "model='" + model + '\'' +
                ", pret=" + pret +
                ", memorie=" + memorie +
                ", esteSalvat=" + esteSalvat +
                '}';
    }
}
