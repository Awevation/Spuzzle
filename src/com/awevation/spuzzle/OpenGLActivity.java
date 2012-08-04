package com.awevation.spuzzle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.content.Intent;
import android.view.*;
import android.app.Fragment;
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

	//Forge a new world
	world = new World();

	//Send the picture generator our world
	GLView.sendWorld(world);

	//set up the controller, then rock and roll
	buttonLeft = (ControllerButton) findViewById(R.id.Left);
	buttonRight = (ControllerButton) findViewById(R.id.Right);
	buttonDown = (ControllerButton) findViewById(R.id.Down);
	buttonUp = (ControllerButton) findViewById(R.id.Up);

	/*buttonLeft.setOnTouchListener(new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent e) {
		switch (e.getAction()) {
		    case MotionEvent.ACTION_DOWN:
			v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
			v.setPressed(true);
			goLeft(true);
			return true;
		    case MotionEvent.ACTION_UP:
			v.setPressed(false);
			goLeft(false);
			return true;
		}
		return false;
	    }
	});

	buttonRight.setOnTouchListener(new View.OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent e) {
		switch (e.getAction()) {
		    case MotionEvent.ACTION_DOWN:	
			v.setPressed(true);
			v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
			goRight(true);
			return true;
		    case MotionEvent.ACTION_UP:
			v.setPressed(false);
			goRight(false);
			return true;
		}
		return false;
	    }
	});*/
	buttonLeft.setOnTouchListener(this);
	buttonRight.setOnTouchListener(this);
	buttonUp.setOnTouchListener(this);
	buttonDown.setOnTouchListener(this);

	buttonLeft.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setXAcc(-0.5f);
	    }
	});

	buttonLeft.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setXAcc(0.f);
	    }
	});

	buttonRight.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setXAcc(0.5f);
	    }
	});

	buttonRight.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setXAcc(0.f);
	    }
	});

	buttonDown.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setYAcc(-0.5f);
	    }
	});

	buttonDown.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.f);
	    }
	});

	buttonUp.setRunmeDown(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.5f);
	    }
	});

	buttonUp.setRunmeUp(new Runnable() {
	    public void run() {
		world.player.setYAcc(0.f);
	    }
	});
    }

    /*public void goRight(boolean go) {
	if(go) {
    	    new Thread(new Runnable() {
   		public void run() {
    		    while(buttonRight.isPressed()) {
    			//GLView.mRenderer.xVel += 0.00000001f;	
    		    }
    		}
    	    }).start();

	} else {
	    new Thread(new Runnable() {
		public void run() {
		    while(!buttonRight.isPressed()) {
			if(GLView.mRenderer.xVel < 0.001f) {
			    //GLView.mRenderer.xVel = 0.f;
			    return;
			}
			//GLView.mRenderer.xVel -= 0.00000005f;
		    }
		}
	    }).start();
	}
    }
    public void goLeft(boolean go) {
	if(go) {
    	    new Thread(new Runnable() {
   		public void run() {
    		    while(buttonLeft.isPressed()) {
    			//GLView.mRenderer.xVel += -0.00000001f;	
    		    }
    		}
    	    }).start();

	} else {
	    new Thread(new Runnable() {
		public void run() {
		    while(!buttonLeft.isPressed()) {
			if(GLView.mRenderer.xVel > -0.001f) {
			    //GLView.mRenderer.xVel = 0.f;
			    return;
			}
			//GLView.mRenderer.xVel -= -0.00000005f;
		    }
		}
	    }).start();
	}
    }

    public void goDown(View view) {
    }

    public void goUp(View view) {
    }*/
}
