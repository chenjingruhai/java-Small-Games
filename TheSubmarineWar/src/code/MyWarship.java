package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MyWarship {

	int x;
	int y;
	int width;
	int height;

	boolean L, R; // 方向变量

	GameMain gs = null;
	boolean isLive;

	public MyWarship(int x, int y, int width, int height, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gs = gs;
		this.isLive = isLive;
	}

	public void drawMyShip(Graphics arg0) {

		if (!this.isLive) {
			return;
		}
		arg0.drawImage(gs.warshipIMG, x, y, width, height, gs);// 用画笔对象画背景

		if (L) {
			x -= 10;
		}
		if (R) {
			x += 10;
		}

		if (this.x >= 1024 - width) {
			this.x = 1024 - width;
		}
		if (this.x <= 0) {
			this.x = 0;
		}

	}

	// 战舰移动
	public void moveShip(KeyEvent arg0) {

		int code = arg0.getKeyCode();// 拿到键值

		switch (code) {

		case KeyEvent.VK_A:
			L = true;
			break;
		case KeyEvent.VK_D:
			R = true;
			break;
		case KeyEvent.VK_SPACE:
			if (this.isLive) {
				// 实例化子弹对象
				if(gs.amm>0){
					Bomb bomb = new Bomb(x + 20, y, 10, 10, gs, true);
					gs.Bomblist.add(bomb);
					gs.amm-=1;
					gs.sound.playSound("music/Beam.mp3");
				}
				
				
			}

			break;

		default:
			break;
		}

	}

	// 战舰停止移动
	public void StopShip(KeyEvent arg0) {

		int code = arg0.getKeyCode();// 拿到键值

		switch (code) {

		case KeyEvent.VK_A:
			L = false;
			break;
		case KeyEvent.VK_D:
			R = false;
			break;

		default:
			break;
		}

	}
	//拿到矩形
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		return new Rectangle(0,0,0,0);
		
	}
	
	
	//我方战舰和鱼雷相交
	public void isXJTor(ArrayList<Torpedo> torlist) {
		for (int i = 0; i < torlist.size(); i++) {
			Torpedo tor = torlist.get(i);// 拿出一个潜艇一个鱼雷

			if (this.isLive && this.getRec().intersects(tor.getRec())) {
				// this.isLive=false;
				
				tor.isLive = false;
				gs.boold -= 1;
				if (gs.boold <= 0) {

					this.isLive = false;

				}
			}
		}
	}
	
	//我方战舰和道具：小心心相交
	public void isXJLife(ArrayList<Life> lifelist) {
		for (int i = 0; i < lifelist.size(); i++) {
			Life lif = lifelist.get(i);// 拿出一个小心心

			if (this.isLive && this.getRec().intersects(lif.getRec())) {
				
				lif.isLive = false;
				
				if (gs.boold <5) {

					gs.boold += 1;

				}
			}
		}
	}
	
	//我方战舰和道具：小星星相交
	public void isXJStar(ArrayList<Star> starlist) {
		for (int i = 0; i < starlist.size(); i++) {
			Star lif = starlist.get(i);// 拿出一个星星

			if (this.isLive && this.getRec().intersects(lif.getRec())) {
				
				lif.isLive = false;
				gs.score+=10;
				
			}
		}
	}
	
	
	
	

}
