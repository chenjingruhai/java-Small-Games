package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.sound.midi.Receiver;

public class MyPlane {

	int x;
	int y;
	int width;
	int height;

	GameMain gs = null;
	boolean isLive;
	boolean U, D, L, R; // �������

	public MyPlane(int x, int y, int width, int hight, GameMain gs,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = hight;
		this.gs = gs;
		this.isLive = isLive;
	}

	public void drawMyPlane(Graphics arg0) {
		if (!this.isLive) {
			return;
		}
		arg0.drawImage(gs.myPlaneIMG, x, y, width, height, gs);// �û��ʶ��󻭱���

		if (U) {
			y -= 10;
		}
		if (D) {
			y += 10;
		}
		if (L) {
			x -= 10;
		}
		if (R) {
			x += 10;
		}

		if (this.x >= 500 - width) {
			this.x = 500 - width;
		}
		if (this.x <= 0) {
			this.x = 0;
		}
		if (this.y >= 700 - height) {
			this.y = 700 - height;
		}
		if (this.y <= 0) {
			this.y = 0;
		}

	}

	// �ɻ��ƶ�
	public void movePlane(KeyEvent arg0) {

		int code = arg0.getKeyCode();// �õ���ֵ

		switch (code) {
		case KeyEvent.VK_W:
			U = true;
			break;
		case KeyEvent.VK_S:
			D = true;
			break;
		case KeyEvent.VK_A:
			L = true;
			break;
		case KeyEvent.VK_D:
			R = true;
			break;
		case KeyEvent.VK_J:
			if (this.isLive) {
				// ʵ�����ӵ�����
				Bulle bulle = new Bulle(x + 20, y, 10, 10, gs, true);
				gs.Bullelist.add(bulle);

				gs.sound.playSound("music/Beam.mp3");
			}

			break;

		default:
			break;
		}

	}

	// �ɻ��ƶ�
	public void StopPlane(KeyEvent arg0) {

		int code = arg0.getKeyCode();// �õ���ֵ

		switch (code) {
		case KeyEvent.VK_W:
			U = false;
			break;
		case KeyEvent.VK_S:
			D = false;
			break;
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

	// �õ�����
	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);

	}

	public void isXJ(ArrayList<EnemyPlane> EPlaneList) {
		for (int i = 0; i < EPlaneList.size(); i++) {
			EnemyPlane eplane = EPlaneList.get(i);// �ó�һ���л�

			if (this.isLive && this.getRec().intersects(eplane.getRec())) {
				eplane.isLive = false;
				// eplane.isLive=false;

				gs.boold -= 50;
				if (gs.boold <= 0) {

					this.isLive = false;

				}
			}
		}
	}

	public void isXJplane(ArrayList<EBulle> eBullelist) {
		for (int i = 0; i < eBullelist.size(); i++) {
			EBulle eBulle = eBullelist.get(i);// �ó�һ���л��ӵ�

			if (this.isLive && this.getRec().intersects(eBulle.getRec())) {
				// this.isLive=false;
				eBulle.isLive = false;
				gs.boold -= 50;
				if (gs.boold <= 0) {

					this.isLive = false;

				}
			}
		}
	}

}
