package fr.equipeR.teltechmobile.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopCartPhones extends ArrayList<Smartphone> {

    private static ShopCartPhones singletonInstance = new ShopCartPhones();
    private static HashMap<Smartphone, Integer> smartToQuant;
    private ShopCartPhones() {
        smartToQuant = new HashMap<>();
        this.add(SmartphoneList.getInstance().get(0));
        this.add(SmartphoneList.getInstance().get(1));
    }
    @Override
    public boolean add(Smartphone smartphone){
        super.add(smartphone);
        smartToQuant.put(smartphone, 1);
        return true;
    }

    public Integer getQuantity(Smartphone smartphone){
        return smartToQuant.get(smartphone);
    }

    public void setQuantity(Smartphone smartphone, int quantity){
        smartToQuant.put(smartphone, quantity);
    }

    public static ShopCartPhones getInstance(){
        return singletonInstance;
    }
}
