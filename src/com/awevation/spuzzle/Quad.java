package com.awevation.spuzzle;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import android.opengl.GLES20;
import android.opengl.Matrix;

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
    private int mProgram;
    private int posAttr;
    private int texAttr;
    private int mMVMatrixHandle;
    private int mPMatrixHandle;

    private final String vertexShaderCode =
	"attribute vec4 vPosition;" +
	"uniform mat4 uMVMatrix;" +
	"uniform mat4 uPMatrix;" +
	"void main() {" +
	"  gl_Position = uPMatrix * uMVMatrix * vPosition;" +
	"}";

    private final String fragmentShaderCode =
	"precision mediump float;" +
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

    float color[] = {
	0.0f, 0.0f, 1.0f, 1.0f
    };

    public Quad() {
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

    public void update(float dt) {
	xVel += xAcc * dt;
	yVel += yAcc * dt;

	xPos += xVel;
	yPos += yVel;
    }

    public void draw(MatrixStack stack) {
	stack.push();

	stack.loadIdentity();

	stack.translate(xPos, yPos, 0.f);
	stack.scale(30f, 30f);

	GLES20.glUseProgram(mProgram);

	posAttr = GLES20.glGetAttribLocation(mProgram, "vPosition");
	texAttr = GLES20.glGetUniformLocation(mProgram, "vColor");

	GLES20.glVertexAttribPointer(posAttr, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, vertexBuffer);

	GLES20.glEnableVertexAttribArray(posAttr);

	GLES20.glUniform4f(texAttr, 0.0f, 0.0f, 1.0f, 1.0f);

	mMVMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVMatrix");
	mPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uPMatrix");

	GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, stack.getMV(), 0);
	GLES20.glUniformMatrix4fv(mPMatrixHandle, 1, false, stack.pMatrix, 0);

	GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, 4, GLES20.GL_UNSIGNED_SHORT, indicesBuffer);

	GLES20.glDisableVertexAttribArray(posAttr);

	stack.pop();
    }
}

