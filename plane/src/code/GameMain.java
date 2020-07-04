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
 * 版权：Yishan Wu
 * 版本：1.0
 * 时间：2020.07.01
 */

public class GameMain extends Frame {
	
	
	Toolkit tool = Toolkit.getDefaultToolkit();//实例化一个对象
	//用工具对象拿到图片对象
	Image bgIMG = tool.getImage(GameMain.class.getResource("/images/bg01.jpg"));
	
	Image myPlaneIMG = tool.getImage(GameMain.class.getResource("/images/player05.png"));
	
	Image EnemyPlaneIMG = tool.getImage(GameMain.class.getResource("/images/enemy01.png"));
	
	Image BulleIMG = tool.getImage(GameMain.class.getResource("/images/bul01.png"));
	
	Image BossIMG = tool.getImage(GameMain.class.getResource("/images/boss_2.png"));
	
	Image BombIMG = tool.getImage(GameMain.class.getResource("/images/bomb_enemy_3.png"));
	
	Image OverIMG = tool.getImage(GameMain.class.getResource("/images/gameover.png"));
	
	BackGround backOBJ = new BackGround(0, 0, 500, 700, this);//实例化一个背景对象
	MyPlane myPlaneOBJ = new MyPlane(200, 500, 50, 50, this, true);//实例化一个飞机
	
	Bomb bomb = null;//实例化一个爆炸对象
	
	
	
	//随机出现的敌机
	Random r = new Random();

    Boss bossOBJ = new Boss(200,100,100,100,this,true);   //实例化一个boss
    
    
    
  //创建子弹集合
	ArrayList<Bulle> Bullelist = new ArrayList<Bulle>();
	//敌机集合
	ArrayList<EnemyPlane> EPlaneList = new ArrayList<EnemyPlane>();
	//敌机子弹集合
	ArrayList<EBulle> eBullelist = new ArrayList<EBulle>();
	
	int boold = 200;//血量
	int score=0;//分数
	int overY = 800;
	
	GameSound sound = new GameSound();//实例化声音对象
	
	@Override
	public void paint(Graphics arg0) {
		
		backOBJ.drawBG(arg0);//调用画背景的方法
		myPlaneOBJ.drawMyPlane(arg0);//调用画飞机的方法
		myPlaneOBJ.isXJ(EPlaneList);
		myPlaneOBJ.isXJplane(eBullelist);
	//	enemOBJ.drawEnemyPlane(arg0);//调用画敌机01的方法
		bossOBJ.drawBoss(arg0);//调用画boss方法
		//我方飞机的子弹
		for (int i = 0; i < Bullelist.size(); i++) {
			Bulle bulle = Bullelist.get(i);
			bulle.drawBullet(arg0);
			bulle.isXJ(EPlaneList);//判断相交
			
			//移除出界和死掉的子弹
			if(bulle.y<0||!bulle.isLive){
				Bullelist.remove(bulle);
			}
			
		}
		//循环将所有对象敌机画出来
		for (int i = 0; i < EPlaneList.size();i++) {
			EnemyPlane ePlane = EPlaneList.get(i);
			ePlane.drawEnemyPlane(arg0);
			
			
		}
		
		//敌方飞机的子弹
		for (int i = 0; i < eBullelist.size(); i++) {
			EBulle bulle = eBullelist.get(i);
			bulle.draweBullet(arg0);
		}
		
		arg0.drawImage(myPlaneIMG, 20, 50, 20, 20, this);
		arg0.setColor(Color.red);
		//画飞机的血条
		arg0.drawRect(50, 50, 200, 20);
		arg0.fillRect(50, 50, boold, 20);
		
		//设置游戏得分
		arg0.setFont(new Font("宋体", Font.BOLD, 15));
		arg0.drawString("得分："+score, 380, 70);
		
		//爆炸
		if(bomb!=null){
			bomb.drawBomb(arg0);//画爆炸对象
		}
		bomb = null;
		
		//过关提示
		
		
		
		//结束界面
		
		
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
	 * 双缓冲技术
	 * 1.创建一个缓冲区的画布
	 * 2.拿到缓冲区的笔
	 * 3.在缓冲区里面画
	 * 4.把缓冲区的界面传入窗口界面
	 */
	
	Image tempIMG = null;//声明一个空的背景图片
	@Override
	public void update(Graphics arg0) {
		
		if(tempIMG==null){
			tempIMG = this.createImage(500,700);//在缓冲区创建背景图片
			
		}
		
		Graphics newG = tempIMG.getGraphics();//拿到缓冲区的画笔
		
		arg0.drawImage(tempIMG, 0, 0, 500,700,this);
		
		paint(newG);
	}
	
	
	
	int time=0;//时间频率变量
	/*
	 * 多线程：三步
	 * 1.把一个普通类继承Thread类
	 * 2.重写run
	 * 3.启动线程
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
					//添加敌机集合
					int rx=r.nextInt(450);
					EnemyPlane eplane = new EnemyPlane(rx,0, 40, 40, GameMain.this, true);
					EPlaneList.add(eplane);			
					
					time=0;
				}
			
			}
		}
	}
	
	
	

	
	/*
	 * 初始化游戏
	 */
	public void init() {
		this.setTitle("雷霆战机");
		
		sound.playBgSound("music/BGM_0001.mp3");
		this.setResizable(false);//禁用最大化
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);//居中
		this.setVisible(true);
		
		
		new MyThread().start();
		
		
		//给窗口的关闭按钮赋予功能
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		//窗口里面的键盘事件，
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
		
	//	new GameMain().init();//调用初始化init方法

	//}
	
	
	
	

}
