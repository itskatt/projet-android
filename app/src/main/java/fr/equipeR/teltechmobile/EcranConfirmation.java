package fr.equipeR.teltechmobile;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class EcranConfirmation extends AppCompatActivity {

    /*
    Ecran de confirmation de commande, panier validé avec les animations
     */
    private ProgressBar progressBar;
    private TextView progressTextView;
    private Button purchaseButton;
    private Handler handler;

    private static final int PROGRESS_BAR_MAX = 100;
    private static final int PROGRESS_INCREMENT = 2;

    private TextView validation;
    private ImageView logoValide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_confirmation);

        validation = findViewById(R.id.validation);
        validation.setVisibility(View.GONE);

        logoValide = findViewById(R.id.logoValide);
        logoValide.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.textview);

        progressBar.setMax(PROGRESS_BAR_MAX);
        progressBar.setProgress(0);
        progressTextView.setText("0");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startProgressBar();
            }
        }, 500);

    }

    private void startProgressBar() {
        new Thread(() -> {
            for (int i = 0; i <= PROGRESS_BAR_MAX; i += PROGRESS_INCREMENT) {
                final int currentProgressCount = i;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(() -> {
                    progressBar.setProgress(currentProgressCount);
                    progressTextView.setText(getString(R.string.progress_text, currentProgressCount));
                });
                if (currentProgressCount == PROGRESS_BAR_MAX) {
                    runOnUiThread(() -> {
                        validation.setVisibility(View.VISIBLE);
                        logoValide.setVisibility(View.VISIBLE);
                    });
                    showValidationAnimation();
                }
            }
        }).start();
    }

    private void showValidationAnimation() {
        validation.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.validation_anim));
        progressBar.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.leaving_right_anim));
        progressTextView.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.leaving_left_anim));
        logoValide.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.arriving_anim));
        progressBar.setVisibility(View.INVISIBLE);
        progressTextView.setVisibility(View.INVISIBLE);
    }

}
