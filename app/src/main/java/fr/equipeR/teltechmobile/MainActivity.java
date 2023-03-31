package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("TelTech");
        Intent intent = new Intent(this, EcranSmartphone.class);
        this.startActivity(intent);
    }
}