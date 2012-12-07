package com.awevation.spuzzle;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.opengl.GLUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import android.content.Context;
import android.content.res.Resources;


public class Anser extends Quad {
    public Anser(Context context) {
	super(context);
    }

    public Anser(Context context, float xPos, float yPos) {
	super(context, xPos, yPos);
    }

    public Anser(Context context, float xPos, float yPos, int texRescource) {
	super(context, xPos, yPos, texRescource);
    }

    @Override
    public void update(float dt) {
	scale = 40f;
	super.update(dt);
	if(alpha > 1.0f || alpha < 0.0f ) {
	    alphaInc *= -1;
	}

	if(xVel != 0) {
	    angleInc = 1f + (xVel * 2);
	} else {
	    angleInc = 1f;
	}

	//angle += angleInc;

	if(angle > 360f || angle < -360f) {
	    angle = 0f;
	}

	alpha = 1.0f;
    }

    @Override
    public void draw(float[] mvMatrix, float[] pMatrix) {
	super.draw(mvMatrix, pMatrix);
    }
}
