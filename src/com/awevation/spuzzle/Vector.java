package com.awevation.spuzzle;

//We always knew this was inevitable
public class Vector {
    public float x;
    public float y;

    public Vector(float x, float y) {
	this.x = x;
	this.y = y;
    }

    public Vector add(Vector vec) {
	return new Vector(x + vec.x, y + vec.y);
    }

    public Vector subtract(Vector vec) {
	return new Vector(x - vec.x, y - vec.y);
    }

    public float getDP(Vector vec) {
	return x*vec.x + y*vec.y;
    }
}
