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


public class Background extends Quad {
    public Background(Context context) {
	super(context);
    }

    public Background(Context context, float xPos, float yPos) {
	super(context, xPos, yPos);
    }

    public Background(Context context, float xPos, float yPos, int texRescource) {
	super(context, xPos, yPos, texRescource);
    }

    @Override
    public void update(float dt) {
	scale = 320f;
	super.update(dt);
    }
}
