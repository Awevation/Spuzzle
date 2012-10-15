package com.awevation.spuzzle;

public class AABB {
    private float x;
    private float y;
    private float width;
    private float height;

    public AABB(float x, float y, float width, float height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    public void setX(float x) {
	this.x = x;
    }

    public float getX() {
	return this.x;
    }


    public void setY(float y) {
	this.y = y;
    }

    public float getY() {
	return y;
    }
}
