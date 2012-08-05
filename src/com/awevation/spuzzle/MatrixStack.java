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

    private Stack stack = new Stack();

    public MatrixStack() {
    }

    public void push() {
	float[] mvMatrixDupe = mvMatrix.clone();

	stack.push(mvMatrixDupe);
    }

    public void pop() {
	if(stack.empty()) {
	    Log.d(TAG, "Can't pop this empty stack...");
	} else {
	    mvMatrix = (float[]) stack.pop();
	}
    }

    public void loadIdentity() {
	//Matrix.setIdentityM(mMatrix, 0);
	//Matrix.setIdentityM(vMatrix, 0);
	Matrix.setIdentityM(mvMatrix, 0);
	//Matrix.setIdentityM((float[]) stackM.peek(), 0);
	//Matrix.setIdentityM((float[]) stackV.peek(), 0);
    }

    public void setProjection(float[] pMatrix) {
	this.pMatrix = pMatrix;
    }

    public void translate(float x, float y, float z) {
	//float[] tMatrix = new float[16];
	Matrix.translateM(mvMatrix, 0, x, y, z);
	//Matrix.multiplyMM(mvMatrix, 0, mvMatrix, 0, tMatrix, 0);

	//mvMatrix[3] += (x / mvMatrix[15]);
	//mvMatrix[7] += (y / mvMatrix[15]);
	//mvMatrix[11] += (z / mvMatrix[15]);
	//Matrix.multiplyMM(mvMatrix, 0, mMatrix, 0, vMatrix, 0);
    }

    public void scale(float x, float y) {
	Matrix.scaleM(mvMatrix, 0, x, y, 0f);
    }

    public float[] getM() {
	return mMatrix;
    }

    public float[] getV() {
	return vMatrix;
    }

    public float[] getMV() {
	return mvMatrix;
    }

    public float[] getMVP() {
	Matrix.multiplyMM(mvpMatrix, 0, pMatrix, 0, mvMatrix, 0);
	
	return mvpMatrix;
    }

}
