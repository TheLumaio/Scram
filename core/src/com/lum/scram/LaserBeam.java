package com.lum.scram;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class LaserBeam {
	public float lifetime;
	public Vector2 p1,p2;
	public int index;
	public float x,y;
	
	private float dx,dy;
	
	public LaserBeam(Vector2 p1, Vector2 p2, int index) {
		this.p1 = p1;
		this.p2 = p2;
		this.index = index;
		lifetime = 1;
		x = p1.x;
		y = p1.y;
	}
	
	public void render(ShapeRenderer srend, float delta) {
		lifetime -= delta;
		
		float dist = (float) Math.sqrt((p2.x-x)*(p2.x-x) + (p2.y-y)*(p2.y-y))*2;
		
		float angle = MathUtils.atan2(p2.y - p1.y, p2.x - p1.x);
		dx = MathUtils.cos(angle)*dist*delta;
		dy = MathUtils.sin(angle)*dist*delta;
		
		x += dx;
		y += dy;
		
		srend.setProjectionMatrix(Core.mainCam.combined);
		srend.begin(ShapeType.Filled);
		srend.setColor(MathUtils.random(1), MathUtils.random(1), MathUtils.random(1), lifetime);
		srend.rectLine(x, y, p2.x, p2.y, (4*Core.PIM)*lifetime);
		srend.circle(p2.x, p2.y, (1*Core.PIM)+(lifetime/4), 100);
		srend.setColor(Color.WHITE);
		srend.end();
	}
	
}
