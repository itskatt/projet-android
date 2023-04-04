package fr.equipeR.teltechmobile;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ShakeableActivity  extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private float lastX, lastY, lastZ;
    private static final int SHAKE_THRESHOLD = 40;

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

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float deltaX = Math.abs(lastX - x);
        float deltaY = Math.abs(lastY - y);
        float deltaZ = Math.abs(lastZ - z);

        if (deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD) {
            if (isFlashOn) {
                turnOffFlash();
            } else {
                turnOnFlash();
            }
        }
        float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
        if (acceleration > SHAKE_THRESHOLD) {
            finish();
        }

        lastX = x;
        lastY = y;
        lastZ = z;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    private void turnOnFlash() {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void turnOffFlash() {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
