package pushpushgame;
import javax.swing.*;
import java.awt.*;
//소프트웨어학과 문현우
public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout); //CardLayout을 적용

    public MainFrame() {
        setTitle("Escape game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 640);
        setLocationRelativeTo(null);  // 화면 가운데 띄우기

        mainPanel.add(new StartScreen(this), "start");
        mainPanel.add(new UserInfo(this), "user");
        mainPanel.add(new Stage1(this), "stage1");
        mainPanel.add(new Stage2(this), "stage2");
        mainPanel.add(new Stage3(this), "stage3");
        mainPanel.add(new AllClearScreen(this), "allclear");

        add(mainPanel);
        setVisible(true);
    }
    
    private String currentScreen = "start"; // 초기값은 시작 화면, 현재 화면 이름 저장
    public String getCurrentScreen() {
        return currentScreen;
    }

    public Component showScreen(String name) {
        cardLayout.show(mainPanel, name);
        currentScreen = name;
        Component comp;
        // 화면 이름에 따라 해당 컴포넌트에 포커스 요청
        switch(name) {
            case "start":
        	    comp = mainPanel.getComponent(0);
        	    comp.requestFocusInWindow();
        	    break;
            case "user":
            	comp = mainPanel.getComponent(1);
            	comp.requestFocusInWindow();
            	break;
            case "stage1":
                comp = mainPanel.getComponent(2);
                comp.requestFocusInWindow();
                break;
            case "stage2":
                comp = mainPanel.getComponent(3);
                comp.requestFocusInWindow();
                break;
            case "stage3":
                comp = mainPanel.getComponent(4);
                comp.requestFocusInWindow();
                break;
            case "allclear":
                comp = mainPanel.getComponent(5);
                comp.requestFocusInWindow();
                break;
            default:
                comp = mainPanel;
                break;
        } 
        return comp;
    }
    
    public void resetStages() {
    	mainPanel.remove(mainPanel.getComponent(2)); // stage1
        mainPanel.remove(mainPanel.getComponent(2)); // stage2
        mainPanel.remove(mainPanel.getComponent(2)); // stage3

        // 새로운 스테이지 컴포넌트 생성 및 추가
        Stage1 stage1 = new Stage1(this);
        Stage2 stage2 = new Stage2(this);
        Stage3 stage3 = new Stage3(this);

        mainPanel.add(stage1, "stage1");
        mainPanel.add(stage2, "stage2");
        mainPanel.add(stage3, "stage3");

        mainPanel.revalidate();
        mainPanel.repaint(); 
    }
    
    public void resetCurrentStage() {
        String current = getCurrentScreen();

        Component newStage = switch (current) {
            case "stage1" -> new Stage1(this);
            case "stage2" -> new Stage2(this);
            case "stage3" -> new Stage3(this);
            default -> null;
        };

        if (newStage != null) {
            mainPanel.add(newStage, current); // 기존 이름으로 덮어쓰기
            cardLayout.show(mainPanel, current); // CardLayout은 이름 중복되면 마지막 것을 보여줌
            currentScreen = current;
            newStage.requestFocus();
        }
    }
    
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new MainFrame());
//    }
//}