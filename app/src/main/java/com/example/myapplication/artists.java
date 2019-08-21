package com.example.myapplication;

public class artists {

   private String name;
   private String id;
   private String type;


    public artists(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public artists() {
    }

    public artists(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
