package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Submarine2 {
	
	int x;
	int y;
	int width;
	int height;

	
	GameMain gs = null;
	boolean isLive;
	
	

	
	
	





	public Submarine2(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	//拿到矩形
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		
		return new Rectangle(0,0,0,0);
		

	}









	int time=0;
	public void drawSubmarine(Graphics arg0){

		if(!this.isLive){
			return;
		}
		arg0.drawImage(gs.sub2IMG, x, y, width,height,gs);//用画笔对象画背景
		time++;
		if(time%100==0){
			Torpedo tor = new Torpedo(x+45, y+2, 11, 10, gs, true);
			
			gs.Torlist.add(tor);
		}	
		
		
		x+=2;
		

	}

}
