package character;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public class Item implements IRenderable{
	double x,y;
	protected boolean isVisible = true;
	public void effect(Hero hero) {
		
	}
	public Item() {
		this.randomAddress();
	}
	public void randomAddress() {
		Random rand = new Random();
		x = rand.nextDouble()*640;
		y = rand.nextDouble()*480;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
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

}
