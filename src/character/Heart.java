package character;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public class Heart extends Item implements IRenderable {
	private int n =0;
	public List<Image> image = new ArrayList<>();
	public Heart() {
		for (int i=0; i<16; i++) {
			Image item = new Image("frame"+i+".png",40,40,false,false);
			image.add(item);
		}
		
	}
	@Override
	public void effect(Hero hero) {
		hero.gainHP();
		this.isVisible =false;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
			gc.drawImage(image.get(n), x, y);
		n++;
		if (n>=15) n=0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.isVisible;
	}

}
