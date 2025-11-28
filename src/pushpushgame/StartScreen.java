package pushpushgame;
import javax.swing.*;
import java.awt.*;

//소프트웨어학과 2021203023 문현우

public class StartScreen extends JPanel {
    public StartScreen(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Escape Game");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JButton btn1 = ButtonManager.userinfoButton(0, 40, 30, 100, mainFrame);
        JButton btn2 = ButtonManager.rankButton(0, 40, 30, 100, mainFrame);
        JButton btn3 = ButtonManager.exitButton(0, 40, 30, 100);

        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn2.setAlignmentX(CENTER_ALIGNMENT);
        btn3.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(btn1);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btn2);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btn3);
        add(Box.createVerticalGlue());
    }
}