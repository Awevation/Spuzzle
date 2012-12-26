package com.awevation.spuzzle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.content.Intent;
import android.view.*;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.widget.Button;
import android.util.Log;
import java.lang.Thread;

public class OpenGLActivity extends MultiTouchActivity implements SensorEventListener {
   private MyGLSurfaceView GLView;
   public  World world;

   //for sensing orientation, for control
   private Sensor sense;
   private SensorManager sm;
   
   //sensed values
   private int azimuth;
   private int pitch;
   private int roll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	//Get fullscreenage
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	super.onCreate(savedInstanceState);

	//Prime the view
	setContentView(R.layout.main);
		
	//Get a hold of the picture-generator
	GLView = (MyGLSurfaceView) findViewById(R.id.GLView);

	//Get a handle on the world instantiated in the picture generator
	world = GLView.world;

	sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	sense = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

//	sm.getRotationMatrix();
	final float acc = 10f;

	/*buttonUp.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.f);
	    }
	});*/
    }

    @Override
    protected void onResume() {
	super.onResume();
	sm.registerListener(this, sense, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
	super.onPause();
	sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
	azimuth = Math.round(e.values[0]);
	pitch = Math.round(e.values[1]);
	roll = Math.round(e.values[2]);
	Log.d("OpenGLActivity", e.sensor.toString());
    }
}
