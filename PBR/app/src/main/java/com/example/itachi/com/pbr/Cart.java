package com.example.itachi.com.pbr;

/**
 * Created by akhilbatchu on 19/3/18.
 */

public class Cart {
    private String Name,Rs,Id,Quantity;
    public Cart()
    {

    }
    public Cart(String Name,String Rs,String Id,String Quantity)
    {
        this.Quantity=Quantity;
        this.Name= Name;
        this.Rs= Rs;
        this.Id = Id;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getId()
    {
        return  Id;
    }
    public void setId(String id)
    {
        Id = id;
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