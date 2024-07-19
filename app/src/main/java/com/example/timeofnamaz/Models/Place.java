package com.example.timeofnamaz.Models;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    public String name;
    public LatLng latlng;
    public int n;

    public Place(String name, LatLng latlng, int n) {
        this.name = name;
        this.latlng = latlng;
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }
}
