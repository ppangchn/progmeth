package item;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class IncreaseHeroMove extends Item {
	private Random rand = new Random();
	public IncreaseHeroMove() {
//	x = rand.nextDouble();
//		this.y = rand.nextDouble();
		// TODO Auto-generated constructor stub
	}
	
	public void draw(GraphicsContext gc) {
			gc.setFill(Color.AQUA);
			Random rand = new  Random();
			double x = rand.nextDouble();//เน�เธซเน�เธญเธขเธนเน�เธ�เน�เธงเธ�เธ—เธตเน�เธ�เธณเธซเธ�เธ”เธขเธฑเธ�เน�เธ�
			double y = rand.nextDouble();
			gc.fillRect(0, 0, x, y);
			
			
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
