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

	boolean L, R; // �������

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
		arg0.drawImage(gs.warshipIMG, x, y, width, height, gs);// �û��ʶ��󻭱���

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

	// ս���ƶ�
	public void moveShip(KeyEvent arg0) {

		int code = arg0.getKeyCode();// �õ���ֵ

		switch (code) {

		case KeyEvent.VK_A:
			L = true;
			break;
		case KeyEvent.VK_D:
			R = true;
			break;
		case KeyEvent.VK_SPACE:
			if (this.isLive) {
				// ʵ�����ӵ�����
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

	// ս��ֹͣ�ƶ�
	public void StopShip(KeyEvent arg0) {

		int code = arg0.getKeyCode();// �õ���ֵ

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
	//�õ�����
	public Rectangle getRec() {
		if(this.isLive){
			return new Rectangle(x, y, width, height);
		}
		return new Rectangle(0,0,0,0);
		
	}
	
	
	//�ҷ�ս���������ཻ
	public void isXJTor(ArrayList<Torpedo> torlist) {
		for (int i = 0; i < torlist.size(); i++) {
			Torpedo tor = torlist.get(i);// �ó�һ��Ǳͧһ������

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
	
	//�ҷ�ս���͵��ߣ�С�����ཻ
	public void isXJLife(ArrayList<Life> lifelist) {
		for (int i = 0; i < lifelist.size(); i++) {
			Life lif = lifelist.get(i);// �ó�һ��С����

			if (this.isLive && this.getRec().intersects(lif.getRec())) {
				
				lif.isLive = false;
				
				if (gs.boold <5) {

					gs.boold += 1;

				}
			}
		}
	}
	
	//�ҷ�ս���͵��ߣ�С�����ཻ
	public void isXJStar(ArrayList<Star> starlist) {
		for (int i = 0; i < starlist.size(); i++) {
			Star lif = starlist.get(i);// �ó�һ������

			if (this.isLive && this.getRec().intersects(lif.getRec())) {
				
				lif.isLive = false;
				gs.score+=10;
				
			}
		}
	}
	
	
	
	

}
