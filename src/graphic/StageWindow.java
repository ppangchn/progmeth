package graphic;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StageWindow {
	private AnimationTimer a;
	private GraphicsContext gc;
	public String[] stageimage;
	public StageWindow(GraphicsContext gc) {
		this.gc = gc;
	}
	public void draw() {
		gc.setFill(Color.RED);
		gc.fillText("STAGE 1", 0, 200);
		a = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gc.fillText("STAGE 123123", 0, 500);
//				gc.tex
//				if (text.getX()==400) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
					
			
			
		};
		a.start();
//		if (text.getX()>800) a.stop();
	}
}
