package graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameWinner {
	public static Image winimage = new Image("win.jpg",800,450,false,false);
	public static final Font CLEAR_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("Digital_tech.otf"),90);
	public static final Font MAIN_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("Digital_tech.otf"),30);
	public static String[] quote = {"G","A","M","E"," ","C","L","E","A","R"};
	public static boolean isFinished = false;

	
	public static void draw(GraphicsContext gc)  {
		Thread thread = new Thread() {
			public void run() {
				gc.drawImage(winimage, 0, 0);
				gc.setFill(Color.GOLD);
				gc.setFont(CLEAR_FONT);
				
				gc.setStroke(Color.DARKGOLDENROD);
				gc.setLineWidth(3);
				String text ="";
				for(int k=0;k<=9;k++) {
					text+=quote[k];
					System.out.println(""+text+" "+k+" "+quote[k]);
					gc.strokeText(text, 180, 200);
					gc.fillText(text, 180, 200);
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				gc.setFont(MAIN_FONT);
				gc.fillText("PRESS ENTER TO GO TO MAIN MENU", 195, 400);
				isFinished = true;
			}
			
			
		};
		thread.setDaemon(true);
		thread.start();
		
		
	}
	public static void startAnimation(GraphicsContext gc) {
	draw(gc);
}
	public static boolean isFinished() {
		return isFinished;
	}

}
