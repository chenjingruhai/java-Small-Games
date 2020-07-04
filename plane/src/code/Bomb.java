package code;

import java.awt.Graphics;

public class Bomb {
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	public Bomb(int x, int y, int width, int height, GameMain gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}
	
	public void drawBomb(Graphics arg0 ) {
		arg0.drawImage(gs.BombIMG, x, y,20 ,20,gs);
	}

}
