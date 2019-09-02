package com.example.myapplication.Chat_Bot;

import java.io.Serializable;
import java.util.ArrayList;

public class output implements Serializable {

    private ArrayList<String> text;

    public output(ArrayList<String> text) {
        this.text = text;
    }

    public output() {
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }
}
