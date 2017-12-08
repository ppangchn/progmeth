package graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;

public class GameScreen implements IRenderable {
	private int currentscore = 0;
	private int lv;
	private int exp;
	private int maxexp;
	private int life;
	private Image bggame;
	private int BCount;
	private int CoolDown ;
	
	public GameScreen() {
		setImage();
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
		gc.setFill(Color.WHITE);
		gc.fillText(" : "+life, 50, 20);
		gc.fillText("Lv: "+lv, 440, 20);
		gc.fillText("Exp : "+exp+"/"+maxexp,500, 20);
		gc.fillText("SCORE : "+currentscore, 650, 20);
		gc.setFill(Color.WHITE);
		//Image skill = new Image("skillinven.png");
		//gc.drawImage(skill,346,386);
		Image skillbullet = new Image("fuckingfireball1.png");
		gc.drawImage(skillbullet, 220,10);
		
		Image skillulti = new Image("fuckingulti1.png");
		gc.drawImage(skillulti, 260, 10);
		
		Image skillBaria = new Image("fuckingBaria1.png");
		gc.drawImage(skillBaria, 300 , 10);
		gc.fillText("B"+BCount,750,350);
		gc.fillText("C"+CoolDown,750,380);
		//System.out.println("bg");
		
		
		
	}
	public void setImage() {
		System.out.println("AA");
		bggame = new Image("bggame.jpg");
		
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public int getScore() {
		return currentscore;
	}
	public void setScore(int score) {
		currentscore = score;
	}
	public void setHeroData(int lv,int exp,int maxexp,int life,GraphicsContext gc) {
		this.lv =lv;
		this.exp = exp;
		this.maxexp = maxexp;
		this.life = life;
	}
	
	public void setSkillData(int BulletCount , int CoolDown) {
		this.BCount = BulletCount ;
		this.CoolDown = CoolDown/30;
		
	}
	
	
}
