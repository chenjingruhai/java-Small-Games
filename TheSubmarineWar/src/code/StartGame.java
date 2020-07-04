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

	
	Toolkit tool = Toolkit.getDefaultToolkit();//实例化一个对象
	//用工具对象拿到图片对象
	Image startIMG = tool.getImage(GameMain.class.getResource("/images/start.png"));//加载背景图片
	
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(startIMG, 0, 0, 641, 429, this);
		arg0.setColor(Color.red);
		arg0.setFont(new Font("宋体", Font.BOLD, 20));
		arg0.drawString("按下Enter键进入游戏", 228, 385);
		arg0.setColor(Color.yellow);
		arg0.drawString("战斗到最后一刻!", 245, 363);
		
		arg0.setColor(Color.black);
		arg0.drawString("A控制左移，D控制右移，按下空格投掷炸弹", 128, 140);
		
	}
	
	GameSound sound = new GameSound();//实例化声音对象
	
	
	
	public void init() {
		sound.playBgSound("music/start.mp3");
		this.setTitle("潜艇大战");
		
		
		this.setResizable(false);//禁用最大化
		this.setSize(641, 429);
		this.setLocationRelativeTo(null);//居中
		this.setVisible(true);
		
		
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
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER){
					StartGame.this.setVisible(false);//开始界面消失
					new GameMain().init();//显示游戏主界面
					sound.stopSound();//停止并销毁声音对象
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
