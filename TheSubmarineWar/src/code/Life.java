package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Life {
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	boolean isLive;

	public Life(int x, int y, int width, int height, GameMain gs, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}

	// ÄÃµ½¾ØÐÎ
	public Rectangle getRec() {
		if (this.isLive) {
			return new Rectangle(x, y, width, height);
		}
		return new Rectangle(0, 0, 0, 0);

	}
	int time = 0;
	public void drawLife(Graphics arg0) {
		
		time++;
		if(time%100==0){
			this.isLive=false;
			time=0;
		}

		if (isLive) {
			arg0.drawImage(gs.LifeIMG, x, y, 22	, 20, gs);
			

		}
		
		

	}

}
