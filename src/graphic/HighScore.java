package graphic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HighScore {
	private static List<String> data = new ArrayList<>();
	public static final Font font = new Font("Digital tech",50);
	private static AnimationTimer entername;
	private String name ="";
	public HighScore(Stage primarystage) {
	}
	public static void draw(GraphicsContext gc,String name) {
		
		Image bgname = new Image("highscore.jpg");
		gc.drawImage(bgname, 0, 0);
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(font);
		gc.fillText("ENTER YOUR NAME", 225, 70);
		entername = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				updateAnimation(gc,name);
			}
			
		};
		entername.start();
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				do{
//					updateAnimation(gc,name);
//					
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					}
//				while(true);
//				}
//	});	
//	t.setDaemon(true);
//	t.start();
		
	}
	protected static void updateAnimation(GraphicsContext gc,String name) {
		// TODO Auto-generated method stub
		gc.fillText(name, 250, 200);
		
	}
	public static void drawTable(GraphicsContext gc) {
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				do{
//					updateAnimation();
//					try {
//						Thread.sleep(16);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					}while(isAnimationRunning!=false);
//				}
//	});	
//	t.setDaemon(true);
//	t.start();
		Image bghighscore = new Image("highscorebg.jpg");
		gc.drawImage(bghighscore, 0, 0);
		int j = data.size();
		if (data.size()>10) j=10;
		for (int i =0; i<j; i++) {
			gc.setFill(Color.ALICEBLUE);
			gc.setFont(font);
			gc.fillText(data.get(i), 250, 200);
			
		}
	}
	public static void addData(int score,String name) {
		data.add(name+" "+score);
	}
}
