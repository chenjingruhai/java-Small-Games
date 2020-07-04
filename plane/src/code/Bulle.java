package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bulle {
	
	int x;
	int y;
	int width;
	int height;
	GameMain gs = null;
	boolean isLive;
	public Bulle(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	public void drawBullet(Graphics arg0 ) {
		if(!this.isLive){
			return;
		}
		arg0.drawImage(gs.BulleIMG, x,y,width, height, gs);
		
		y-=5;
	}
	
	
	
	
	//拿到矩形
	public Rectangle getRec(){
		return new Rectangle(x,y,width,height);
		
	}
	
	//判断一颗子弹是否和多个敌机相交
	
	public void isXJ(ArrayList<EnemyPlane> EPlaneList ) {
		for (int i = 0; i < EPlaneList.size(); i++) {
			EnemyPlane eplane = EPlaneList.get(i);//拿出一个敌机
			
			if(this.isLive && this.getRec().intersects(eplane.getRec())){
				this.isLive=false;
				eplane.isLive=false;
				gs.score++;
				
				gs.bomb = new Bomb(x, y, 80, 80, gs);//实例化爆炸
				gs.sound.playSound("music/Explode.mp3");
			}
		}
	}
	
	

}
