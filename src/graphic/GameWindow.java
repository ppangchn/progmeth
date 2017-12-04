package graphic;

import java.util.Random;

import character.Bullet;
import character.Hero;
import character.Monster;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameWindow extends Canvas{
	public Hero hero;
	public Bullet bullet;
	public GameScreen gamescreen;
	public Monster monster;
	public String control = "";
	public GraphicsContext gc;
	public Scene scene;
	public Stage primaryStage;
	public char c = 'a';
	public boolean hasBullet = false;
	public int frame = 0;
	
	public GameWindow(Stage primaryStage) {
		setWidth(800);
		setHeight(450);
		this.primaryStage = primaryStage;
		gc = getGraphicsContext2D();
		StackPane s = new StackPane();
		s.getChildren().add(gc.getCanvas());
		scene = new Scene(s);
		hero = new Hero();
		gamescreen = new GameScreen();
		monster = new Monster();
		this.primaryStage.setScene(scene);
		System.out.println("pang noob");
		RenderableHolder.getinstance().add(gamescreen);
		RenderableHolder.getinstance().add(hero);
		RenderableHolder.getinstance().add(monster);
		requestFocus();
	}
	public void drawGameWinDow() {
		addMoving(gc);
		frame=0;
		//addMonster();
		
		
		AnimationTimer a = new AnimationTimer() {
			public void handle(long now) {
				frame++;
			if (frame%300 == 0)addMonster();
//			if (hero.getLv()>=2 && hero.getLv()<=4) {
//				frame-=2;
//			}
//			if(frame%25 ==0 && hero.lv>1 && hero.lv <=6) addMonster();
//			if(frame%25 ==0 && hero.lv>6 && hero.lv<= 8) addMonster();
//			if(frame%10 ==0 && hero.lv>8 && hero.lv <=10 ) addMonster();
////			if (frame%10000 == 0)//dropItem
//			
				RenderableHolder.getinstance().draw(gc);
				int exp = RenderableHolder.getinstance().setVisible();
				int score = RenderableHolder.getinstance().remove();
				RenderableHolder.getinstance().Collision(hero);
				hero.setExp(hero.getExp()+exp);
				hero.updateLv();
				gamescreen.setHeroData(hero.getLv(),hero.getExp(),hero.getMaxexp(),hero.getLife(),gc);
				gamescreen.setScore(score);
				RenderableHolder.getinstance().update(control);
				}
			};
			a.start();
	}
	
	public void addMoving(GraphicsContext gc) {
		this.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.LEFT) {
				control+="a";
				c='a';
				System.out.println(control);
			}
			if (KeyEvent.getCode() == KeyCode.RIGHT) {
				control+="d";
				c='d';
				System.out.println(control);
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				control+="w";
				c='w';
				System.out.println(control);
				
			}
			if (KeyEvent.getCode() == KeyCode.DOWN ) {
				control+="s";
				c='s';
				System.out.println(control);
				
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				bullet = hero.attack(c);
				
			}
		});
		this.setOnKeyReleased((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.LEFT) {
				control = control.replace("a","");
				//bullet.c='a';
				RenderableHolder.getinstance().update(control);
			}
			if (KeyEvent.getCode() == KeyCode.RIGHT) {
				control = control.replace("d", "");
				//bullet.c='d';
				RenderableHolder.getinstance().update(control);
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				control = control.replace("w", "");
				//bullet.c='w';
				RenderableHolder.getinstance().update(control);
				
			}
			if (KeyEvent.getCode() == KeyCode.DOWN ) {
				control = control.replace("s", "");
				//bullet.c='s';
				RenderableHolder.getinstance().update(control);
				
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				RenderableHolder.getinstance().update(control);
			}
		});
		
	}
	public void addMonster() {
		monster = new Monster();
		RenderableHolder.getinstance().add(monster);
	}
}
	