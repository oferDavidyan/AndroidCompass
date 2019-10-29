package il.co.igates.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

    // device sensor manager
    private SensorManager mSensorManager;

    CompassView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        cv = findViewById(R.id.compassView);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        cv.setBearing(degree);
        cv.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // not in use
    }
}
