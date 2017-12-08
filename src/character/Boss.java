package character;

import java.util.ArrayList;
import java.util.List;

import graphic.GameWin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Boss extends Monster implements IRenderable {
	private List<Image> front = new ArrayList<>();
	private double x ;  //=center;
	private double y; //=center;
	private int life = 10;
	public Image bosspic;
	private int time = 0;
	private int count = 0;
	private boolean isVisible = true;
	public Boss(Hero hero) {
		super(hero);
		for (int i=1; i<5; i++) {
			front.add(new Image("bossfront"+i+".png",40,58,false,false));
		}
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(GraphicsContext gc) {
		time++;
		if(time==15) {
			count++;
			time = 0; 
		}
		gc.drawImage(front.get(count), 400, 100);
		
		if (count>=3) count = 0;
		
	}
	@Override
	public boolean isVisible() {
		return isVisible;
	}
//	@Override
//	public void setImage() {
//		// TODO Auto-generated method stub
//		bosspic = new Image("bossfront1.png",40,58,false,false);
//	}

	
}
                 