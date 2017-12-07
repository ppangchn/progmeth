package graphic;



import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOver {
	public static Font GAME_FONT = new Font("Digital Tech",60);
	public static Font MAIN_FONT = new Font("Courier New",20);
	private static int frame = 0;
	public GameOver() {
	}
	public static void draw(GraphicsContext gc) {
		AnimationTimer gameoveranimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				Image background = new Image("gameover.jpg");
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, 800, 450);
				gc.drawImage(background, 0, 0);
				gc.setFill(Color.ORANGERED);
				gc.setFont(GAME_FONT);
				gc.fillText("GAME OVER\n\nputang ina mo", 400, 150);
				gc.setFill(Color.WHITE);
				gc.setFont(MAIN_FONT);
				gc.fillText("Press Enter to go to Main Menu", 380, 380);
			}
			
		};
		gameoveranimation.start();
	}
}
