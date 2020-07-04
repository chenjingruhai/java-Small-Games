package code;

import java.awt.Graphics;

public class BackGround {
	
	int x;
	int y;
	int width;
	int height;
	int y1=-700;
	
	GameMain gs = null;
	
	
	
	
	
	//构造方法用于接收另一个类传递的值
	public BackGround(int x, int y, int width, int height, GameMain gs) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
	}






	/*
	 * 画背景
	 */
	public void drawBG(Graphics arg0) {
		arg0.drawImage(gs.bgIMG, x, y, width,height,gs);//用画笔对象画背景
		arg0.drawImage(gs.bgIMG, x, y1, width,height,gs);//用画笔对象画背景
		
		if(y1>=0){
			y1=-700;
			y=0;
			
		}
		y+=2;
		y1+=2;
		
		
	}

}
