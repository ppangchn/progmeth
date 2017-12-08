package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Bullet implements IRenderable {
	private double x;
	private double y;
	private char direction;
	private boolean isVisible = true;
	public Image bulletpic;
	
	public Bullet(double x,double y,char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		setBullet();
	}
	public void setBullet() {
		bulletpic = new Image("bullet.png");
	}
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bulletpic, x, y);
		//System.out.println("BOMP");
	}


	public void setIsvisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public void updatePos() {
		// TODO Auto-generated method stub
			if (direction=='a') x-=10;
			if (direction=='d') x+=10;
			if (direction=='w') y-=10;
			if (direction=='s') y+=10;
			if(x>800 || x <0 || y>450 || y<0) isVisible = false;
			if( direction=='r') {
				x+=10;
				y+=10;
			}
			if(direction=='t') {
				x+=10;
				y-=10;
			}
			if(direction=='y') {
				x-=10;
				y-=10;
			}
			if(direction=='u') {
				x-=10;
				y+=10;
			}
		
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}
	

}
