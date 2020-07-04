package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Torpedo {
	
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	boolean isLive;
	public Torpedo(int x, int y, int width, int height, GameMain gs,
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

	
	public void drawTor(Graphics arg0 ) {
		
		if(isLive){
			arg0.drawImage(gs.TorIMG, x, y,11 ,10,gs);
			y-=5;
			
			if(y<315){
				this.isLive=false;
			}
		}
		
		
		
	}

}
