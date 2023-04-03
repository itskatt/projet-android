package fr.equipeR.teltechmobile.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ShopCartPhones extends ArrayList<Smartphone> {

    private static ShopCartPhones singletonInstance = new ShopCartPhones();
    private ShopCartPhones() {
        add(SmartphoneList.getInstance().get(0));
        add(SmartphoneList.getInstance().get(1));
    }

    public static ShopCartPhones getInstance(){
        return singletonInstance;
    }
}
