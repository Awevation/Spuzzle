package com.awevation.spuzzle;

import android.util.Log;
import android.opengl.Matrix;
import java.util.Stack;

public class MatrixStack {
    private String TAG = "MatrixStack"; 

    public float[] mMatrix = new float[16];
    public float[] vMatrix = new float[16];
    public float[] pMatrix = new float[16];

    public float[] mvMatrix = new float[16];
    public float[] mvpMatrix = new float[16];

    private Stack stackM = new Stack();
    private Stack stackV = new Stack();

    private float[] mMatrixCurr;
    private float[] vMatrixCurr;
    private float[] mMatrixPrev = new float[16];
    private float[] vMatrixPrev = new float[16];

    public MatrixStack() {
	Log.d(TAG, "Instantiated");
	stackM.push(mMatrix);
	stackV.push(vMatrix);
    }

    public void push() {
	/*if(stackM.empty() || stackV.empty()) {
	    Log.d(TAG, "say wha? I'm empty.... ERROR ERROR");
	} else {
	    //mMatrixPrev = (float[]) stackM.peek();
	    //vMatrixPrev = (float[]) stackV.peek();
	}*/


	float[] mMatrixCurr = (float[]) stackM.peek();
	float[] vMatrixCurr = (float[]) stackV.peek();

	float[] mMatrixDupe = new float[16];
	float[] vMatrixDupe = new float[16];

	for(int i = 0; i < 16; i++) {
	    mMatrixDupe[i] = mMatrixCurr[i];
	    vMatrixDupe[i] = vMatrixCurr[i];
	}

	stackM.push(mMatrixDupe);
	stackV.push(vMatrixDupe);
    }

    public void pop() {
	if(stackV.empty() || stackM.empty()) {
	    Log.d(TAG, "Can't pop this empty stack...");
	} else {
	    stackM.pop();
	    stackV.pop();
	    mMatrix = (float[]) stackM.peek();
	    vMatrix = (float[]) stackV.peek();

	}
    }

    public void loadIdentity() {
	Matrix.setIdentityM(mMatrix, 0);
	Matrix.setIdentityM(vMatrix, 0);
    }

    public void setProjection(float[] pMatrix) {
	this.pMatrix = pMatrix;
    }

    public void translate(float x, float y, float z) {
	Matrix.translateM((float[]) stackM.peek(), 0, x, y, z);
	Matrix.multiplyMM(mvMatrix, 0, (float[]) stackM.peek(), 0, (float[]) stackV.peek(), 0);
    }

    public float[] getMVP() {
	Matrix.multiplyMM(mvpMatrix, 0, pMatrix, 0, mvMatrix, 0);
	
	return mvpMatrix;
    }

}
