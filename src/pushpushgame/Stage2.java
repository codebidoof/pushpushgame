package pushpushgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//소프트웨어학과 2021203023 문현우

public class Stage2 extends JPanel {
	
	private MainFrame mainFrame;
	private Image[] _imgs;
	private Image _playerImg;
	private Image _fireImg;
	private Image _firingImg; 
	private Image _doorImg; 
    private int _rowCount = 8;
    private int _colCount = 9;
    private boolean isDoorOpen = false;
    private int doorRow = 2, doorCol = 4; // 문 위치
    private int[][] _mapData = { // 0:잉여 및 길, 1:징애물, 2:횃대 Stage2의 맵 크기는 8X9로 설정
        {0, 1, 1, 1, 1, 0, 0 ,0, 0},
        {0, 1, 0, 0, 1, 1, 1, 1, 1},
        {0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 0, 1, 0, 0, 0 ,0, 1},
        {1, 0, 0, 0, 0, 2, 1, 1, 1},
        {1, 0, 0, 1, 2, 2, 1, 0, 0},
        {1, 1, 0, 0, 0, 1, 1 ,0, 0},
        {0, 1, 1, 1, 1, 1, 0, 0, 0}
    };
    //플레이어 초기 위치
    private int playerRow = 6;
    private final int firstplayerRow = playerRow;
    private int playerCol = 2;
    private final int firstplayerCol = playerCol;
    
    //공 초기 위치
    private int fire1Row = 2, fire1Col = 3;
    private final int firstfire1Row = fire1Row, firstfire1Col = fire1Col;
    private int fire2Row = 2, fire2Col = 5;
    private final int firstfire2Row = fire2Row, firstfire2Col = fire2Col;
    private int fire3Row = 5, fire3Col = 2;
    private final int firstfire3Row = fire3Row, firstfire3Col = fire3Col;

    public Stage2(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(640, 640));
        loadImages();
        setFocusable(true);
        setLayout(null); // 절대 위치 지정이 가능하게 설정
        
        // 버튼 추가 (오른쪽 상단에 배치)
        JButton btn1 = ButtonManager.homeButton(550, 10, 70, 30, mainFrame);
        this.add(btn1);
        JButton btn2 = ButtonManager.resetButton(550, 50, 70, 30, mainFrame);
        this.add(btn2);

        // 키 이벤트 처리
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int nextRow = playerRow;
                int nextCol = playerCol;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        nextRow--;
                        break;
                    case KeyEvent.VK_DOWN:
                        nextRow++;
                        break;
                    case KeyEvent.VK_LEFT:
                        nextCol--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        nextCol++;
                        break;
                }

                if (isfireAt(nextRow, nextCol)) {
                    int fireNextRow = nextRow + (nextRow - playerRow);
                    int fireNextCol = nextCol + (nextCol - playerCol);

                    if (fireNextRow >= 0 && fireNextRow < _rowCount &&
                        fireNextCol >= 0 && fireNextCol < _colCount &&
                        _mapData[fireNextRow][fireNextCol] != 1 &&
                        !isfireAt(fireNextRow, fireNextCol)) {

                        movefire(nextRow, nextCol, fireNextRow, fireNextCol);
                        playerRow = nextRow;
                        playerCol = nextCol;
                    }
                } else if (_mapData[nextRow][nextCol] != 1) {
                    playerRow = nextRow;
                    playerCol = nextCol;
                }
                
                // 클리어 여부 체크
                if (isClear()) {
                	    isDoorOpen = true; // 문이 보임
                    }
                
                // 문이 보이고, 플레이어가 문 위에 있으면 다음 스테이지
                if (isDoorOpen && playerRow == doorRow && playerCol == doorCol) {
                	Music.getInstance().playEffect("music/door.mp3"); 
                    mainFrame.showScreen("stage3");
                }

                repaint();
            }
        });
    }
    
    // 클리어 조건: 횃불이 모두 횃대(2) 위치에 있으면 클리어
    private boolean isClear() {
        return _mapData[fire1Row][fire1Col] == 2 &&
               _mapData[fire2Row][fire2Col] == 2 &&
               _mapData[fire3Row][fire3Col] == 2; 
    }
    
    // 횃불의 위치
    private boolean isfireAt(int row, int col) {
        return (row == fire1Row && col == fire1Col) ||
               (row == fire2Row && col == fire2Col) ||
               (row == fire3Row && col == fire3Col);   
    }
    
    private void movefire(int fromRow, int fromCol, int toRow, int toCol) {
    	boolean wasOnPerch = (_mapData[fromRow][fromCol] == 2);
        boolean willBeOnPerch = (_mapData[toRow][toCol] == 2);
        
        if (fromRow == fire1Row && fromCol == fire1Col) {
            fire1Row = toRow; fire1Col = toCol;
        } else if (fromRow == fire2Row && fromCol == fire2Col) {
            fire2Row = toRow; fire2Col = toCol;
        } else if (fromRow == fire3Row && fromCol == fire3Col) {
            fire3Row = toRow; fire3Col = toCol;
        } 
        
        // 횃불이 새로 횃대에 올라갔을 때만 효과음 재생
        if (!wasOnPerch && willBeOnPerch) {
            Music.getInstance().playEffect("music/torch.mp3");
        }
    }

    private void loadImages() {
        _imgs = new Image[3];
        for (int i = 0; i < _imgs.length; ++i) {
            ImageIcon icon = new ImageIcon("images/image" + i + ".jpg");
            _imgs[i] = icon.getImage();
        }
        _playerImg = new ImageIcon("images/player.jpg").getImage(); // 플레이어 이미지
        _fireImg = new ImageIcon("images/fire.jpg").getImage(); //공 이미지
        _firingImg = new ImageIcon("images/firing.jpg").getImage(); // 불 이미지 
        _doorImg = new ImageIcon("images/door.jpg").getImage(); //문 이미지
    }

    private void drawCell(Graphics g, int row, int col) {
        int cellW = getWidth() / _colCount;
        int cellH = getHeight() / _rowCount;
        int idx = _mapData[row][col];
        int x = cellW * col;
        int y = cellH * row;
        g.drawImage(_imgs[idx], x, y, cellW, cellH, this);
        
        // 횃불 이미지 맵 위에그리기
        if ((row == fire1Row && col == fire1Col) ||
        	(row == fire2Row && col == fire2Col) ||
        	(row == fire3Row && col == fire3Col)) {
        	    g.drawImage(_fireImg, x, y, cellW, cellH, this);
        }
        
        // 횃대 위에 횃불이 올라가 있으면 불 이미지 출력
        if (_mapData[row][col] == 2 && isfireAt(row, col)) {
            g.drawImage(_firingImg, x, y, cellW, cellH, this);
            g.setColor(new Color(255, 100, 100, 150)); // 반투명 붉은색
        	g.fillRect(x, y, cellW, cellH);
        }
        
        // 플레이어 이미지 맵 위에 그리기
        if (row == playerRow && col == playerCol) {
            g.drawImage(_playerImg, x, y, cellW, cellH, this);
        }
        
        // 클리어 조건 만족시 문 이미지 그림
        if (isDoorOpen && row == doorRow && col == doorCol) {
            g.drawImage(_doorImg, x, y, cellW, cellH, this);
        }
    }
    
    private int countFiresOnPerch() {
        int count = 0;
        if (_mapData[fire1Row][fire1Col] == 2) count++;
        if (_mapData[fire2Row][fire2Col] == 2) count++;
        if (_mapData[fire3Row][fire3Col] == 2) count++;
        return count;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < _rowCount; ++row) {
            for (int col = 0; col < _colCount; ++col) 
                drawCell(g, row, col);
        }
        // 좌상단에 텍스트 출력
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Stage 2", 10, 25); 
        
        // 횃불이 횃대 위에 올라간 개수 세기
        int firesOnPerch = countFiresOnPerch();

        // 불 켜진 횃대 개수에 따라 어두운 정도 다르게
        int alpha;
        switch(firesOnPerch) {
            case 0: alpha = 150; break;  // 완전 어둡게
            case 1: alpha = 100; break;  // 약간 덜 어둡게
            case 2: alpha = 50; break;  // 좀 더 밝게
            case 3: alpha = 0; break;   // 다 켜짐
            default: alpha = 150; break;
        }

        if (alpha > 0) {
            Color dark = new Color(0, 0, 0, alpha);
            g.setColor(dark);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
