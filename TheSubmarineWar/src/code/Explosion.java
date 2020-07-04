package code;

import java.awt.Graphics;

public class Explosion {
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	public Explosion(int x, int y, int width, int height, GameMain gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}
	
	public void drawExp(Graphics arg0 ) {
		arg0.drawImage(gs.ExpIMG, x, y,60 ,60,gs);
	}

}
