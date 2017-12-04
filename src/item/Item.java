package item;

import java.util.Random;

import sharedObject.IRenderable;

public abstract class Item implements IRenderable{
	private double x;
	private double y;
	private int count = 0;
	private boolean isAppeared = false;
	private Random rand;
	public Item() {
		x = rand.nextDouble()*800;
		y = rand.nextDouble()*450;
	}
	public void random() {
		x = rand.nextDouble()*800;
		y = rand.nextDouble()*450;
	}
	
}
