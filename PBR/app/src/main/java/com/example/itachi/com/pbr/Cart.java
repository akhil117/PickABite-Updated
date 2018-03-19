package com.example.itachi.com.pbr;

/**
 * Created by akhilbatchu on 19/3/18.
 */

public class Cart {
    private String Name,Rs;
    public Cart()
    {

    }
    public Cart(String Name,String Rs)
    {
        this.Name= Name;
        this.Rs= Rs;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRs() {
        return Rs;
    }

    public void setRs(String rs) {
        Rs = rs;
    }
}
