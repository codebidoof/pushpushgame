package pushpushgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//소프트웨어학과 2021203023 문현우

public class ButtonManager {
	//시작 버튼
	public static JButton startButton(int x, int y, int width, int height, MainFrame mainFrame) {
        JButton button = new JButton("▶ 게임 시작");
        button.setFont(new Font("Dialog", Font.PLAIN, 20));
        button.setBounds(x, y, width, height);
        button.addActionListener(action-> {
        	mainFrame.showScreen("stage1");
        	
        });
        return button;
    }
	
	//시작 버튼
		public static JButton userinfoButton(int x, int y, int width, int height, MainFrame mainFrame) {
	        JButton button = new JButton("게임 시작");
	        button.setFont(new Font("Dialog", Font.PLAIN, 20));
	        button.setBounds(x, y, width, height);
	        button.addActionListener(action-> {
	        	mainFrame.showScreen("user");
	        });
	        return button;
	    }
	
	//종료 버튼
	public static JButton exitButton(int x, int y, int width, int height) {
        JButton button = new JButton("게임 종료");
        button.setFont(new Font("Dialog", Font.PLAIN, 20));
        button.setBounds(x, y, width, height);
        button.addActionListener(action-> {
        	System.exit(0);
        });
        return button;
    }
	
	//랭킹 화면 버튼
	public static JButton rankButton(int x, int y, int width, int height, MainFrame mainFrame) {
        JButton button = new JButton("랭킹 보기");
        button.setFont(new Font("Dialog", Font.PLAIN, 20));
        button.setBounds(x, y, width, height);
        button.addActionListener(action-> {
        	//추후 구현 예정
        });
        return button;
    }
	
	// 홈화면 버튼
	public static JButton homeButton(int x, int y, int width, int height, MainFrame mainFrame) {
        JButton button = new JButton("🏠");
        button.setFont(new Font("Dialog", Font.PLAIN, 20));
        button.setBounds(x, y, width, height);
        button.addActionListener(action-> {
        	mainFrame.resetStages();
        	mainFrame.showScreen("start");
        });
        return button;
    }
	
	// 리셋 버튼
	public static JButton resetButton(int x, int y, int width, int height, MainFrame mainFrame) {
	    JButton button = new JButton("리셋");
	    button.setFont(new Font("Dialog", Font.PLAIN, 15));
	    button.setBounds(x, y, width, height);
	    button.addActionListener(action -> {
	    	mainFrame.resetCurrentStage();
	    });
	    return button;
	}
	
	
	
	
	
}
