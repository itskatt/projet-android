package fr.equipeR.teltechmobile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import fr.equipeR.teltechmobile.ChangingPriceActivity;

public class ShopCartPhones extends ArrayList<Smartphone> {

    private static final ShopCartPhones singletonInstance = new ShopCartPhones();
    private static HashMap<Smartphone, Integer> smartToQuant;
    private static HashMap<Smartphone, Double> smartToPrice;
    private static HashMap<Smartphone, Double> smartToTaxedPrice;

    private ChangingPriceActivity changingPriceActivity;

    private ShopCartPhones() {
        smartToQuant = new HashMap<>();
        smartToPrice = new HashMap<>();
        smartToTaxedPrice = new HashMap<>();

    }
    @Override
    public boolean add(Smartphone smartphone){
        if (super.contains(smartphone)){
            return false;
        }
        super.add(smartphone);
        smartToQuant.put(smartphone, 1);
        smartToPrice.put(smartphone, smartphone.getPriceNoTax());
        smartToTaxedPrice.put(smartphone, smartphone.getPriceTax());

        if (changingPriceActivity != null){
            changingPriceActivity.onItemNumberUpdated(this.size());
        }
        return true;
    }


    public boolean remove(Object smartphone){
        super.remove(smartphone);
        smartToQuant.remove(smartphone);
        if (changingPriceActivity != null){
            changingPriceActivity.onItemNumberUpdated(this.size());
        }
        return true;
    }

    public Integer getQuantity(Smartphone smartphone){
        return smartToQuant.get(smartphone);
    }

    public void setQuantity(Smartphone smartphone, int quantity){
        smartToQuant.put(smartphone, quantity);
    }

    public void updatePrice(Smartphone smartphone, Double newPrice) {
        smartToPrice.put(smartphone, newPrice);

        if (this.changingPriceActivity != null){
            changingPriceActivity.onHTPriceUpdated(calcHTPrice());
        }
    }

    public void updateTaxedPrice(Smartphone smartphone, Double newPrice) {
        smartToTaxedPrice.put(smartphone, newPrice);

        if (this.changingPriceActivity != null){
            changingPriceActivity.onTTCPriceUpdated(calcTTCPrice());
        }
    }

    public Double calcHTPrice(){
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        smartToPrice.forEach((smartphone, aDouble) -> {
            result.updateAndGet(v -> v + aDouble);
        });
        return result.get();
    }

    public Double calcTTCPrice(){
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        smartToTaxedPrice.forEach((smartphone, aDouble) -> {
            result.updateAndGet(v -> v + aDouble);
        });
        return result.get();
    }

    public void callWhenPriceChanging(ChangingPriceActivity changingPriceActivity){
        this.changingPriceActivity = changingPriceActivity;
    }


    public static ShopCartPhones getInstance(){
        return singletonInstance;
    }


}
