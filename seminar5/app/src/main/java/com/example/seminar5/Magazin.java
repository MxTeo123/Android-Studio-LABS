package com.example.seminar5;

import android.os.Parcel;
import android.os.Parcelable;

public class Magazin implements Parcelable {
    private String nume;
    private int nrAngajati;

    public Magazin(String nume, int nrAngajati) {
        this.nume = nume;
        this.nrAngajati = nrAngajati;
    }

    protected Magazin(Parcel in) {
        nume = in.readString();
        nrAngajati = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeInt(nrAngajati);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Magazin> CREATOR = new Creator<Magazin>() {
        @Override
        public Magazin createFromParcel(Parcel in) {
            return new Magazin(in);
        }

        @Override
        public Magazin[] newArray(int size) {
            return new Magazin[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrAngajati() {
        return nrAngajati;
    }

    public void setNrAngajati(int nrAngajati) {
        this.nrAngajati = nrAngajati;
    }

    @Override
    public String toString() {
        return "Magazin{" +
                "nume='" + nume + '\'' +
                ", nrAngajati=" + nrAngajati +
                '}';
    }
}
