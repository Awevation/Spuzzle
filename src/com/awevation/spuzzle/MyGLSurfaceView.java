package com.awevation.spuzzle;

import android.opengl.GLSurfaceView;
import android.content.Context;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.util.Log;

public class MyGLSurfaceView extends GLSurfaceView {

    public World world;
    public MyGLRenderer mRenderer;
    private Quad player;

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
	super(context, attrs);

	mRenderer = new MyGLRenderer();

	setEGLContextClientVersion(2);
	setRenderer(mRenderer);
	//setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void sendWorld(World targetWorld) {
	mRenderer.sendWorld(targetWorld);
    }
}
