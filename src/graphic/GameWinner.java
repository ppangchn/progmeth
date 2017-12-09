package graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameWinner {
	public static Image winimage = new Image("win.jpg",800,450,false,false);
	public static String[] quote = {"G","A","M","E","","C","L","E","A","R"};
	public static String text ="";
	public static int i =0;
	public static int frame = 0;
	
	public static void draw(GraphicsContext gc)  {
		gc.drawImage(winimage, 0, 0);
		gc.setFill(Color.GOLD);
		gc.fillText(text+quote[i], 400, 200);
		gc.setStroke(Color.DARKGOLDENROD);
		gc.strokeText(text+quote[i], 400, 200);
		if (frame==50) {
			i++;    
		}
		frame++;
	}
}
