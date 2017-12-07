package graphic;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;

public class StageWindow {
	private AnimationTimer stageanimation;
	public String[] stagedialog = {"Y","O","U"," ","S","O"," ","N","O","O","B"," ","L","A",".","    ","C","A","T","C","H"," ","M","E"," ","I","F"," ","Y","O","U"," ","C","A","N","  !"};
	public String[] boss = {"boss.png"};
	public String sentence ="";
	public Font font = new Font("Digital Tech",18);
	private int count = 0;
	public int frame=0;
	public GraphicsContext gc;
	private boolean isVisible = true;
	public StageWindow(GraphicsContext gc) {
		this.gc = gc;
	}
	public void draw() {
		stageanimation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (isVisible) {
					Image dialog = new Image("DialogBox2.png");
					Image bossimage = new Image(boss[0]);
					gc.drawImage(bossimage, 30, 150);
					gc.drawImage(dialog, 190, 350);
					gc.setFill(Color.ALICEBLUE);
					gc.setFont(font);
					sentence+=stagedialog[count];
					gc.fillText(sentence, 250, 380);
					if (count<stagedialog.length-1) count++;
					frame++;
					
				}
			}
		};
		stageanimation.start();
	
	}
}
