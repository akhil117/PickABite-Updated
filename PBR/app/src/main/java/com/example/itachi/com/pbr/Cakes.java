package com.example.itachi.com.pbr;

/**
 * Created by ITACHI on 02-03-2018.
 */
public class Cakes {
    private String ID,Name,image,RS;
    public Cakes(){

    }

    public Cakes(String ID, String Name, String image, String RS) {
        this.ID = ID;
        this.RS=RS;
        this.Name = Name;
        this.image= image;
    }



    public String getRS() {
        return RS;
    }

    public void setRS(String RS) {
        this.RS = RS;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {

        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getimage() {
        return image;
    }

    public void setImg(String image) {
        this.image=image;
    }
}
