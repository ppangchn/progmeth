package character;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Boss extends Hero implements IRenderable {
	private List<Image> front = new ArrayList<>();
	public Image bosspic;
	private double x = 400;
	private double y = 100;
	private int time = 0;
	private int count = 0;
	private boolean isVisible = true;
	public Boss() {
		super.setX(x);
		super.setY(y);
		super.setBoss(true);
		for (int i=1; i<5; i++) {
			front.add(new Image("bossfront"+i+".png",40,58,false,false));
		}
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(GraphicsContext gc) {
		time++;
		if(time==15) {
			attack('s');
			attack('r');
			attack('u');
			count++;
			time = 0; 
		}
		gc.drawImage(front.get(count), 400, 100);
		if (count>=3) count = 0;
		
	}
	@Override
	public boolean isVisible() {
		return isVisible;
	}
	
}
                 