package character;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public class Boss extends Monster implements IRenderable {
	private String[] left = {"bossleft1.png","bossleft2.png","bossleft3.png","bossleft4.png"};
	private String[] right = {"bossright1.png","bossright2.png","bossright3.png","bossright4.png"};
	private String[] front = {"bossfront1.png","bossfront2.png","bossfront3.png","bossfront4.png"};
	private String[] back = {"bossback1.png","bossback2.png","bossback3.png","bossback4.png"};
	public Boss(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		
	}
	
}
                 