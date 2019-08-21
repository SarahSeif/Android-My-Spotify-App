package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable {
    private String name;
    private ArrayList<artists> artists;
    private String preview_url;
    private album album;

    public Song(String name, ArrayList<com.example.myapplication.artists> artists, String preview_url, com.example.myapplication.album album) {
        this.name = name;
        this.artists = artists;
        this.preview_url = preview_url;
        this.album = album;
    }


    public Song(String name, ArrayList<com.example.myapplication.artists> artists) {
        this.name = name;
        this.artists = artists;
    }

    public Song() {
    }

    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<com.example.myapplication.artists> getArtists() {
        return artists;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public com.example.myapplication.album getAlbum() {
        return album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtists(ArrayList<com.example.myapplication.artists> artists) {
        this.artists = artists;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public void setAlbum(com.example.myapplication.album album) {
        this.album = album;
    }
}

