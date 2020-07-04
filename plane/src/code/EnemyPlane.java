package code;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyPlane {
	int x;
	int y;
	int width;
	int height;

	GameMain gs = null;
	boolean isLive;

	public EnemyPlane(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}

	int fy = 0;// ������� 0��ʾ�����ƶ���1��ʾ�����ƶ�
	
	
	
	
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		
		return new Rectangle(0,0,0,0);
		

	}

	int time = 0;

	public void drawEnemyPlane(Graphics arg0) {
		if (!this.isLive) {
			return;
		}
		arg0.drawImage(gs.EnemyPlaneIMG, x, y, width, height, gs);// �û��ʶ��󻭵л�
		
		//��һ�ηɻ���ͬʱ��һ���ӵ�
		//EBulle ebulle = new EBulle(x+15, y+25, 10, 10, gs, true);
		//gs.eBullelist.add(ebulle);
		
		time++;
		if(time%15==0){
			EBulle ebulle = new EBulle(x+15, y+25, 10, 10, gs, true);
			gs.eBullelist.add(ebulle);
		}
		
		y += 4;

	}
}
