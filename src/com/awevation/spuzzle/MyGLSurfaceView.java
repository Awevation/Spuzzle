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

	mRenderer = new MyGLRenderer(context);

	world = new World(context);
	mRenderer.sendWorld(world);

	setEGLContextClientVersion(2);
	setEGLConfigChooser(new MultisampleConfigChooser());
	setRenderer(mRenderer);
	//setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
