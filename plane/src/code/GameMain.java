package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

/*
 * ��Ȩ��Yishan Wu
 * �汾��1.0
 * ʱ�䣺2020.07.01
 */

public class GameMain extends Frame {
	
	
	Toolkit tool = Toolkit.getDefaultToolkit();//ʵ����һ������
	//�ù��߶����õ�ͼƬ����
	Image bgIMG = tool.getImage(GameMain.class.getResource("/images/bg01.jpg"));
	
	Image myPlaneIMG = tool.getImage(GameMain.class.getResource("/images/player05.png"));
	
	Image EnemyPlaneIMG = tool.getImage(GameMain.class.getResource("/images/enemy01.png"));
	
	Image BulleIMG = tool.getImage(GameMain.class.getResource("/images/bul01.png"));
	
	Image BossIMG = tool.getImage(GameMain.class.getResource("/images/boss_2.png"));
	
	Image BombIMG = tool.getImage(GameMain.class.getResource("/images/bomb_enemy_3.png"));
	
	Image OverIMG = tool.getImage(GameMain.class.getResource("/images/gameover.png"));
	
	BackGround backOBJ = new BackGround(0, 0, 500, 700, this);//ʵ����һ����������
	MyPlane myPlaneOBJ = new MyPlane(200, 500, 50, 50, this, true);//ʵ����һ���ɻ�
	
	Bomb bomb = null;//ʵ����һ����ը����
	
	
	
	//������ֵĵл�
	Random r = new Random();

    Boss bossOBJ = new Boss(200,100,100,100,this,true);   //ʵ����һ��boss
    
    
    
  //�����ӵ�����
	ArrayList<Bulle> Bullelist = new ArrayList<Bulle>();
	//�л�����
	ArrayList<EnemyPlane> EPlaneList = new ArrayList<EnemyPlane>();
	//�л��ӵ�����
	ArrayList<EBulle> eBullelist = new ArrayList<EBulle>();
	
	int boold = 200;//Ѫ��
	int score=0;//����
	int overY = 800;
	
	GameSound sound = new GameSound();//ʵ������������
	
	@Override
	public void paint(Graphics arg0) {
		
		backOBJ.drawBG(arg0);//���û������ķ���
		myPlaneOBJ.drawMyPlane(arg0);//���û��ɻ��ķ���
		myPlaneOBJ.isXJ(EPlaneList);
		myPlaneOBJ.isXJplane(eBullelist);
	//	enemOBJ.drawEnemyPlane(arg0);//���û��л�01�ķ���
		bossOBJ.drawBoss(arg0);//���û�boss����
		//�ҷ��ɻ����ӵ�
		for (int i = 0; i < Bullelist.size(); i++) {
			Bulle bulle = Bullelist.get(i);
			bulle.drawBullet(arg0);
			bulle.isXJ(EPlaneList);//�ж��ཻ
			
			//�Ƴ�������������ӵ�
			if(bulle.y<0||!bulle.isLive){
				Bullelist.remove(bulle);
			}
			
		}
		//ѭ�������ж���л�������
		for (int i = 0; i < EPlaneList.size();i++) {
			EnemyPlane ePlane = EPlaneList.get(i);
			ePlane.drawEnemyPlane(arg0);
			
			
		}
		
		//�з��ɻ����ӵ�
		for (int i = 0; i < eBullelist.size(); i++) {
			EBulle bulle = eBullelist.get(i);
			bulle.draweBullet(arg0);
		}
		
		arg0.drawImage(myPlaneIMG, 20, 50, 20, 20, this);
		arg0.setColor(Color.red);
		//���ɻ���Ѫ��
		arg0.drawRect(50, 50, 200, 20);
		arg0.fillRect(50, 50, boold, 20);
		
		//������Ϸ�÷�
		arg0.setFont(new Font("����", Font.BOLD, 15));
		arg0.drawString("�÷֣�"+score, 380, 70);
		
		//��ը
		if(bomb!=null){
			bomb.drawBomb(arg0);//����ը����
		}
		bomb = null;
		
		//������ʾ
		
		
		
		//��������
		
		
		if(!myPlaneOBJ.isLive){
			overY-=5;
		}
			if(overY>350){
			arg0.drawImage(OverIMG, 50, overY, 400, 100, this);
			}else{
				arg0.drawImage(OverIMG, 50, 350, 400, 100, this);
			}
		
		
		
		}
	
	
		
	
	
	
	
	/*
	 * ˫���弼��
	 * 1.����һ���������Ļ���
	 * 2.�õ��������ı�
	 * 3.�ڻ��������滭
	 * 4.�ѻ������Ľ��洫�봰�ڽ���
	 */
	
	Image tempIMG = null;//����һ���յı���ͼƬ
	@Override
	public void update(Graphics arg0) {
		
		if(tempIMG==null){
			tempIMG = this.createImage(500,700);//�ڻ�������������ͼƬ
			
		}
		
		Graphics newG = tempIMG.getGraphics();//�õ��������Ļ���
		
		arg0.drawImage(tempIMG, 0, 0, 500,700,this);
		
		paint(newG);
	}
	
	
	
	int time=0;//ʱ��Ƶ�ʱ���
	/*
	 * ���̣߳�����
	 * 1.��һ����ͨ��̳�Thread��
	 * 2.��дrun
	 * 3.�����߳�
	 */
	class  MyThread extends Thread{
		@Override
		public void run() {
			
			while(true){
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				
				time++;
				
				if(time%30==0){
					//��ӵл�����
					int rx=r.nextInt(450);
					EnemyPlane eplane = new EnemyPlane(rx,0, 40, 40, GameMain.this, true);
					EPlaneList.add(eplane);			
					
					time=0;
				}
			
			}
		}
	}
	
	
	

	
	/*
	 * ��ʼ����Ϸ
	 */
	public void init() {
		this.setTitle("����ս��");
		
		sound.playBgSound("music/BGM_0001.mp3");
		this.setResizable(false);//�������
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);//����
		this.setVisible(true);
		
		
		new MyThread().start();
		
		
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
				myPlaneOBJ.movePlane(arg0);
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				myPlaneOBJ.StopPlane(arg0);
			}
		});
		
		
	}
	
	

	/**
	 * @param args
	 */
	//public static void main(String[] args) {
		
	//	new GameMain().init();//���ó�ʼ��init����

	//}
	
	
	
	

}
