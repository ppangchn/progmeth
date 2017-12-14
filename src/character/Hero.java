package character;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Hero extends Entity implements IRenderable {
	private boolean lvthreebefore = false;
	private boolean lvfourbefore = false;
	private boolean lvfivebefore = false;
	private boolean lvsixbefore = false;
	private boolean lvsevenbefore = false;
	private String control;
	private int life = 10;
	public Image heropic;
	private int exp = 0;
	private int lv = 1;
	private int maxexp = 50;
	private int timeOfPics = 0;
	private List<Image> left = new ArrayList<>();
	private List<Image> right = new ArrayList<>();
	private List<Image> front = new ArrayList<>();
	private List<Image> back = new ArrayList<>();
	private boolean isBarrierOn =  false;
	private int barrierCount;
	public Image barrier = new Image("BariaEffect.png");
	private int speed = 4 ; 
	public Image UltiEffect = new Image("UltiEffect.png");
	private boolean  isBoss = false;
	private boolean isUltiOn = false;
	public AudioClip collidesound = new AudioClip(ClassLoader.getSystemResource("animesound.wav").toString());
	
	
	public Hero() {
		super(400,225);
		for (int i=1; i<4; i++) {
			left.add(new Image("left"+i+".png"));
			right.add(new Image("right"+i+".png"));
			front.add(new Image("front"+i+".png"));
			back.add(new Image("back"+i+".png"));
		}
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
	public void setHero() {
		heropic = front.get(0);
	}
	@Override
	public void draw(GraphicsContext gc) {
		//System.out.println("hero");
		timeOfPics++;
		if(timeOfPics>=30) timeOfPics = 0; 
		//System.out.println("earth");
		if(isBarrierOn) gc.drawImage(barrier, x-8, y+15);
		if(isUltiOn()) gc.drawImage(UltiEffect, x-12, y+15);
		gc.drawImage(heropic, x, y);
		
		
	}
	public void updatePos() {
		if (!isBoss) {
			if (control.contains("a")) if(x>=35) {
				x-=speed;
				heropic = left.get(timeOfPics/10);
			}
			if (control.contains("d")) if (x+90<=800) {
				x+=speed; 
				heropic = right.get(timeOfPics/10);
				
			}
			if (control.contains("w")) if (y-60>=0) {
				y-=speed;
				heropic = back.get(timeOfPics/10);
				
			}
			if (control.contains("s")) if (y+90<=460) {
				y+=speed;
				heropic = front.get(timeOfPics/10);
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
			maxexp+=2*lv;
		}
	}
	public Bullet attack(char c) {
		Bullet bullet = new Bullet(x,y,c);
		RenderableHolder.getinstance().add(bullet);
		if (isBoss) bullet.setFromBoss(true);
		bullet.setBullet();
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
			if(isBarrierOn) {
				barrierCount --;
				if(barrierCount == 0) isBarrierOn = false;
			}
			
			else {
				life--;
				collidesound.play();
			}
			return true;
		}
		return false;
	}
	public void barrier() {
		isBarrierOn = true;
		barrierCount = 3;
	}
	public int getBariaCount() {
		return barrierCount;
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
	public boolean isUltiOn() {
		return isUltiOn;
	}
	public void setUltiOn(boolean isUltiOn) {
		this.isUltiOn = isUltiOn;
	}
	public void setControl(String control) {
		this.control =control;
	}
}
