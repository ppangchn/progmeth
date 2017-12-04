package character;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Block implements IRenderable{
	private double x;
	private double y;
	private Random rand;
	private int num;
	public Image blockimage;
	public String[] blockURL = {"block1.png","block2.png","block3.png","block4.png","block5.png","block6.png","block7.png","block8.png","block9.png","block10.png","block11.png","block12.png","block13.png","block14.png","block15.png","block16.png","block17.png"};
	public Block() {
		x = rand.nextDouble()*800;
		y = rand.nextDouble()*450;
	}
	public void Random() {
		x = rand.nextDouble()*800;
		y =rand.nextDouble()*450;
	}
	public void setBlockImage() {
		blockimage = new Image(blockURL[0]);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (String x : blockURL) {
			blockimage = new Image(x);
		}
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
