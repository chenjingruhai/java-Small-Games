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

	
	
	Toolkit tool = Toolkit.getDefaultToolkit();//实例化一个对象
	//用工具对象拿到图片对象
	Image startIMG = tool.getImage(GameMain.class.getResource("/images/strart.jpg"));
	
	
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(startIMG, 0, 0, 353, 466, this);
	}
	
	GameSound sound = new GameSound();//实例化声音对象
	
	
	
	
	/*
	 * 初始化游戏
	 */
	public void init() {
		sound.playBgSound("music/zengjia.mp3");
		this.setTitle("雷霆战机");
		
		
		this.setResizable(false);//禁用最大化
		this.setSize(353, 466);
		this.setLocationRelativeTo(null);//居中
		this.setVisible(true);
		
		
		//给窗口的关闭按钮赋予功能
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		
		//鼠标事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x=arg0.getX();
				int y=arg0.getY();
				//判断鼠标点在开始按钮区域
				if(x>116&&x<235&&y>190&&y<206){
					StartGame.this.setVisible(false);//开始界面消失
					new GameMain().init();//显示游戏主界面
					sound.stopSound();//停止并销毁声音对象
					
				}
			}
		});
		
	}
	
	
	public static void main(String[] args) {
		new StartGame().init();

	}

}
