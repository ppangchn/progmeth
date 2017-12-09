package character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import graphic.GameWinner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Boss extends Hero implements IRenderable {
	private List<Image> front = new ArrayList<>();
	
	private double x = 400;
	private double y = 100;
	private int life = 5;
	private int time = 0;
	private int countframe = 0;
	private boolean isVisible = true;
	private boolean isCoolDown = false;
	private Random rand;
	public Image bosspic;
	public Boss() {
		super.setX(x);
		super.setY(y);
		super.setBoss(true);
		for (int i=1; i<5; i++) {
			front.add(new Image("bossfront"+i+".png",40,58,false,false));
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(front.get(countframe),this.getX(), this.getY());
		if (!isCoolDown) {
			if(time%5==0) {
				attack('a');
				attack('d');
				attack('s');
				attack('r');
				attack('u');
			}
		}
		if(time%15==0) countframe++;
		if (time==100) {
			System.out.println("coooldown");
			isCoolDown = true;
		}
		if (time==150) {
			Random rand = new Random();
			double earth = rand.nextDouble()*640;
			this.setX(earth);
			this.isCoolDown=false;
			time =0;
		}
		
		time++;
		
		if (countframe>=3) countframe = 0;
	}
	
	@Override
	public boolean isVisible() {
		return isVisible;
	}
	public boolean isDead() {
		if (life==0) return true;
		return false;
	}
	
}
                 