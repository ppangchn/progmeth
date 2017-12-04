package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Hero implements IRenderable {
	private double x ;  //=center;
	private double y; //=center;
	private boolean isBuffed = false;
	private int life = 10;
	public Image heropic;
	private int exp = 0;
	private int lv = 1;
	private int maxexp = 50;
	private int time = 0;
	private String[] left = {"left.png","left2.png","left3.png"};
	private String[] right = {"right.png","right2.png","right3.png"};
	private String[] front = {"front.png","front2.png","front3.png"};
	private String[] back = {"back.png","back2.png","back3.png"};
	
	public Hero() {
		this.x = 400;
		this.y = 225;
		setHero();
	}
	public boolean isDead() {
		if (life==0) return true;
		return false;
	}
	public void shoot() {
		
	}
	public void decreaseLife() {
		life--;
		
	}
	public void randomItem() {
		
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
	public void setHero() {
		heropic = new Image("front.png");
	}
<<<<<<< HEAD
||||||| merged common ancestors

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

=======


>>>>>>> c2650ba8d6714896cf0c015c69a317ddd5fe9f91
	@Override
	public void draw(GraphicsContext gc) {
		System.out.println("hero");
		time++;
		if(time>=30) time = 0; 
		gc.drawImage(heropic, x, y);
		
		
	}
	public void updatePos(String control) {
		if (control.contains("a")) if(x>=0) {
			x-=4;
			heropic = new Image(left[time/10]);
		}
		if (control.contains("d")) if (x+90<=800) {
			x+=4; 
			heropic = new Image(right[time/10]);
			
		}
		if (control.contains("w")) if (y-4>=0) {
			y-=4;
			heropic = new Image(back[time/10]);
			
		}
		if (control.contains("s")) if (y+90<=450) {
			y+=4;
			heropic = new Image(front[time/10]);
		}
	}
	@Override
	public boolean isVisible() {
		return true;
	}

	public void updateLv() {
		if (exp>=maxexp) {
			lv++;
			exp=0;
			maxexp+=50*lv;
		}
	}
	public Bullet attack(char c) {
		Bullet bullet = new Bullet(x,y,c);
		RenderableHolder.getinstance().add(bullet);
		//control+="b";
		return bullet;
	}
	public void Collision() {
		
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	
	public int getMaxexp() {
		return maxexp;
	}

	public void setMaxexp(int maxexp) {
		this.maxexp = maxexp;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	
	
	
}