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
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.PlainDocument;



/*
 * ��Ȩ��Yishan Wu
 * �汾��1.0
 * ʱ�䣺2020.07.03
 */



public class GameMain extends Frame {
	
	Toolkit tool = Toolkit.getDefaultToolkit();//ʵ����һ������
	//�ù��߶����õ�ͼƬ����
	Image bgIMG = tool.getImage(GameMain.class.getResource("/images/bg.jpg"));//���ر���ͼƬ
	Image warshipIMG = tool.getImage(GameMain.class.getResource("/images/warship.png"));//����
	Image subIMG = tool.getImage(GameMain.class.getResource("/images/submarine.png"));//����Ǳͧ
	Image sub2IMG = tool.getImage(GameMain.class.getResource("/images/submarine2.png"));//����Ǳͧ
	Image BombIMG = tool.getImage(GameMain.class.getResource("/images/bomb.png"));//������ը��
	Image ExpIMG = tool.getImage(GameMain.class.getResource("/images/explosion.png"));//��ը����ͼƬ
	Image TorIMG = tool.getImage(GameMain.class.getResource("/images/torpedo.png"));//Ǳͧ����
	Image OverIMG = tool.getImage(GameMain.class.getResource("/images/gameover.png"));//����
	Image LifeIMG = tool.getImage(GameMain.class.getResource("/images/life.png"));//С����(����)
	Image ScoreIMG = tool.getImage(GameMain.class.getResource("/images/star.jpg"));//С����(����)
	Image NextIMG = tool.getImage(GameMain.class.getResource("/images/next.png"));//������ʾ
	
	int boold = 5;//������Max=5��
	Random r = new Random();//�������Ǳͧ
	int score=0;//����
	int overY = 650;
	int level = 1;
	int amm = 5000;//��ҩ
	int hell=0;//���ӵз�Ǳͧ���ֵ�Ƶ�ʣ�Ҳ�ɿ��Ƶ��ߵ�Ƶ��
	
	
	
	
	
	BackGround backOBJ =  new BackGround(0, 0, 1024, 682, this);//ʵ��������
	MyWarship shipOBJ = new MyWarship(200, 300, 66	, 26, this, true);//ʵ��������
	//Submarine subOBJ = new Submarine(400, 500, 64, 24, this,true);//ʵ����Ǳͧ
	Explosion exp = null;
	Level lev=null;
	GameSound sound = new GameSound();//ʵ������������
	//Level lvOBJ = new Level(450, 200, 52, 23, this, true);//ʵ������һ�ض���
	
	
	
	//����ը������
	ArrayList<Bomb> Bomblist = new ArrayList<Bomb>();
	//�����ƶ���Ǳͧ����
	ArrayList<Submarine> SubList = new ArrayList<Submarine>();
	//�����ƶ���Ǳͧ����
	ArrayList<Submarine2> SubList2 = new ArrayList<Submarine2>();
	//Ǳͧ���׼���
	ArrayList<Torpedo> Torlist = new ArrayList<Torpedo>();
	//С���ļ���
	ArrayList<Life> LifeList = new ArrayList<Life>();
	//С���Ǽ���
	ArrayList<Star> StarList = new ArrayList<Star>();
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void paint(Graphics arg0) {
		
		backOBJ.drawBG(arg0);//������
		shipOBJ.drawMyShip(arg0);//��ս��
		shipOBJ.isXJTor(Torlist);//ս����������ײ
		shipOBJ.isXJLife(LifeList);//ս����С������ײ������+1������ֵ����5����Ч��
		shipOBJ.isXJStar(StarList);//ս����С������ײ���÷�+10
		
		
		
		
		
		
		//�ҷ�ս��ը��
		for (int i = 0; i < Bomblist.size(); i++) {
			Bomb bomb = Bomblist.get(i);
			bomb.drawBomb(arg0);
			bomb.isXJ(SubList);
			bomb.isXJ2(SubList2);
			
			
			//�Ƴ�������������ӵ�
			if(bomb.y<0||!bomb.isLive){
				Bomblist.remove(bomb);
			}	
		}
		
		
		
		//�з�Ǳͧ������
		for (int i = 0; i < Torlist.size(); i++) {
			Torpedo tor = Torlist.get(i);
			tor.drawTor(arg0);
			
		}
		
		//ѭ�������ж�������Ǳͧ������
		for (int i = 0; i < SubList.size();i++) {
			Submarine sub = SubList.get(i);
			sub.drawSubmarine(arg0);
			
			
		}
		//ѭ�������ж�������Ǳͧ������
		for (int i = 0; i < SubList2.size();i++) {
			Submarine2 sub2 = SubList2.get(i);
			sub2.drawSubmarine(arg0);	
			
		}
		//ѭ�������ж���С���Ļ�����
		for (int i = 0; i < LifeList.size(); i++) {
			Life lif = LifeList.get(i);
			lif.drawLife(arg0);
			
			
		}
		//ѭ�������ж���С���ǻ�����
		for (int i = 0; i < StarList.size(); i++) {
			Star s = StarList.get(i);
			s.drawStar(arg0);
			
			
		}
		
		
		
		
		arg0.setColor(Color.red);
		//������Ϸ�÷ֺ�ս�������͹ؿ�����ʾ
		arg0.setFont(new Font("����", Font.BOLD, 15));
		arg0.drawString("�÷֣�"+score, 900, 63);
		arg0.drawString("������"+boold, 900, 85);
		arg0.drawImage(ScoreIMG, 870, 48, 22, 20, this);
		arg0.drawImage(LifeIMG, 870, 68, 22, 20, this);
		
		arg0.drawString("�ؿ���"+level, 50, 63);
		arg0.drawString("��ҩ��"+amm, 50, 85);
		
		
		
		//��ը
		if(exp!=null){
			exp.drawExp(arg0);//����ը����
			
		}
		exp = null;
		
		if(lev!=null){
			lev.drawNext(arg0);
		}
		//�����10�أ�ͨ��
		if(level==10){
			arg0.setFont(new Font("����", Font.BOLD, 30));
			arg0.drawString("��ϲ������ͨ�أ�", 300, 100);
		}
		
		//��������1,ը���ù�
		if(amm<=0){
			shipOBJ.isLive=false;
			overY-=5;
			if(overY>350){
				arg0.drawImage(OverIMG, 300, overY, 400, 100, this);
				}else{
					arg0.drawImage(OverIMG, 300, 350, 400, 100, this);
					arg0.setColor(Color.yellow);
					//������Ϸ�÷�
					arg0.setFont(new Font("����", Font.BOLD, 30));
					arg0.drawString("�÷֣�"+score, 420, 330);
					
				}
		}
		
		//��������2,����ֵΪ0
		if(!shipOBJ.isLive){
			overY-=5;
		}
			if(overY>350){
			arg0.drawImage(OverIMG, 300, overY, 400, 100, this);
			}else{
				arg0.drawImage(OverIMG, 300, 350, 400, 100, this);
				arg0.setColor(Color.yellow);
				//������Ϸ�÷�
				arg0.setFont(new Font("����", Font.BOLD, 30));
				arg0.drawString("�÷֣�"+score, 420, 330);
				
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
			tempIMG = this.createImage(1024, 628);//�ڻ�������������ͼƬ
			
		}
		
		Graphics newG = tempIMG.getGraphics();//�õ��������Ļ���
		
		arg0.drawImage(tempIMG, 0, 0, 1024, 628,this);
		
		paint(newG);
	}
	
	
	
	
	/*
	 * �ı�ʱ��Ƶ������ʾ�ؿ�
	 */
	
	int time=0;//ʱ��Ƶ�ʱ���
	int xx=0;//����С����ʱ��Ƶ�ʱ���
	int xxx=0;//����С����ʱ��Ƶ�ʱ���
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
					
					e.printStackTrace();
				}
				repaint();
				
				time++;
				xx++;
				xxx++;
				if(time%(51-hell)==0){
					//�������Ǳͧ����
					int ry=r.nextInt(300);
					int rx=r.nextInt(500);
					Submarine sub = new Submarine(2*rx,ry+310, 64, 20, GameMain.this, true);
					SubList.add(sub);		
					
					//�������Ǳͧ
					int ry2=r.nextInt(300);
					int rx2=r.nextInt(500);
					Submarine2 sub2 = new Submarine2(2*rx2,ry2+310, 63, 19, GameMain.this, true);
					SubList2.add(sub2);
					
					
					time=0;
				}
				
				if(xx%400==0){
					//���С����
					int lx = r.nextInt(300);
					Life life = new Life(2*lx, 300, 22, 20, GameMain.this, true);
					LifeList.add(life);
					xx=0;
				}
				
				if(xxx%200==0){
					//���С����
					int lx = r.nextInt(300);
					Star s = new Star(2*lx, 300, 22, 20, GameMain.this, true);
					StarList.add(s);
					xxx=0;
					
				}
				
			
			}
		}
	
	}
	
	
	
	
	
	
	public void init() {
		
		sound.playBgSound("music/bg.mp3");
		this.setTitle("Ǳͧ��ս");
		this.setResizable(false);//�������
		this.setSize(1024, 628);
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
				shipOBJ.moveShip(arg0);
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				shipOBJ.StopShip(arg0);
			}
		});
		
		
		
		
		
	}
	

	
	
	
	//public static void main(String[] args) {
		
	//	new GameMain().init();//���ó�ʼ��init����
		

	//}
	
	
	
	
	
	
	
	
	
	

}
