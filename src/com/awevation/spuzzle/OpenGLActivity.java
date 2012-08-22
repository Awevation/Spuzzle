package com.awevation.spuzzle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.content.Intent;
import android.view.*;
import android.widget.Button;
import android.util.Log;
import java.lang.Thread;

public class OpenGLActivity extends MultiTouchActivity {
   private MyGLSurfaceView GLView;
   public  World world;
   //private Button buttons[];
   private ControllerButton buttonLeft;
   private ControllerButton buttonRight;
   private ControllerButton buttonUp;
   private ControllerButton buttonDown;
   
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

	//set up the controller, then rock and roll
	buttonLeft = (ControllerButton) findViewById(R.id.Left);
	buttonRight = (ControllerButton) findViewById(R.id.Right);
	buttonDown = (ControllerButton) findViewById(R.id.Down);
	buttonUp = (ControllerButton) findViewById(R.id.Up);

	buttonLeft.setOnTouchListener(this);
	buttonRight.setOnTouchListener(this);
	buttonUp.setOnTouchListener(this);
	buttonDown.setOnTouchListener(this);

	final float vel = 15f;
	buttonLeft.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setXAcc(-vel);
	    }
	});

	buttonLeft.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setXAcc(0.f);
	    }
	});

	buttonRight.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setXAcc(vel);
	    }
	});

	buttonRight.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setXAcc(0.f);
	    }
	});

	buttonDown.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setYAcc(-vel);
	    }
	});

	buttonDown.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.f);
	    }
	});

	buttonUp.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setYAcc(vel);
	    }
	});

	buttonUp.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.f);
	    }
	});
    }
}
