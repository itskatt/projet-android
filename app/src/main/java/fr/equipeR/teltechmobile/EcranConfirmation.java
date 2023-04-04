package fr.equipeR.teltechmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 Ecran de confirmation de commande, panier validé avec les animations
 */
public class EcranConfirmation extends ShakeableActivity {
    /**
     * Barre de progression, texte de progression et bouton d'achat.
     */
    private ProgressBar progressBar;
    private TextView progressTextView;
    private Handler handler;

    /**
     * Constantes pour la barre de progression.
     */
    private static final int PROGRESS_BAR_MAX = 100;
    private static final int PROGRESS_INCREMENT = 2;

    /**
     * Texte et image de validation.
     */
    private TextView validation;
    private ImageView logoValide;

    /**
     * Initialise la vue et lance la barre de progression.
     * @param savedInstanceState Instance enregistrée de l'état de l'application.
     */
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
        handler.postDelayed(this::startProgressBar, 500);

    }

    /**
     * Lance la barre de progression.
     */
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

    /**
     * Anime la validation et masque la barre de progression et le texte de progression.
     */
    private void showValidationAnimation() {
        validation.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.validation_anim));
        progressBar.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.leaving_right_anim));
        progressTextView.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.leaving_left_anim));
        logoValide.startAnimation(AnimationUtils.loadAnimation(EcranConfirmation.this, R.anim.arriving_anim));
        progressBar.setVisibility(View.INVISIBLE);
        progressTextView.setVisibility(View.INVISIBLE);
    }

    /**
     * Redirige vers l'écran principal si l'utilisateur appuie sur le bouton retour.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
