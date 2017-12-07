package graphic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScore {
	private static List<String> data = new ArrayList<>();
	public static final Font font = new Font("Digital tech",50);
	public HighScore() {
		
	}
	public static void draw(GraphicsContext gc,String name) {
		Image bgname = new Image("highscore.jpg");
		gc.drawImage(bgname, 0, 0);
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(font);
		gc.fillText("ENTER YOUR NAME", 225, 70);
		gc.fillText(name, 250, 200);
		
	}
	public static void drawTable(GraphicsContext gc) {
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
