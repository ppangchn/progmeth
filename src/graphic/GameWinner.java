package graphic;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameWinner {
	private static AnimationTimer winanimation;
	private static int i =0;
	private static int frame = 0;
	public static Image winimage = new Image("win.jpg",800,450,false,false);
	public static final Font CLEAR_FONT = new Font("Digital tech",90);
	public static final Font MAIN_FONT = new Font("Digital tech",30);
	public static String[] quote = {"G","A","M","E"," ","C","L","E","A","R"};
	public static String text ="";
	
	public static void draw(GraphicsContext gc)  {
		Thread thread = new Thread() {
			public void run() {
				gc.drawImage(winimage, 0, 0);
				gc.setFill(Color.GOLD);
				gc.setFont(CLEAR_FONT);
				gc.fillText(text, 190, 200);
				gc.setStroke(Color.DARKGOLDENROD);
				gc.setLineWidth(3);
				gc.strokeText(text, 190, 200);
				if (i==quote.length) winanimation.stop();
				if (frame==20) {
					text+=quote[i];
					if (i<quote.length) i++;
					frame=0;
				}
				
				frame++;
			}
			
		};
		thread.setDaemon(true);
		thread.start();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				winanimation = new AnimationTimer() {

					@Override
					public void handle(long now) {
						// TODO Auto-generated method stub
						gc.setFont(MAIN_FONT);
						gc.fillText("PRESS ENTER TO GO TO MAIN MENU", 170, 400);
					}
					
				};
			}
			
		});
		winanimation.start();
		
	}

}
