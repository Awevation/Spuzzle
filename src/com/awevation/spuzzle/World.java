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
	player = new Quad();
	test = new Quad();
    }

    public void update(float dt) {
	//account for the difference between time and usable time
	dt /= 1000.f;
	player.update(dt);
	test.xPos = 1.f;
	test.yPos = 1.f;
	test.zPos = 0.f;
    }

    public void draw(MatrixStack stack) {
	player.draw(stack);
	test.draw(stack);
    }
}
