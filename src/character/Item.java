package character;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public class Item implements IRenderable{
	double x,y;
	protected boolean isVisible = true;
	private int frame = 0;
	public int tick=200;
	
	public Item() {
		this.randomAddress();

	}
	public void randomAddress() {
		Random rand = new Random();
		x = rand.nextInt(677)+35;
		y = rand.nextInt(311)+60;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		frame++;
		if (frame==5 && isVisible) {
			isVisible = false;
			frame = 0;
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.isVisible;
	}
	public double getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	public double getY() {
		return y;
	}
	public void effect(Hero hero) {
		
	}
	public void update() {
		tick--;
		if(tick <= 0) isVisible = false; 
	}


}
