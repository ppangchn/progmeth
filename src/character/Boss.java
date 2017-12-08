package character;

import graphic.GameWin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Boss extends Monster implements IRenderable {
	private String[] left = {"bossleft1.png","bossleft2.png","bossleft3.png","bossleft4.png"};
	private String[] right = {"bossright1.png","bossright2.png","bossright3.png","bossright4.png"};
	private String[] front = {"bossfront1.png","bossfront2.png","bossfront3.png","bossfront4.png"};
	private String[] back = {"bossback1.png","bossback2.png","bossback3.png","bossback4.png"};
	private double x ;  //=center;
	private double y; //=center;
	private int life = 10;
	public Image bosspic;
	private int time = 0;
	public Boss(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		time++;
		if(time>=30) time = 0; 
		gc.drawImage(bosspic, x, y);
		
		
	}
	public void updatePos() {
		x+= 1.5*(calculateCos(hero.getX(),hero.getY()));
		y+= 1.5*(calculateSin(hero.getX(),hero.getY()));
		if (x<hero.getX()) {
			
		}
		boolean IsHeroAttacked;
		IsHeroAttacked = hero.isAttacked(x, y);
		if(IsHeroAttacked) life--;
		if (life==0) {
			isVisible = false;
			GameWin gamewin = new GameWin();
		}
		
	}
	@Override
	public boolean isVisible() {
		return true;
	}

	
}
                 