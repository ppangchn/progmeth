package graphic;



import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOver {
	public static final Font GAMEOVER_FONT = new Font("Digital Tech",80);
	public static final Font CONTENT_FONT = new Font("Digital Tech",40);
	public static final Font MAIN_FONT = new Font("Courier New",20);
//	protected static final boolean isAnimationRunning = false;
	public static Image gameover;
	public static AudioClip gameoversound = new AudioClip(ClassLoader.getSystemResource("GameOver.wav").toString());
	private static int framebg = 0;
	private static int framepress =0;
	private static int count;
	private static AnimationTimer gameoveranimation;
	public GameOver() {
		
	}
	public static void draw(GraphicsContext gc) {
		setImage();
		count =0;
		playSong();
		Thread t = new Thread(new Runnable() {
			public void run() {
//				do{
					updateAnimation(gc);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					}
//				while(isAnimationRunning!=false);
				}
		});	
		t.setDaemon(true);
		t.start();
		gameoveranimation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (count!=3) {
					if (framebg==50 && count!=3) {
						gc.setFill(Color.BLACK);
						gc.fillRect(0, 0, 800, 450);
						gc.drawImage(gameover, 0, 0);
					}
					if (framepress==110 && count!=3) {
						gc.setFill(Color.ORANGERED);
						gc.setFont(GAMEOVER_FONT);
						gc.fillText("GAME OVER", 370, 150);
						gc.setFont(CONTENT_FONT);
						gc.fillText("putang ina mo", 420, 250);
						gc.setFill(Color.WHITE);
						gc.setFont(MAIN_FONT);
						gc.fillText("Press Enter to go to Main Menu", 370, 380);
						framebg=0;
						framepress=0;
						count++;
					}
					framebg++;
					framepress++;
				}
				else {
					gc.setFill(Color.BLACK);
					gc.fillRect(0, 0, 800, 450);
					gc.drawImage(gameover, 0, 0);
					gc.setFill(Color.ORANGERED);
					gc.setFont(GAMEOVER_FONT);
					gc.fillText("GAME OVER", 370, 150);
					gc.setFont(CONTENT_FONT);
					gc.fillText("putang ina mo", 420, 250);
					gc.setFill(Color.WHITE);
					gc.setFont(MAIN_FONT);
					gc.fillText("Press Enter to go to Main Menu", 370, 380);
				}
				
			}
			
		};
		gameoveranimation.start();
	}
	protected static void updateAnimation(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFont(GAMEOVER_FONT);
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2 );
		gc.fillText("GAME OVER", 220, 250);
		gc.strokeText("GAME OVER", 220, 250);
		
	}
	public static void setImage() {
		gameover = new Image("gameover.jpg");
		
	}
	public static void playSong() {
		gameoversound.play();
	}
	public static void stopAnimationTimer() {
		gameoveranimation.stop();
	}
	
}
