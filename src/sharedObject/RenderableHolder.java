package sharedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import character.Boss;
import character.Bullet;
import character.Hero;
import character.Item;
import character.Monster;
import javafx.scene.canvas.GraphicsContext;

public class RenderableHolder {
	Scanner keyboard = new Scanner(System.in);
	private static final RenderableHolder r = new RenderableHolder();
	private List<IRenderable> object;
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
			if(i instanceof Item) {
				((Item)i).update();
			}
		}
	}
	public void draw(GraphicsContext gc) {
		for (int i =0; i<object.size(); i++) {
			object.get(i).draw(gc);
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
	public void remove() {
		int n = object.size();
		for (int i=n-1; i>=0; i--) {
			if (object.get(i).isVisible() == false) {
				object.remove(i);
			}
		}
	}
	public void Collision(Hero hero) {
		for (IRenderable i : object) {
			if (i instanceof Monster) {
				if (((Monster)i).getX()==hero.getX() && ((Monster)i).getY()==hero.getY()) {
					hero.decreaseLife();
				}
			}
			if(i instanceof Item) {
				
				if(getDist(hero.getX(),((Item)i).getX(),hero.getY(),((Item)i).getY()) <= 20) {
					System.out.println(""+hero.getX()+" "+((Item)i).getX()+" "+hero.getY()+" "+((Item)i).getY());
					((Item) i).effect(hero);
				}
			}
			if (i instanceof Bullet) {
				if (getDist(hero.getX(),((Bullet)i).getX(),hero.getY(),((Bullet)i).getY()) <= 10 && ((Bullet)i).isFromBoss()) {
					System.out.println("nedd to dai");
					hero.decreaseLife();
				}
				if (hero.isBoss()) {
					if (getDist(hero.getX(),((Bullet)i).getX(),hero.getY(),((Bullet)i).getY()) <= 10) {
						System.out.println("nedd to dai boss");
						hero.decreaseLife();
					}
				}
			}
		}
	}
	public double getDist(double x1,double x2,double y1,double y2) {
		double ans = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		return ans;
	}
	
	public void UltimateSkill() {
		for(IRenderable i : object) {
			if(i instanceof Monster) {
				if(i.isVisible()) ((Monster) i).setVisible(false);
			}
		}
	}
	public void clearList() {
		this.object = null;
		this.object = new ArrayList<>();
	}
	
}
