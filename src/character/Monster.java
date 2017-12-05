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
	public Image monsterUp = new Image("monsterup.png");
	public Image monsterDown = new Image("monsterdown.png");
	public Image monsterDie = new Image("monsterDie.png");
	public Image monsterPic;
	public int exp;
	public boolean isVisible = true;
	public Hero hero;
	public int tick;
	
	public Monster(Hero hero) {
		x = (double)rand.nextInt(800);
		//y = (double)rand.nextInt(450);
		y = 100;
		way = rand.nextInt(4)+1;
		System.out.println(way);
		if (way==1) {
			y = (double)rand.nextInt(200);
			x=35;
		}
		else if (way==2) {
			y = (double)rand.nextInt(200);
			x=710;
		}
		else if (way==3) {
			x = (double)rand.nextInt(800);
			y=4;
		}
		else if (way==4) {
			x = (double)rand.nextInt(800);
			y=370;
		}
		setImage();
		this.hero = hero; 
	}

	@Override
	public void draw(GraphicsContext gc) {
		System.out.println("monster");
		if((tick/20)%2==0)monsterPic = monsterUp;
		else monsterPic = monsterDown;
		gc.drawImage(monsterPic, x, y);
		tick++;
		
		
	}
	public void setImage() {
		monsterPic = monsterUp;
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
		//if (way==1) x+=2;
		//else if (way==2) x-=2;
		//else if (way==3) y+=2;
		//else if (way==4) y-=2;
		x+= 1*(calculateCos(hero.getX(),hero.getY()));
		y+= 1*(calculateSin(hero.getX(),hero.getY()));
		boolean IsHeroAttacked;
		IsHeroAttacked = hero.isAttacked(x, y);
		if(IsHeroAttacked) isVisible = false;
		
	}
	
	public double calculateSin(double herox, double heroy) {
		double kam = heroy-this.y;
		double chid = herox-this.x;
		double chack = Math.sqrt((kam*kam)+(chid*chid));
		double sin = kam/chack;
		return sin;
		
	}
	
	public double calculateCos(double herox, double heroy) {
		double kam = heroy-this.y;
		double chid = herox-this.x;
		double chack = Math.sqrt((kam*kam)+(chid*chid));
		double cos = chid/chack;	
		return cos;
	}
	
	
	
	
	
	
	
}
