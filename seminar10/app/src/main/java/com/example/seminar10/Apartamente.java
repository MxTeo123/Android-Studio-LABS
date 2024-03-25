package com.example.seminar10;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Apartamente implements Parcelable {
    int nrcamere;

    int etaj;

    String zona;

    public Apartamente(int nrcamere, int etaj, String zona) {
        this.nrcamere = nrcamere;
        this.etaj = etaj;
        this.zona = zona;
    }

    protected Apartamente(Parcel in) {
        nrcamere = in.readInt();
        etaj = in.readInt();
        zona = in.readString();
    }

    public static final Creator<Apartamente> CREATOR = new Creator<Apartamente>() {
        @Override
        public Apartamente createFromParcel(Parcel in) {
            return new Apartamente(in);
        }

        @Override
        public Apartamente[] newArray(int size) {
            return new Apartamente[size];
        }
    };

    public int getNrcamere() {
        return nrcamere;
    }

    public void setNrcamere(int nrcamere) {
        this.nrcamere = nrcamere;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Apartamente{" +
                "nrcamere=" + nrcamere +
                ", etaj=" + etaj +
                ", zona='" + zona + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(nrcamere);
        dest.writeInt(etaj);
        dest.writeString(zona);
    }
}
