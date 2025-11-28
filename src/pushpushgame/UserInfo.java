package pushpushgame;
import javax.swing.*;
import java.awt.*;

//소프트웨어학과 2021203023 문현우

public class UserInfo extends JPanel{
	public UserInfo(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        JLabel info = new JLabel("닉네임을 입력하세요");
        info.setFont(new Font("Dialog", Font.BOLD, 30));
        info.setForeground(Color.WHITE);
        info.setAlignmentX(CENTER_ALIGNMENT);
        
        JTextField infoinput = new JTextField();
        infoinput.setMaximumSize(new Dimension(300, 40)); // 입력창 크기 제한                                                                                                                                         
        infoinput.setAlignmentX(CENTER_ALIGNMENT);
        infoinput.setFont(new Font("Dialog", Font.PLAIN, 18));
        infoinput.setText("");
        
        JButton btn1 = ButtonManager.startButton(0, 40, 30, 100, mainFrame);
        btn1.setAlignmentX(CENTER_ALIGNMENT);
   
        add(Box.createVerticalGlue());
        add(info);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(infoinput);
        add(Box.createRigidArea(new Dimension(0, 20))); // 버튼 위 간격
        add(btn1);
        add(Box.createVerticalGlue());
    }

}
