package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bomb {

	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	boolean isLive;
	public Bomb(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	//�õ�����
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		
		return new Rectangle(0,0,0,0);	

	}
	
	
	
//�ж�һ���ӵ��Ƿ�������Ǳͧ�ཻ
	
	public void isXJ(ArrayList<Submarine> subList ) {
		for (int i = 0; i < subList.size(); i++) {
			Submarine sub = subList.get(i);//�ó�һ���л�
			
			if(this.isLive && this.getRec().intersects(sub.getRec())){
				this.isLive=false;
				sub.isLive=false;
				gs.score++;
				
				gs.amm+=1;
				
				if(gs.score%10==0){
					
					
					if(gs.shipOBJ.isLive){
						gs.lev = new Level(450, 300, 52, 23, gs, true);//ʵ������һ�ض���//������ʾ
						if(gs.level<10){
							gs.level+=1;
							gs.hell+=5;
						}
						
						if(gs.hell>10){
							gs.hell+=0;
							gs.lev.isLive=false;
							
						}
					}
					
					
					
					
				}
				
				gs.exp = new Explosion(x, y, 60, 62, gs);//ʵ������ը
				gs.sound.playSound("music/Explode.mp3");
				
			}
		}
	}
	
//�ж�һ���ӵ��Ƿ�������Ǳͧ�ཻ
	
	public void isXJ2(ArrayList<Submarine2> subList2 ) {
		for (int i = 0; i < subList2.size(); i++) {
			Submarine2 sub2 = subList2.get(i);//�ó�һ���л�
			
			if(this.isLive && this.getRec().intersects(sub2.getRec())){
				this.isLive=false;
				sub2.isLive=false;
				gs.score++;
				
				gs.amm+=1;
				
				if(gs.shipOBJ.isLive){
					if(gs.score%10==0){
						gs.lev  = new Level(450, 300, 52, 23, gs, true);//ʵ������һ�ض���//������ʾ
						if(gs.level<10){
							gs.level+=1;
							gs.hell+=5;
						}
						
						if(gs.hell>=10){
							gs.hell+=0;
							gs.lev.isLive=false;
						
						}
					
				}
				
				gs.exp = new Explosion(x, y, 60, 62, gs);
				gs.sound.playSound("music/Explode.mp3");
				
			}
		}}
	}
	
	
	
	
	
	
	
	public void drawBomb(Graphics arg0 ) {
		if(!this.isLive){
			return;
		}
		arg0.drawImage(gs.BombIMG, x,y,width, height, gs);
		
		y+=5;
	}
	
	
}
