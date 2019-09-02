package com.example.myapplication.Chat_Bot;

import java.io.Serializable;

public class input implements Serializable {

    private String text;


    public input(String text) {
        this.text = text;
    }

    public input() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}



