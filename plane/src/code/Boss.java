package code;

import java.awt.Graphics;

public class Boss {
	
	int x;
	int y;
	int width;
	int height;
	
	GameMain gs = null;
	boolean isLive;
	public Boss(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}
	
	
	
	int fx = 0;//�������  0��ʾ�����ƶ���1��ʾ�����ƶ�

	
	public void drawBoss(Graphics arg0){
		arg0.drawImage(gs.BossIMG, x, y, width,height,gs);//�û��ʶ��󻭵л�
		
		if(fx==0){
			x-=5;
			if(x<=0){
				fx=1;
			}
		}else if(fx==1){
			x+=5;
			if(x>=450){
				fx=0;
			}
		}
			
			
		
	}

}
