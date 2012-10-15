package com.awevation.spuzzle;

import android.util.Log;
import java.util.Vector;
import java.util.Map;
import android.content.Context;

public class World {
    private Context context;
    public Quad player;
    public Quad background;

    public Vector entities;
    public World(Context context) {
	this.context = context;
	entities = new Vector();
    }

    public void init() {
	background = new Background(context, 150f, 70f, R.drawable.waterfall);
	player = new Anser(context, 15f, 60f, R.drawable.anser);
	Quad cloud = new Quad(context, 100f, 100f, R.drawable.cloud);
	Quad cloud1 = new Quad(context, 0f, 75f, R.drawable.cloud);
	Quad cloud2 = new Quad(context, 75f, 10f, R.drawable.cloud);
	Quad cloud3 = new Quad(context, 200f, 30f, R.drawable.cloud);
	cloud.setXVel(0.25f);
	cloud1.setXVel(0.2f);
	cloud2.setXVel(0.1f);
	cloud3.setXVel(0.15f);
	entities.add(background);
	entities.add(player);
	entities.add(cloud);
	entities.add(cloud1);
	entities.add(cloud2);
	entities.add(cloud3);
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
