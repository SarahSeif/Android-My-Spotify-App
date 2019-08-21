package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayListModel implements Serializable {
    private TrackModel tracks;

    public TrackModel getTracks() {
        return tracks;
    }

    public void setTracks(TrackModel tracks) {
        this.tracks = tracks;
    }
}
