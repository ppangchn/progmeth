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
	private Image bggame;
	private int BCount;
	private int CoolDown ;
	private Image skillbullet;
	private Image skillulti;
	private Image skillBaria;
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
		gc.setFill(Color.WHITE);
		//Image skill = new Image("skillinven.png");
		//gc.drawImage(skill,346,386);
		//System.out.println("bg");
		gc.drawImage(skillBaria, 220,5);
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
	
	public void setSkillData(int BulletCount , int CoolDown) {
		this.BCount = BulletCount ;
		this.CoolDown = CoolDown/30;
		
	}
	public void setCoolDown(int CoolDownFire , int CoolDownBarrier , int CoolDownUltimateSkill) {
		 skillbullet = new Image("fuckingfireball1.png");
		 skillulti = new Image("fuckingulti1.png");
		 skillBaria = new Image("fuckingBaria1.png");
		if(CoolDownFire != 0) skillbullet = new Image("fireballCoolDown.png");
		if(CoolDownBarrier != 0)skillBaria = new Image("BariaCoolDown.png");
		if(CoolDownUltimateSkill != 0) skillulti = new Image("UltiCoolDown.png");
		
	}
	
	
}
