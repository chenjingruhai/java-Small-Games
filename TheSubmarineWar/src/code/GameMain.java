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
 * 版权：Yishan Wu
 * 版本：1.0
 * 时间：2020.07.03
 */



public class GameMain extends Frame {
	
	Toolkit tool = Toolkit.getDefaultToolkit();//实例化一个对象
	//用工具对象拿到图片对象
	Image bgIMG = tool.getImage(GameMain.class.getResource("/images/bg.jpg"));//加载背景图片
	Image warshipIMG = tool.getImage(GameMain.class.getResource("/images/warship.png"));//军舰
	Image subIMG = tool.getImage(GameMain.class.getResource("/images/submarine.png"));//向左潜艇
	Image sub2IMG = tool.getImage(GameMain.class.getResource("/images/submarine2.png"));//向右潜艇
	Image BombIMG = tool.getImage(GameMain.class.getResource("/images/bomb.png"));//军舰的炸弹
	Image ExpIMG = tool.getImage(GameMain.class.getResource("/images/explosion.png"));//爆炸动画图片
	Image TorIMG = tool.getImage(GameMain.class.getResource("/images/torpedo.png"));//潜艇鱼雷
	Image OverIMG = tool.getImage(GameMain.class.getResource("/images/gameover.png"));//结束
	Image LifeIMG = tool.getImage(GameMain.class.getResource("/images/life.png"));//小红心(生命)
	Image ScoreIMG = tool.getImage(GameMain.class.getResource("/images/star.jpg"));//小星星(分数)
	Image NextIMG = tool.getImage(GameMain.class.getResource("/images/next.png"));//过关提示
	
	int boold = 5;//命数（Max=5）
	Random r = new Random();//随机出现潜艇
	int score=0;//分数
	int overY = 650;
	int level = 1;
	int amm = 5000;//弹药
	int hell=0;//增加敌方潜艇出现的频率，也可控制道具的频率
	
	
	
	
	
	BackGround backOBJ =  new BackGround(0, 0, 1024, 682, this);//实例化背景
	MyWarship shipOBJ = new MyWarship(200, 300, 66	, 26, this, true);//实例化军舰
	//Submarine subOBJ = new Submarine(400, 500, 64, 24, this,true);//实例化潜艇
	Explosion exp = null;
	Level lev=null;
	GameSound sound = new GameSound();//实例化声音对象
	//Level lvOBJ = new Level(450, 200, 52, 23, this, true);//实例化下一关动画
	
	
	
	//创建炸弹集合
	ArrayList<Bomb> Bomblist = new ArrayList<Bomb>();
	//向左移动的潜艇集合
	ArrayList<Submarine> SubList = new ArrayList<Submarine>();
	//向右移动的潜艇集合
	ArrayList<Submarine2> SubList2 = new ArrayList<Submarine2>();
	//潜艇鱼雷集合
	ArrayList<Torpedo> Torlist = new ArrayList<Torpedo>();
	//小心心集合
	ArrayList<Life> LifeList = new ArrayList<Life>();
	//小星星集合
	ArrayList<Star> StarList = new ArrayList<Star>();
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void paint(Graphics arg0) {
		
		backOBJ.drawBG(arg0);//画背景
		shipOBJ.drawMyShip(arg0);//画战舰
		shipOBJ.isXJTor(Torlist);//战舰和鱼雷碰撞
		shipOBJ.isXJLife(LifeList);//战舰和小心心碰撞，生命+1（生命值满（5）无效）
		shipOBJ.isXJStar(StarList);//战舰和小星星碰撞，得分+10
		
		
		
		
		
		
		//我方战舰炸弹
		for (int i = 0; i < Bomblist.size(); i++) {
			Bomb bomb = Bomblist.get(i);
			bomb.drawBomb(arg0);
			bomb.isXJ(SubList);
			bomb.isXJ2(SubList2);
			
			
			//移除出界和死掉的子弹
			if(bomb.y<0||!bomb.isLive){
				Bomblist.remove(bomb);
			}	
		}
		
		
		
		//敌方潜艇的鱼雷
		for (int i = 0; i < Torlist.size(); i++) {
			Torpedo tor = Torlist.get(i);
			tor.drawTor(arg0);
			
		}
		
		//循环将所有对象向左潜艇画出来
		for (int i = 0; i < SubList.size();i++) {
			Submarine sub = SubList.get(i);
			sub.drawSubmarine(arg0);
			
			
		}
		//循环将所有对象向右潜艇画出来
		for (int i = 0; i < SubList2.size();i++) {
			Submarine2 sub2 = SubList2.get(i);
			sub2.drawSubmarine(arg0);	
			
		}
		//循环将所有对象小心心画出来
		for (int i = 0; i < LifeList.size(); i++) {
			Life lif = LifeList.get(i);
			lif.drawLife(arg0);
			
			
		}
		//循环将所有对象小星星画出来
		for (int i = 0; i < StarList.size(); i++) {
			Star s = StarList.get(i);
			s.drawStar(arg0);
			
			
		}
		
		
		
		
		arg0.setColor(Color.red);
		//设置游戏得分和战舰命数和关卡数显示
		arg0.setFont(new Font("宋体", Font.BOLD, 15));
		arg0.drawString("得分："+score, 900, 63);
		arg0.drawString("命数："+boold, 900, 85);
		arg0.drawImage(ScoreIMG, 870, 48, 22, 20, this);
		arg0.drawImage(LifeIMG, 870, 68, 22, 20, this);
		
		arg0.drawString("关卡："+level, 50, 63);
		arg0.drawString("弹药："+amm, 50, 85);
		
		
		
		//爆炸
		if(exp!=null){
			exp.drawExp(arg0);//画爆炸对象
			
		}
		exp = null;
		
		if(lev!=null){
			lev.drawNext(arg0);
		}
		//到达第10关，通关
		if(level==10){
			arg0.setFont(new Font("宋体", Font.BOLD, 30));
			arg0.drawString("恭喜、您已通关！", 300, 100);
		}
		
		//结束界面1,炸弹用光
		if(amm<=0){
			shipOBJ.isLive=false;
			overY-=5;
			if(overY>350){
				arg0.drawImage(OverIMG, 300, overY, 400, 100, this);
				}else{
					arg0.drawImage(OverIMG, 300, 350, 400, 100, this);
					arg0.setColor(Color.yellow);
					//设置游戏得分
					arg0.setFont(new Font("宋体", Font.BOLD, 30));
					arg0.drawString("得分："+score, 420, 330);
					
				}
		}
		
		//结束界面2,生命值为0
		if(!shipOBJ.isLive){
			overY-=5;
		}
			if(overY>350){
			arg0.drawImage(OverIMG, 300, overY, 400, 100, this);
			}else{
				arg0.drawImage(OverIMG, 300, 350, 400, 100, this);
				arg0.setColor(Color.yellow);
				//设置游戏得分
				arg0.setFont(new Font("宋体", Font.BOLD, 30));
				arg0.drawString("得分："+score, 420, 330);
				
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
			tempIMG = this.createImage(1024, 628);//在缓冲区创建背景图片
			
		}
		
		Graphics newG = tempIMG.getGraphics();//拿到缓冲区的画笔
		
		arg0.drawImage(tempIMG, 0, 0, 1024, 628,this);
		
		paint(newG);
	}
	
	
	
	
	/*
	 * 改变时间频率来表示关卡
	 */
	
	int time=0;//时间频率变量
	int xx=0;//道具小心心时间频率变量
	int xxx=0;//道具小星星时间频率变量
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
					
					e.printStackTrace();
				}
				repaint();
				
				time++;
				xx++;
				xxx++;
				if(time%(51-hell)==0){
					//添加向左潜艇集合
					int ry=r.nextInt(300);
					int rx=r.nextInt(500);
					Submarine sub = new Submarine(2*rx,ry+310, 64, 20, GameMain.this, true);
					SubList.add(sub);		
					
					//添加向右潜艇
					int ry2=r.nextInt(300);
					int rx2=r.nextInt(500);
					Submarine2 sub2 = new Submarine2(2*rx2,ry2+310, 63, 19, GameMain.this, true);
					SubList2.add(sub2);
					
					
					time=0;
				}
				
				if(xx%400==0){
					//添加小心心
					int lx = r.nextInt(300);
					Life life = new Life(2*lx, 300, 22, 20, GameMain.this, true);
					LifeList.add(life);
					xx=0;
				}
				
				if(xxx%200==0){
					//添加小星星
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
		this.setTitle("潜艇大战");
		this.setResizable(false);//禁用最大化
		this.setSize(1024, 628);
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
				shipOBJ.moveShip(arg0);
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				shipOBJ.StopShip(arg0);
			}
		});
		
		
		
		
		
	}
	

	
	
	
	//public static void main(String[] args) {
		
	//	new GameMain().init();//调用初始化init方法
		

	//}
	
	
	
	
	
	
	
	
	
	

}
