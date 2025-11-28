package pushpushgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//소프트웨어학과 2021203023 문현우

public class AllClearScreen extends JPanel {
	

	 private MainFrame mainFrame;
	 public AllClearScreen(MainFrame mainFrame) {
	        this.mainFrame = mainFrame;
	        setLayout(null); // 수동 위치 지정

	        setPreferredSize(new Dimension(640, 640));
	        setBackground(Color.WHITE); // 배경 흰색

	        JLabel titleLabel = new JLabel("🎉 All Stages Cleared! 🎉", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
	        titleLabel.setBounds(70, 100, 500, 50); // 중앙정렬 효과
	        add(titleLabel);
	        
	        // 버튼 생성 → ButtonManager 이용
	        JButton btn1 = ButtonManager.homeButton(220, 320, 200, 50, mainFrame);
	        add(btn1);
	        JButton btn2 = ButtonManager.rankButton(220, 380, 200, 50, mainFrame);
	        add(btn2);
	        JButton btn3 = ButtonManager.exitButton(220, 440, 200, 50);
	        add(btn3);
	        
	 }
}
