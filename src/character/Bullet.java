package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Bullet implements IRenderable {
	private double x;
	private double y;
	public Image bulletpic;
	public char c;
	public boolean isVisible = true;
	
	
	public Bullet(double x,double y,char c) {
		this.x = x;
		this.y = y;
		this.c = c;
		setBullet();
	}
//	public void Shooting() {
//		draw
//		
//	}
	public void setBullet() {
		
		String image_path = "bullet.png";
		Image bullet = new Image(image_path);
		bulletpic = bullet;	
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
			if (c=='a') x-=10;
			if (c=='d') x+=10;
			if (c=='w') y-=10;
			if (c=='s') y+=10;
			if(x>800 || x <0 || y>450 || y<0) isVisible = false;
		
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}
	

}
