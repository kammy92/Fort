package com.karman.fort.model;

import java.util.ArrayList;

public class TourItem {
    int id;
    ArrayList<Integer> iconList = new ArrayList<> ();
    String title, description;
    ArrayList<String> imageList = new ArrayList<> ();
    
    public TourItem (int id, ArrayList<Integer> iconList, String title, String description, ArrayList<String> imageList) {
        this.iconList = iconList;
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
    }
    
    public String getDescription () {
        return description;
    }
    
    public void setDescription (String description) {
        this.description = description;
    }
    
    public int getId () {
        return id;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public ArrayList<Integer> getIconList () {
        return iconList;
    }
    
    public void setIconList (ArrayList<Integer> iconList) {
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
