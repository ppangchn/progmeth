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
	public Block() {
//		x = rand.nextDouble()*800;
//		y = rand.nextDouble()*450;
	}
	public void Random() {
		x = rand.nextDouble()*800;
		y =rand.nextDouble()*450;
	}
	public void setBlockImage() {

		blockimage = new Image("block.png");
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(blockimage, x, y);
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
