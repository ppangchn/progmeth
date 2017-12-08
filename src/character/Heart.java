package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;

public class Heart extends Item implements IRenderable {

	public Heart() {
		super();
		
	}
	@Override
	public void effect(Hero hero) {
		hero.gainHP();
		this.isVisible =false;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.AQUA);
		gc.fillRect(this.x, this.y, 20, 20);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.isVisible;
	}

}
