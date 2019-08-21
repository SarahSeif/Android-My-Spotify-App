package com.example.myapplication;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private String url;

    public ImageModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
