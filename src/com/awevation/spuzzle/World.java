package com.awevation.spuzzle;

import android.util.Log;

public class World {
    public Quad player;

    public World() {
    }

    public void init() {
	player = new Quad();
    }

    public void update(float dt) {
	player.update(dt);
    }

    public void draw(float[] mvpMatrix) {
	player.draw(mvpMatrix);
    }
}
