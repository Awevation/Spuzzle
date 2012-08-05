package com.awevation.spuzzle;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.graphics.Bitmap;
import java.io.InputStream;

class Quad {
    private String TAG = "Quad";

    private FloatBuffer vertexBuffer;
    private ShortBuffer indicesBuffer;
    private float height;
    private float width;
    public float xPos = 0;
    public float yPos = 0;
    public float zPos = 0;
    private float xVel = 0;
    private float yVel = 0;
    private float xAcc = 0;
    private float yAcc = 0;
    private float angle = 0;
    private float scale = 0;
    private int mProgram;
    private int posAttr;
    private int texAttr;
    private float[] mMatrix = new float[16]; //The model matrix, for the local coordinate system
    private float[] rMatrix = new float[16]; //rotation
    private float[] mvMatrix = new float[16];
    private int mMVMatrixHandle;
    private int mPMatrixHandle;
    private float alpha = 0f;

    private final String vertexShaderCode =
	"attribute vec4 vPosition;" +
	"uniform mat4 uMVMatrix;" +
	"uniform mat4 uPMatrix;" +
	"void main() {" +
	"  gl_Position = uPMatrix * uMVMatrix * vPosition;" +
	"}";

    private final String fragmentShaderCode =
	"precision mediump float;" +
	"uniform sampler2D uSampler;" +
	"uniform vec4 vColor;" +
	"void main() {" +
	"  gl_FragColor = vColor;" +
	"}";

    static final int COORDS_PER_VERTEX = 3;

    private final int vertexCount = quadCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // bytes per vertex

    static float quadCoords[] = {
	-0.5f,  0.5f, 0.0f,   // top left
	-0.5f, -0.5f, 0.0f,   // bottom left
	 0.5f, -0.5f, 0.0f,   // bottom right
	 0.5f,  0.5f, 0.0f  // top right
    };

    private short indices[] = {1, 0, 2, 3};

    public Quad() {
	init();
    }

    public Quad(float xPos, float yPos) {
	init();
	this.xPos = xPos;
	this.yPos = yPos;
    }

    public void init() {
	ByteBuffer vbb = ByteBuffer.allocateDirect(quadCoords.length * 4); //each float is four bytes
	vbb.order(ByteOrder.nativeOrder());
	vertexBuffer = vbb.asFloatBuffer();
	vertexBuffer.put(quadCoords);
	vertexBuffer.position(0);

	ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2); //shorts are just two bytes!!!
	ibb.order(ByteOrder.nativeOrder());
	indicesBuffer = ibb.asShortBuffer();
	indicesBuffer.put(indices);
	indicesBuffer.position(0);

	int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
	int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

	mProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
	GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
	GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
	GLES20.glLinkProgram(mProgram);                  // creates OpenGL ES program executables
    }

    public void setXVel(float xVel) {
	this.xVel = xVel;
    }

    public void setYVel(float yVel) {
	this.yVel = yVel;
    }

    public void setXAcc(float xAcc) {
	this.xAcc = xAcc;
    }

    public void setYAcc(float yAcc) {
	this.yAcc = yAcc;
    }

    private float alphaInc = 0.01f;

    public void update(float dt) {
	scale = 20f;

	xVel += xAcc * dt;
	yVel += yAcc * dt;

	xPos += xVel;
	if(xPos > 800) {
	    xPos = 250;
	}
	yPos += yVel;

	if(alpha > 1.0f || alpha < 0.0f ) {
	    alphaInc *= -1;
	}

	alpha += alphaInc;

	angle += 1f;

	if(angle > 360f) {
	    angle = 0f;
	}
    }

    public void draw(float[] mvMatrix, float[] pMatrix) {
	//stack.push();

	//stack.loadIdentity();

	Matrix.setIdentityM(mMatrix, 0);
	//Matrix.setIdentityM(mvMatrix, 0);

	Matrix.translateM(mMatrix, 0, xPos, yPos, 0.f);
	Matrix.multiplyMM(mvMatrix, 0, mvMatrix, 0, mMatrix, 0);
	Matrix.scaleM(mvMatrix, 0, scale, scale, 0f);
	Matrix.rotateM(mvMatrix, 0, angle, 0f, 0f, 1f);

	//stack.translate(xPos, yPos, 0.f);
	//stack.scale(30f, 30f);

	GLES20.glUseProgram(mProgram);

	posAttr = GLES20.glGetAttribLocation(mProgram, "vPosition");
	texAttr = GLES20.glGetUniformLocation(mProgram, "vColor");

	GLES20.glVertexAttribPointer(posAttr, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, vertexBuffer);

	GLES20.glEnableVertexAttribArray(posAttr);

	GLES20.glUniform4f(texAttr, 0.0f, 0.0f, 1.0f, alpha);

	mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVMatrix");
	mPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uPMatrix");

	GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mvMatrix, 0);
	GLES20.glUniformMatrix4fv(mPMatrixHandle, 1, false, pMatrix, 0);

	GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, 4, GLES20.GL_UNSIGNED_SHORT, indicesBuffer);

	GLES20.glDisableVertexAttribArray(posAttr);

	//stack.pop();
    }
}

