package com.example.seminar10fix;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Apartament implements Parcelable {
    int etaj;
    String complex;
    double suprafata;
    int pret;

    public Apartament(int etaj, String complex, double suprafata, int pret) {
        this.etaj = etaj;
        this.complex = complex;
        this.suprafata = suprafata;
        this.pret = pret;
    }

    protected Apartament(Parcel in) {
        etaj = in.readInt();
        complex = in.readString();
        suprafata = in.readDouble();
        pret = in.readInt();
    }

    public static final Creator<Apartament> CREATOR = new Creator<Apartament>() {
        @Override
        public Apartament createFromParcel(Parcel in) {
            return new Apartament(in);
        }

        @Override
        public Apartament[] newArray(int size) {
            return new Apartament[size];
        }
    };

    @Override
    public String toString() {
        return "Apartament{" +
                "etaj=" + etaj +
                ", complex='" + complex + '\'' +
                ", suprafata=" + suprafata +
                ", pret=" + pret +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(etaj);
        dest.writeString(complex);
        dest.writeDouble(suprafata);
        dest.writeInt(pret);
    }
}
