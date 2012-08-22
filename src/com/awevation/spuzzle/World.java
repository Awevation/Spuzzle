package com.awevation.spuzzle;

import android.util.Log;
import java.util.Vector;
import java.util.Map;
import android.content.Context;

public class World {
	private Context context;
    public Quad player;
    public Quad test;
    public Vector entities;

    public World(Context context) {
	this.context = context;
	entities = new Vector();
    }

    public void init() {
	player = new Quad(context, 15f, 60f);
	test = new Quad(context, 100f, 100f);
	entities.add(player);
	entities.add(test);
    }

    public void update(float dt) {
	//account for the difference between time and usable time
	dt /= 1000.f;
	for(int i =0; i < entities.size(); i++) {
	Quad entity = (Quad) entities.get(i);
	entity.update(dt);
	}
    }

    public void draw(float[] mvMatrix, float[] pMatrix) {
	for(int i =0; i < entities.size(); i++) {
	Quad entity = (Quad) entities.get(i);
	entity.draw(mvMatrix.clone(), pMatrix);
	}
    }
}
