package fr.equipeR.teltechmobile.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ShopCartPhones extends ArrayList<Smartphone> {

    private static ShopCartPhones singletonInstance = new ShopCartPhones();
    private ShopCartPhones() {

    }

    public static ShopCartPhones getInstance(){
        return singletonInstance;
    }

}
