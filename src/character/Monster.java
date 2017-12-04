package character;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Monster implements IRenderable {
	private double x;
	private double y;
	private int way;
	public Random rand = new Random();
	public Image monsterpic;
	public int exp;
	public boolean isVisible = true;
	public Monster() {
		x = (double)rand.nextInt(800);
		//y = (double)rand.nextInt(450);
		y = 100;
		way = rand.nextInt(4)+1;
		System.out.println(way);
		if (way==1) {
			y = (double)rand.nextInt(200);
			x=0;
		}
		else if (way==2) {
			y = (double)rand.nextInt(200);
			x=800;
		}
		else if (way==3) {
			x = (double)rand.nextInt(800);
			y=0;
		}
		else if (way==4) {
			x = (double)rand.nextInt(800);
			y=450;
		}
		setImage();
	}

	@Override
	public void draw(GraphicsContext gc) {
		System.out.println("monster");
		gc.drawImage(monsterpic, x, y);
		// TODO Auto-generated method stub
		
	}
	public void setImage() {
		monsterpic = new Image("1424749313.png");
	}
	@Override
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean isDestroyed(double x,double y) {
		if ((this.x < x+20 && x-20 < this.x) && (this.y < y+20 && y-20 < this.y)) {
			isVisible = false;
			return true;
		}
		return false;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void updatePos() {
		if (way==1) x+=2;
		else if (way==2) x-=2;
		else if (way==3) y+=2;
		else if (way==4) y-=2;
	}
	
}
