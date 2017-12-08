package sharedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import character.Bullet;
import character.Hero;
import character.Monster;
import javafx.scene.canvas.GraphicsContext;

public class RenderableHolder {
	Scanner keyboard = new Scanner(System.in);
	private static final RenderableHolder r = new RenderableHolder();
	private List<IRenderable> object;
	private int bulletC ;
	public RenderableHolder() {
		object = new ArrayList<>();
	}
	public static RenderableHolder getinstance() {
		return r;
	}
	public void add(IRenderable i) {
		object.add(i);
	}
	public void update(String control) {
		for (IRenderable i : object) {
			if ( i instanceof Hero) {
				((Hero)i).updatePos(control);
			}
			if (i instanceof Bullet) {
				((Bullet)i).updatePos();
			}
			if (i instanceof Monster) {
				((Monster)i).updatePos();
			}
		}
	}
	public void draw(GraphicsContext gc) {
		for (IRenderable i : object) {

		//System.out.println("check");

			i.draw(gc);
		}
	}
	public int setVisible() {
		int exp =0;
		for (IRenderable i : object) {
			if (i instanceof Monster) {
				for (IRenderable j : object) {
					if (j instanceof Bullet) {
						if (((Monster)i).isDestroyed(((Bullet)j).getX(), ((Bullet)j).getY())) {
							((Monster)i).setVisible(false);
							((Bullet)j).setIsvisible(false);
							exp+=10;
						}
					}
			}
			
			}
			
		}
		return exp;
	}
	public int remove() {
		int score =0;
		int n = object.size();
		for (int i=n-1; i>=0; i--) {
			if (object.get(i).isVisible() == false) {
				if(object.get(i) instanceof Bullet) System.out.println("remove");
				if(object.get(i) instanceof Monster) score++;
				object.remove(i);
				
			}
			
			
		}
		return score;
	}
	public void Collision(Hero hero) {
		for (IRenderable i : object) {
			if (i instanceof Monster) {
				if (((Monster)i).getX()==hero.getX() && ((Monster)i).getY()==hero.getY()) {
					hero.decreaseLife();
				}
			}
		}
	}
	
	public void UltimateSkill() {
		for(IRenderable i : object) {
			if(i instanceof Monster) {
				if(i.isVisible()) ((Monster) i).setVisible(false);
			}
		}
	}
	
}
