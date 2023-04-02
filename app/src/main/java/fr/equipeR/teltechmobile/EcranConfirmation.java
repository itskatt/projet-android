package fr.equipeR.teltechmobile;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EcranConfirmation extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressTextView;
    private Button purchaseButton;
    private Handler handler;
    private Animation validationAnimation;

    private static final int PROGRESS_BAR_MAX = 100;
    private static final int PROGRESS_INCREMENT = 2;

    private TextView validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_confirmation);

        validation = findViewById(R.id.validation);
        validation.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.textview);
        purchaseButton = findViewById(R.id.purchase_button);
        validationAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.validation_anim);

        progressBar.setMax(PROGRESS_BAR_MAX);
        progressBar.setProgress(0);
        progressTextView.setText("0");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                startProgressBar();
            }
        }, 500);

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showValidationAnimation();
            }
        });
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
            }
        }).start();
    }

    private void showValidationAnimation() {
        progressBar.clearAnimation();
        progressBar.setVisibility(View.GONE);
        progressTextView.setVisibility(View.GONE);
        purchaseButton.setVisibility(View.GONE);
        validation.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.validation_anim));
//        sortie = findViewById(R.id.sortie);
//        sortie.setOnClickListener(clic -> {
//            sortie.startAnimation(AnimationUtils
//                    .loadAnimation(MainActivity.this, R.anim.sortie));
//            sortie.setVisibility(Button.INVISIBLE);
//            buttonAnim.put(sortie,1);
//        });
    }
}
