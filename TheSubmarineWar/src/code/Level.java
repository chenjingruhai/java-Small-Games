package code;

import java.awt.Graphics;

public class Level {
	int x;
	int y;
	int width;
	int height;
	
	
	
	GameMain gs = null;
	boolean isLive;

	

	




	public Level(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}







	
	public void drawNext(Graphics arg0){
		
		
		if(!this.isLive){
			return;
		}else{
			arg0.drawImage(gs.NextIMG, x, y, width,height,gs);//ÓÃ»­±Ê¶ÔÏó»­±³¾°
			y+=5;
			if(y>=800){
				y=800;
			}
		}
		
		
		
		
			
			
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
