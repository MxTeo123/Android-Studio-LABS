package com.example.seminar10fix;

public class Preferinte {
    int dimensiuneText;
    String culoareFundal;

    public int getDimensiuneText() {
        return dimensiuneText;
    }

    public void setDimensiuneText(int dimensiuneText) {
        this.dimensiuneText = dimensiuneText;
    }

    public String getCuloareFundal() {
        return culoareFundal;
    }

    public void setCuloareFundal(String culoareFundal) {
        this.culoareFundal = culoareFundal;
    }

    public Preferinte(int dimensiuneText, String culoareFundal) {
        this.dimensiuneText = dimensiuneText;
        this.culoareFundal = culoareFundal;
    }
}
