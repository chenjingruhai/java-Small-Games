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
	
	
	
	
	//�õ�����
	public Rectangle getRec(){
		return new Rectangle(x,y,width,height);
		
	}
	
	//�ж�һ���ӵ��Ƿ�Ͷ���л��ཻ
	
	public void isXJ(ArrayList<EnemyPlane> EPlaneList ) {
		for (int i = 0; i < EPlaneList.size(); i++) {
			EnemyPlane eplane = EPlaneList.get(i);//�ó�һ���л�
			
			if(this.isLive && this.getRec().intersects(eplane.getRec())){
				this.isLive=false;
				eplane.isLive=false;
				gs.score++;
				
				gs.bomb = new Bomb(x, y, 80, 80, gs);//ʵ������ը
				gs.sound.playSound("music/Explode.mp3");
			}
		}
	}
	
	

}
