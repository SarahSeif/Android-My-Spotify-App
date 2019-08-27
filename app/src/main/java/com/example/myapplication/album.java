package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class album implements Serializable {

    ArrayList<ImageModel> images;

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }
}
//testing sth