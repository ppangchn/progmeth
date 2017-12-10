package character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Boss extends Hero {
	private List<Image> front = new ArrayList<>();
	
	private double x = 400;
	private double y = 100;
	private int life = 20;
	private int time = 0;
	private int countframe = 0;
	private boolean isVisible = true;
	private boolean isCoolDown = false;
	private Random rand = new Random();
	public Image bosspic;
	public Boss() {
		super();
		this.setX(x);
		this.setY(y);
		this.setBoss(true);
		for (int i=1; i<5; i++) {
			front.add(new Image("bossfront"+i+".png",40,58,false,false));
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(front.get(countframe),this.getX(), this.getY());
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		gc.strokeRect(150, 400, 480, 10);
		gc.fillRect(150, 400, 480*life/20, 10);
		System.out.println(time);
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
			System.out.println("random");
		}
		if (time==150) {
			double earth = rand.nextDouble()*680;
			for(int i=0;i<100;i++) {
				System.out.println(earth);
			}
			
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
		if (life<=0) return true;
		return false;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
}
                 