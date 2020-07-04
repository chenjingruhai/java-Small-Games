package code;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartGame extends Frame{

	
	
	Toolkit tool = Toolkit.getDefaultToolkit();//ʵ����һ������
	//�ù��߶����õ�ͼƬ����
	Image startIMG = tool.getImage(GameMain.class.getResource("/images/strart.jpg"));
	
	
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(startIMG, 0, 0, 353, 466, this);
	}
	
	GameSound sound = new GameSound();//ʵ������������
	
	
	
	
	/*
	 * ��ʼ����Ϸ
	 */
	public void init() {
		sound.playBgSound("music/zengjia.mp3");
		this.setTitle("����ս��");
		
		
		this.setResizable(false);//�������
		this.setSize(353, 466);
		this.setLocationRelativeTo(null);//����
		this.setVisible(true);
		
		
		//�����ڵĹرհ�ť���蹦��
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		
		//����¼�
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x=arg0.getX();
				int y=arg0.getY();
				//�ж������ڿ�ʼ��ť����
				if(x>116&&x<235&&y>190&&y<206){
					StartGame.this.setVisible(false);//��ʼ������ʧ
					new GameMain().init();//��ʾ��Ϸ������
					sound.stopSound();//ֹͣ��������������
					
				}
			}
		});
		
	}
	
	
	public static void main(String[] args) {
		new StartGame().init();

	}

}
