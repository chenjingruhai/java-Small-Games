package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartGame extends Frame{

	
	Toolkit tool = Toolkit.getDefaultToolkit();//ʵ����һ������
	//�ù��߶����õ�ͼƬ����
	Image startIMG = tool.getImage(GameMain.class.getResource("/images/start.png"));//���ر���ͼƬ
	
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(startIMG, 0, 0, 641, 429, this);
		arg0.setColor(Color.red);
		arg0.setFont(new Font("����", Font.BOLD, 20));
		arg0.drawString("����Enter��������Ϸ", 228, 385);
		arg0.setColor(Color.yellow);
		arg0.drawString("ս�������һ��!", 245, 363);
		
		arg0.setColor(Color.black);
		arg0.drawString("A�������ƣ�D�������ƣ����¿ո�Ͷ��ը��", 128, 140);
		
	}
	
	GameSound sound = new GameSound();//ʵ������������
	
	
	
	public void init() {
		sound.playBgSound("music/start.mp3");
		this.setTitle("Ǳͧ��ս");
		
		
		this.setResizable(false);//�������
		this.setSize(641, 429);
		this.setLocationRelativeTo(null);//����
		this.setVisible(true);
		
		
		//�����ڵĹرհ�ť���蹦��
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		//��������ļ����¼���
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER){
					StartGame.this.setVisible(false);//��ʼ������ʧ
					new GameMain().init();//��ʾ��Ϸ������
					sound.stopSound();//ֹͣ��������������
				}
				
			
		
		
			}
	
			});
		
		
		//	@Override
		///	public void keyPressed(KeyEvent arg0) {
			//	myPlaneOBJ.movePlane(arg0);
				
			//}
			
		//	@Override
		//	public void keyReleased(KeyEvent arg0) {
			//	myPlaneOBJ.StopPlane(arg0);
			//}
		//});
		
		}
	
	
	
	public static void main(String[] args) {
		
		new StartGame().init();

	}

}
