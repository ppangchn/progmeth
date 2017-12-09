package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Hero implements IRenderable {
	private boolean lvthreebefore = false;
	private boolean lvfourbefore = false;
	private boolean lvfivebefore = false;
	private boolean lvsixbefore = false;
	private boolean lvsevenbefore = false;
	private double x ;  //=center;
	private double y; //=center;
	private int life = 10;
	public Image heropic;
	private int exp = 0;
	private int lv = 1;
	private int maxexp = 30;
	private int time = 0;
	private String[] left = {"left.png","left2.png","left3.png"};
	private String[] right = {"right.png","right2.png","right3.png"};
	private String[] front = {"front.png","front2.png","front3.png"};
	private String[] back = {"back.png","back2.png","back3.png"};
	private boolean isBariaOn =  false;
	private int BariaCount;
	private Image barrier = new Image("baria1.png");
	private int speed = 4 ; 
	private Image UltiEffect = new Image("UltiEffect.png");
	private boolean  isBoss = false;
	public boolean isUltiOn = false;
	
	
	public Hero() {
		this.x = 400;
		this.y = 225;
		setHero();
		
	}
	public void gainHP() {
		this.life+=1;
	}
	public boolean isLvthreebefore() {
		return lvthreebefore;
	}
	public void setLvthreebefore(boolean lvthreebefore) {
		this.lvthreebefore = lvthreebefore;
	}
	public boolean isLvfourbefore() {
		return lvfourbefore;
	}
	public void setLvfourbefore(boolean lvfourbefore) {
		this.lvfourbefore = lvfourbefore;
	}
	public boolean isLvfivebefore() {
		return lvfivebefore;
	}
	public void setLvfivebefore(boolean lvfivebefore) {
		this.lvfivebefore = lvfivebefore;
	}
	public boolean isLvsixbefore() {
		return lvsixbefore;
	}
	public void setLvsixbefore(boolean lvsixbefore) {
		this.lvsixbefore = lvsixbefore;
	}
	public boolean isLvsevenbefore() {
		return lvsevenbefore;
	}
	public void setLvsevenbefore(boolean lvsevenbefore) {
		this.lvsevenbefore = lvsevenbefore;
	}
	public boolean isDead() {
		if (life==0) return true;
		return false;
	}
	public void decreaseLife() {
		life--;
		
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
	@Override
	public void draw(GraphicsContext gc) {
		//System.out.println("hero");
		time++;
		if(time>=30) time = 0; 
		//System.out.println("earth");
		if(isBariaOn) gc.drawImage(barrier, x-8, y+15);
		if(isUltiOn) gc.drawImage(UltiEffect, x-8, y+15);
		gc.drawImage(heropic, x, y);
		
		
	}
	public void updatePos(String control) {
		if (!isBoss) {
			if (control.contains("a")) if(x>=35) {
				x-=speed;
				heropic = new Image(left[time/10]);
			}
			if (control.contains("d")) if (x+90<=800) {
				x+=speed; 
				heropic = new Image(right[time/10]);
				
			}
			if (control.contains("w")) if (y-60>=0) {
				y-=speed;
				heropic = new Image(back[time/10]);
				
			}
			if (control.contains("s")) if (y+90<=460) {
				y+=speed;
				heropic = new Image(front[time/10]);
			}
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
			maxexp+=30*lv;
		}
	}
	public Bullet attack(char c) {
		Bullet bullet = new Bullet(x,y,c);
		RenderableHolder.getinstance().add(bullet);
		if (isBoss) bullet.setFromBoss(true);
		return bullet;
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
	
	public boolean isAttacked(double x, double y) {
		
		if(Math.abs(this.x-x)<=25 && Math.abs(this.y-y)<=25 ) {
			if(isBariaOn) {
				BariaCount --;
				if(BariaCount == 0) isBariaOn = false;
			}
			
			else life--;
			return true;
		}
		return false;
	}
	public void barrier() {
		isBariaOn = true;
		BariaCount = 3;
	}
	public int getBariaCount() {
		return BariaCount;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	public boolean isBoss() {
		return isBoss;
	}
	
	
}
