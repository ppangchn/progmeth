package graphic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sharedObject.IRenderable;

public class GameScreen implements IRenderable {
	public int score = 0;
	public int lv;
	public int exp;
	public int maxexp;
	public int life;
	public Image bggame;
	
	public GameScreen() {
		setImage();
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		//bggame = new Image("bggame.png");
		gc.drawImage(bggame, 0, 0);
		//gc.setFill(Color.PINK);
		//gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		String image_path = "heart.png";
		Image heart = new Image(image_path);
		Font font = new Font("Monospace", 18);
		gc.setFont(font);
		gc.drawImage(heart, 20,0);
		gc.setFill(Color.BLACK);
		gc.fillText(" : "+life, 50, 20);
		gc.fillText("Lv: "+lv, 440, 20);
		gc.fillText("Exp : "+exp+"/"+maxexp,500, 20);
		gc.fillText("SCORE : "+score, 650, 20);
		System.out.println("bg");
		
		
	}
	public void setImage() {
		System.out.println("AA");
		bggame = new Image("bggame.png");
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setHeroData(int lv,int exp,int maxexp,int life,GraphicsContext gc) {
		this.lv =lv;
		this.exp = exp;
		this.maxexp = maxexp;
		this.life = life;
	}
}
