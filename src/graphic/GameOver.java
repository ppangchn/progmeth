package graphic;



import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOver {
	public static final Font GAMEOVER_FONT = new Font("Digital Tech",80);
	public static final Font CONTENT_FONT = new Font("Digital Tech",40);
	public static final Font MAIN_FONT = new Font("Courier New",20);
	public static Image gameover;
	private static int framebg = 0;
	private static int framepress =0;
	private static int count;
	public GameOver() {
		
	}
	public static void draw(GraphicsContext gc) {
		setImage();
		count =0;
		AnimationTimer gameoveranimation = new AnimationTimer() {

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
	public static void setImage() {
		gameover = new Image("gameover.jpg");
		
	}
}
