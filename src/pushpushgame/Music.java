package pushpushgame;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

//소프트웨어학과 2021203023 문현우

public class Music {
	 private static Music instance;

	 private Music() {
	 }

	 public static Music getInstance() {
	     if (instance == null) {
	         instance = new Music();
	     }
	     return instance;
	 }

	 // 효과음 재생 (한 번만 재생)
	 public void playEffect(String fileName) {
	     new Thread(() -> {
	         try {
	             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
	             Player player = new Player(bis);
	             player.play();  // 다 재생될 때까지 블로킹됨 (그래서 스레드로 실행)
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }).start();
	 }

}
