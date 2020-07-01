package code;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Thelove {
	
	public static void isOne(JButton jbt) {
		String i = jbt.getActionCommand();
		if(jbt.getIcon().toString()=="./src/images/72.jpg") {
			jbt.setIcon(new ImageIcon("./src/images/preview_"+i+".gif"));//i从0开始，故第一张小图缺失
		}else {
			jbt.setIcon(new ImageIcon("./src/images/72.jpg"));
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameSound gs = new GameSound();
		gs.playBgSound("music/zt.mp3");
		
		JFrame jf = new JFrame();
		JPanel up = new JPanel();//上面版
		JPanel down = new JPanel();//下面板
		JButton btnArray[] = new JButton[25];
		JButton reset = new JButton("重置");
		JButton jj = new JButton("嗯哼");
		
		reset.addActionListener(new ActionListener() {//重置按钮
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<btnArray.length;i++) {
					btnArray[i].setIcon(new ImageIcon("./src/images/72.jpg"));
				}
			}
		});
		
		
		
		jf.setTitle("The Beaut");
		jf.setResizable(false);//最大化
		jf.setSize(800,800);
		jf.setLocationRelativeTo(null);//居中
		up.setLayout(new GridLayout(5,5));//网格布局
		
		for(int i=0;i<25;i++) {
			JButton btn = new JButton(new ImageIcon("./src/images/72.jpg"));
			btnArray[i] = btn;
			btnArray[i].setActionCommand(String.valueOf(i));//下标
			/*添加事件*/
			btnArray[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int num = Integer.valueOf(e.getActionCommand());//得到下标
					System.out.println(num);
					
					if(num==0) {
						isOne(btnArray[num]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num+5]);
					}else if(num==4) {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num+5]);
					}else if(num==20) {
						isOne(btnArray[num]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num-5]);
					}else if(num==24) {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num-5]);
					}else if(num>0&&num<4) {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num+5]);
					}else if(num>20&&num<24) {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num-5]);
					}else if(num%5==0) {
						isOne(btnArray[num]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num+5]);
						isOne(btnArray[num-5]);
					}else if(num%5==4) {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num+5]);
						isOne(btnArray[num-5]);
					}else {
						isOne(btnArray[num]);
						isOne(btnArray[num-1]);
						isOne(btnArray[num+1]);
						isOne(btnArray[num+5]);
						isOne(btnArray[num-5]);
					}}
			});
			
			up.add(btnArray[i]);
		}
		
		//判断所有格不等于第一张图片，然后弹出提示框
		
		
		int sum=0;
		
		for (int j = 0; j < 25; j++) {
			if(btnArray[j].getIcon().toString()!="./src/images/72.jpg") {
				++sum;
			}else {
				sum=0;
				break;
			}
				
		}
		
		if(sum==25) {
			//弹出提示框
			/*int res = JOptionPane.showConfirmDialog(null, "是否继续操作", "是否继续", JOptionPane.YES_NO_OPTION);*/
			JOptionPane.showConfirmDialog(null, "么么哒", "么么哒", JOptionPane.YES_NO_OPTION);
		}
		sum=0;//还原统计变量
		down.setLayout(new FlowLayout());//流式面板
		down.add(reset);
		down.add(jj);
		jf.setLayout(new BorderLayout());//边格
		jf.add(up,"Center");
		jf.add(down,"South");
		
			
		jf.add(up);
		//jf.add(down);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束jvm
		jf.setVisible(true);
		
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
			// f.setVisible(false);
			System.exit(0);
			}
			});

	}

}
