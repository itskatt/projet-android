package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.equipeR.teltechmobile.model.SmartphoneList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartphoneList list = SmartphoneList.getInstance(this);
        list.fetchSmartphones(System.out::println, System.err::println);

        Intent intent = new Intent(this, EcranConfirmation.class);
        this.startActivity(intent);
    }
}