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
	//account for the difference between time and usable time
	dt /= 1000.f;
	player.update(dt);
    }

    public void draw(MatrixStack stack) {
	player.draw(stack);
    }
}
