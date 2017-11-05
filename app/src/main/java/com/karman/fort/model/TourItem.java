package com.karman.fort.model;

import java.util.ArrayList;

public class TourItem {
    int id;
    ArrayList<String> iconList = new ArrayList<> ();
    String title, description, audio_uri;
    ArrayList<String> imageList = new ArrayList<> ();
    
    public TourItem (int id, ArrayList<String> iconList, String title, String description, String audio_uri, ArrayList<String> imageList) {
        this.iconList = iconList;
        this.id = id;
        this.title = title;
        this.description = description;
        this.audio_uri = audio_uri;
        this.imageList = imageList;
    }
    
    public String getDescription () {
        return description;
    }
    
    public void setDescription (String description) {
        this.description = description;
    }
    
    public String getAudio_uri () {
        return audio_uri;
    }
    
    public void setAudio_uri (String audio_uri) {
        this.audio_uri = audio_uri;
    }
    
    public int getId () {
        return id;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public ArrayList<String> getIconList () {
        return iconList;
    }
    
    public void setIconList (ArrayList<String> iconList) {
        this.iconList = iconList;
    }
    
    public String getTitle () {
        return title;
    }
    
    public void setTitle (String title) {
        this.title = title;
    }
    
    public ArrayList<String> getImageList () {
        return imageList;
    }
    
    public void setImageList (ArrayList<String> imageList) {
        this.imageList = imageList;
    }
}
