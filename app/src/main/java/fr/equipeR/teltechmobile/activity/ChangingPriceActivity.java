package fr.equipeR.teltechmobile.activity;

public interface ChangingPriceActivity {
    void onHTPriceUpdated(double newPrice);
    void onTTCPriceUpdated(double newPrice);

    void onItemNumberUpdated(double newNumber);
}
