package graphic;

import java.util.Random;

import character.Hero;
import character.Monster;
import item.Item;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameWindow extends Canvas{
	private Hero hero;
	private GameScreen gamescreen;
	private Monster monster;
	private Item item;
	public String control = "";
	public GraphicsContext gc;
	public Scene scene;
	public Stage primaryStage;
	public char c = 'a';
	public boolean hasBullet = false;
	public int frame = 0;
	private AudioClip soundgame;
	public String[] soundgameURL = {"Caramelldansen.mp3","PonPonPon.mp3","Senbonzakura.mp3","Melancholic.mp3","LevanPolkka.mp3"};
	private Random rand;
	private static AnimationTimer gamewindowanimation;
	private StageWindow stagewindow;
	private boolean stageON = false ;
	private AudioClip fire = new AudioClip(ClassLoader.getSystemResource("fire.wav").toString());
	private int CoolDown = 0;
	private boolean isOver = false;
	
	public GameWindow(Stage primaryStage) {
		stagewindow = new StageWindow(getGraphicsContext2D());
		rand = new Random();
		int x = rand.nextInt(soundgameURL.length);
		setWidth(800);
		setHeight(450);
		this.primaryStage = primaryStage;
		gc = getGraphicsContext2D();
		StackPane s = new StackPane();
		s.getChildren().add(gc.getCanvas());
		scene = new Scene(s);
		hero = new Hero();
		gamescreen = new GameScreen();
		monster = new Monster(hero);
		this.primaryStage.setScene(scene);
		RenderableHolder.getinstance().add(gamescreen);
		RenderableHolder.getinstance().add(hero);
		RenderableHolder.getinstance().add(monster);
		soundgame = new AudioClip(ClassLoader.getSystemResource(soundgameURL[x]).toString());
		soundgame.play();
		
//		RenderableHolder.getinstance().add(item);
		requestFocus();
	}
	public void drawGameWinDow() {
		addMoving(gc);
		frame=0;
		//addMonster();
		
		
		gamewindowanimation = new AnimationTimer() {
			public void handle(long now) {
				frame++;

			if ((frame%600)<500)
				{
					
					if (frame%60 ==0 )addMonster();
				}
			
				RenderableHolder.getinstance().draw(gc);
				int exp = RenderableHolder.getinstance().setVisible();
				int score = RenderableHolder.getinstance().remove();
				RenderableHolder.getinstance().Collision(hero);
				hero.setExp(hero.getExp()+exp);
				hero.updateLv();
				gamescreen.setHeroData(hero.getLv(),hero.getExp(),hero.getMaxexp(),hero.getLife(),gc);
				gamescreen.setScore(gamescreen.getScore()+score);
				gamescreen.setSkillData(hero.getBariaCount(), CoolDown);
				if(CoolDown != 0 ) CoolDown--;
				
				RenderableHolder.getinstance().update(control);
				if (soundgame.isPlaying()==false) playSong();
				if (hero.getLv()==3 && hero.isLvthreebefore()==false && !isOver) {
					hero.setLvthreebefore(true);
					gamewindowanimation.stop();
					stagewindow.draw();
					stageON = true;
				}
				if (hero.getLife()==0) {
					gamewindowanimation.stop();
					GameOver.draw(gc);
					isOver = true;
				}
			}
			};
			gamewindowanimation.start();
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
				if(stageON) {
				    gamewindowanimation.start();
				    stageON = false ;
				}
				if (!isOver) {
					fire.play();
					hero.attack(c);
				}
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (isOver) {
					soundgame.stop();
					StartWindow startwindow = new StartWindow(primaryStage);
					startwindow.drawStartWindow();
					}
			}
			if(KeyEvent.getCode() == KeyCode.D) {
				if(CoolDown == 0)
				{
			    fire.play();
				hero.attack('s');
				hero.attack('w');
				hero.attack('d');
				hero.attack('a');
				CoolDown = 90;
				}
			}
			if(KeyEvent.getCode() == KeyCode.S) {
				hero.barrier();
			}
			if (KeyEvent.getCode() == KeyCode.Q) {
				
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
		monster = new Monster(hero);
		RenderableHolder.getinstance().add(monster);
	}
	public void addItem() {
		
		RenderableHolder.getinstance().add(item);
	}
	public void playSong() {
		int x = rand.nextInt(soundgameURL.length);
		soundgame = new AudioClip(ClassLoader.getSystemResource(soundgameURL[x]).toString());
		soundgame.play();
	}
	public static AnimationTimer getGamewindowanimation() {
		return gamewindowanimation;
	}
	

}
	