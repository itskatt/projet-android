package fr.equipeR.teltechmobile;

public interface ChangingPriceActivity {
    void onHTPriceUpdated(double newPrice);
    void onTTCPriceUpdated(double newPrice);

    void onItemNumberUpdated(double newNumber);
}
