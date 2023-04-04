package fr.equipeR.teltechmobile;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Cette classe abstraite étend la classe AppCompatActivity et implémente SensorEventListener.
 * Elle fournit une fonctionnalité permettant de déclencher l'activation/désactivation du flash de
 * la caméra du téléphone en secouant le téléphone.
 */
public abstract class ShakeableActivity  extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private float lastX, lastY, lastZ;
    private static final int SHAKE_THRESHOLD = 20;
    private static final int DEBOUNCE_TIME_MILLIS = 1000;
    private long lastTimeFlashlightToggled = -1;

    /**
     * Appelé lors de la création de l'activité. Initialise les capteurs et la caméra du téléphone.
     *
     * @param savedInstanceState l'état de l'instance précédente de l'activité, s'il existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appelé lorsque l'activité devient visible à l'utilisateur. Enregistre l'écouteur de capteur.
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * Appelé lorsque l'activité n'est plus visible à l'utilisateur. Désenregistre l'écouteur de capteur.
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    /**
     * Appelé lorsqu'un changement est détecté dans les capteurs. Contrôle si une secousse est détectée et active/désactive le flash de la caméra en conséquence. Termine l'activité en cas de secousse violente.
     *
     * @param event l'événement de capteur détecté.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float deltaX = Math.abs(lastX - x);
        float deltaY = Math.abs(lastY - y);
        float deltaZ = Math.abs(lastZ - z);

        if (deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD) {
            if ((System.currentTimeMillis() - lastTimeFlashlightToggled) < DEBOUNCE_TIME_MILLIS)
                return;

            lastTimeFlashlightToggled = System.currentTimeMillis();
            if (isFlashOn) {
                turnOffFlash();
            } else {
                turnOnFlash();
            }
        }

        lastX = x;
        lastY = y;
        lastZ = z;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    /**
     *  Allume le flash de la caméra si possible.
     *  Si l'accès à la caméra est autorisé et qu'une caméra est disponible, cette méthode allume le flash.
     *  Sinon, elle lance une exception CameraAccessException.
     */
    private void turnOnFlash() {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
            }
        } catch (CameraAccessException e) {
            Log.e(getClass().getName(), "turnOnFlash: ", e);
        }
    }

    /**
     * Éteint le flash de la caméra si possible.
     * Si l'accès à la caméra est autorisé et qu'une caméra est disponible, cette méthode éteint le flash.
     * Sinon, elle lance une exception CameraAccessException.
     */
    private void turnOffFlash() {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
            }
        } catch (CameraAccessException e) {
            Log.e(getClass().getName(), "turnOffFlash: ", e);
        }
    }
}
