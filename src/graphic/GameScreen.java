package graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;

public class GameScreen implements IRenderable {
	private int lv;
	private int exp;
	private int maxexp;
	private int life;
	public Image bggame;
	public Image skillbullet;
	public Image skillulti;
	public Image skillBarrier;
	public GameScreen() {
		setImage();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bggame, 0, 0);
		String image_path = "heart.png";
		Image heart = new Image(image_path);
		Font font = new Font("Monospace", 18);
		gc.setFont(font);
		gc.drawImage(heart, 20,0);
		gc.setFill(Color.WHITE);
		gc.fillText(" : "+life, 50, 20);
		gc.fillText("Lv: "+lv, 440, 20);
		gc.fillText("Exp : "+exp+"/"+maxexp,500, 20);
		gc.setFill(Color.WHITE);
		gc.drawImage(skillBarrier, 220,5);
		gc.fillText("S",231 , 55);
		gc.drawImage(skillbullet, 260, 5);
		gc.fillText("D",271,55);
		gc.drawImage(skillulti, 300 , 5);
		gc.fillText("F", 311, 55);
		
		
		
		
	}
	public void setImage() {
		bggame = new Image("bggame.jpg");
		
	}
	@Override
	public boolean isVisible() {
		return true;
	}
	public void setHeroData(int lv,int exp,int maxexp,int life,GraphicsContext gc) {
		this.lv =lv;
		this.exp = exp;
		this.maxexp = maxexp;
		this.life = life;
	}
	public void setCoolDown(int CoolDownFire , int CoolDownBarrier , int CoolDownUltimateSkill) {
		 skillbullet = new Image("Fireball.png");
		 skillulti = new Image("Ulti.png");
		 skillBarrier = new Image("Baria.png");
		if(CoolDownFire != 0) skillbullet = new Image("FireballCoolDown.png");
		if(CoolDownBarrier != 0)skillBarrier = new Image("BariaCoolDown.png");
		if(CoolDownUltimateSkill != 0) skillulti = new Image("UltiCoolDown.png");
		
	}
	
	
}
