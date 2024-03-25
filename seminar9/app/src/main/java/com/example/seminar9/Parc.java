package com.example.seminar9;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parcuri")
public class Parc {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nume")
    private String nume;
    private float suprafata;
    @ColumnInfo
    private boolean taxaIntrare;

    public Parc(String nume, float suprafata, boolean taxaIntrare) {
        this.nume = nume;
        this.suprafata = suprafata;
        this.taxaIntrare = taxaIntrare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(float suprafata) {
        this.suprafata = suprafata;
    }

    public boolean isTaxaIntrare() {
        return taxaIntrare;
    }

    public void setTaxaIntrare(boolean taxaIntrare) {
        this.taxaIntrare = taxaIntrare;
    }

    @Override
    public String toString() {
        return "Parc{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", suprafata=" + suprafata +
                ", taxaIntrare=" + taxaIntrare +
                '}';
    }
}
