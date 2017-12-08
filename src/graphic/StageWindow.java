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
	public String[] stagedialog = {"Y","o","u"," ","s","o"," ","n","o","o","b"," ","l","a","~"," ","C","a","t","c","h"," ","m","e"," ","i","f"," ","y","o","u"," ","c","a","n"," ",":","P"};
	public String[] boss = {"bossblack.png"};
	public String sentence ="";
	public static final Font font = new Font("Courier New",18);
	public static final Font enter = new Font("Courier New",12);
	private int count = 0;
	public int frame=0;
	public GraphicsContext gc;
	private boolean isVisible = true;
	public int time = 90;
	
	
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
					if(count <= stagedialog.length-1) sentence+=stagedialog[count];
					gc.fillText(sentence, 250, 380);
					if (count<=stagedialog.length-1) count++;
					gc.setFont(enter);
				    gc.fillText("Press Enter", 700, 420);
					frame++;
				
					
					
				}
				
			}
		};
		stageanimation.start();
	
	}
	
	public void StopAnimationTimer() {
		stageanimation.stop();
	}
}
