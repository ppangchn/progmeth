package graphic;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StageWindow3 {
	private AnimationTimer stageanimation ;
	public String[] stagedialog = {"T","I","M","E","  ","T","O","  ","H","A","V","E","  ","F","U","N ."};
	public String[] boss = {"boss.png"};
	public String sentence ="";
	public Font font = new Font("Digital Tech",18);
	private int count = 0;
	public int frame=0;
	public GraphicsContext gc;
	private boolean isVisible = true;
	public int time = 90;
	
	public StageWindow3(GraphicsContext gc) {
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
