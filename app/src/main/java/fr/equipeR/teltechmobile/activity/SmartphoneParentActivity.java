package fr.equipeR.teltechmobile.activity;

import android.content.Context;

import fr.equipeR.teltechmobile.model.Smartphone;

public interface SmartphoneParentActivity {
    void onSmartphoneClicked(Smartphone smartphone);
    Context getContext();
}
