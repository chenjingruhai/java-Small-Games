package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EBulle {
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	boolean isLive;
	public EBulle(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	//ÄÃµ½¾ØÐÎ
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		return new Rectangle(0,0,0,0);
		
	}
	
	int time=0;
	public void draweBullet(Graphics arg0 ) {
		time++;
		arg0.drawImage(gs.BulleIMG, x,y,20,20, gs);
			
		y+=10;
	}

}
