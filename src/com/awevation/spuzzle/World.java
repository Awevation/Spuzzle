package com.awevation.spuzzle;

import android.util.Log;
import java.util.Vector;
import java.util.Map;

public class World {
    public Quad player;
    public Quad test;
    public Vector entities;

    public World() {
	entities = new Vector();
    }

    public void init() {
	player = new Quad(15f, 60f);
	test = new Quad(100f, 100f);
    }

    public void update(float dt) {
	//account for the difference between time and usable time
	dt /= 1000.f;
	player.update(dt);
	test.update(dt);
    }

    public void draw(float[] mvMatrix, float[] pMatrix) {
	player.draw(mvMatrix, pMatrix);
	test.draw(mvMatrix, pMatrix);
    }
}
