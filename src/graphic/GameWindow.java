package graphic;

import java.util.Random;

import character.Hero;
import character.Monster;
import item.Item;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sharedObject.IRenderable;
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
	private StageWindow2 stagewindow2;
	private StageWindow3 stagewindow3;
	private StageWindow4 stagewindow4;
	private StageWindow5 stagewindow5;
	private boolean stageON = false ;
	private AudioClip fire = new AudioClip(ClassLoader.getSystemResource("fire.wav").toString());
	private int FireTimes = 0;
	private boolean isOver = false;
	private boolean nameable = false;
	private String playername= "";
	public int time;
	public int CoolDownUltimateSkill;
	public int CoolDownFire;
	public int CoolDownBarrier;
	public int CoolDownSpeed;
	

	
	
	public GameWindow(Stage primaryStage) {
		stagewindow = new StageWindow(getGraphicsContext2D());
		stagewindow2 = new StageWindow2(getGraphicsContext2D());
		stagewindow3 = new StageWindow3(getGraphicsContext2D());
		stagewindow4 = new StageWindow4(getGraphicsContext2D());
		stagewindow5 = new StageWindow5(getGraphicsContext2D());
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
				gamescreen.setSkillData(hero.getBariaCount(), FireTimes);
				fire();
				if(FireTimes != 0 ) FireTimes--;
				if(CoolDownUltimateSkill != 0) CoolDownUltimateSkill--;
				if(CoolDownFire !=0 )CoolDownFire--;
				if(CoolDownBarrier != 0 )CoolDownBarrier--;
				if(CoolDownSpeed != 0) {
					CoolDownSpeed--;
					hero.setSpeed(8);
				}
				else {hero.setSpeed(3); hero.isUltiOn =false;}
				
				RenderableHolder.getinstance().update(control);
				if (soundgame.isPlaying()==false && !isOver) playSong();
				
				if (hero.getLv()==3 && hero.isLvthreebefore()==false && !isOver) {
					hero.setLvthreebefore(true);
					gamewindowanimation.stop();
					stagewindow.draw();
					stageON = true;
				}
				
				if (hero.getLv()==4 && hero.isLvsixbefore()==false && !isOver) {
					hero.setLvsixbefore(true);
					gamewindowanimation.stop();
					stagewindow2.draw();
					stageON = true;
				}
				if (hero.getLv()==5 && hero.isLveightbefore()==false && !isOver) {
					hero.setLveightbefore(true);
					gamewindowanimation.stop();
					stagewindow3.draw();
					stageON = true;
				}
				if (hero.getLv()==6 && hero.isLvninebefore()==false && !isOver) {
					hero.setLvninebefore(true);
					gamewindowanimation.stop();
					stagewindow4.draw();
					stageON = true;					
				}
				if (hero.getLv()==7 && hero.isLvsixbefore()==false && !isOver) {
					hero.setLvtenbefore(true);
					gamewindowanimation.stop();
					stagewindow5.draw();
					stageON = true;
				}
				
				if (hero.getLife()==0) {
					GameOver.draw(gc);
					gamewindowanimation.stop();
					isOver = true;
					soundgame.stop();
				}
			}
			};
			gamewindowanimation.start();
	}
	
	public void addMoving(GraphicsContext gc) {
		this.setOnKeyPressed((KeyEvent) -> {
			if (nameable) {
				if (KeyEvent.getCode() == KeyCode.BACK_SPACE) {
					String tmp = playername;
					playername ="";
					for (int n=0; n<tmp.length()-1; n++) {
						playername +=tmp.charAt(n);
					}
				}
				AnimationTimer name = new AnimationTimer() {
					public void handle(long now) {
						HighScore.draw(gc,playername);
					}
				};
				name.start();
				if (playername.length()==11) {
					Alert alert = new Alert(AlertType.ERROR,"Player's name is too long", ButtonType.OK);
	                alert.setTitle("Error:PLAYER'S NAME IS TOO LONG");
	                alert.setHeaderText("");
	                alert.show();
				}
				playername +=KeyEvent.getText();
				System.out.println(playername);
			}
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
					if(time <= 0) {
						gamewindowanimation.start();
						stageON = false ;
						if(hero.getLv() == 3)stagewindow.StopAnimationTimer();
						if(hero.getLv() == 4)stagewindow2.StopAnimationTimer();
						if(hero.getLv() == 5)stagewindow3.StopAnimationTimer();
						if(hero.getLv() == 6)stagewindow4.StopAnimationTimer();
						if(hero.getLv() == 7)stagewindow5.StopAnimationTimer();
					}
				}
				if (!isOver) {
					fire.play();
					hero.attack(c);
				}
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (isOver && !nameable) {
					soundgame.stop();
					GameOver.stopAnimationTimer();
					nameable = true;
					HighScore.draw(gc,playername);
					
//					StartWindow startwindow = new StartWindow(primaryStage);
//					startwindow.drawStartWindow();
					}
				else if (isOver && nameable) {
					HighScore.addData(gamescreen.getScore(),playername);
					HighScore.drawTable(gc);
				}
			}
			if(KeyEvent.getCode() == KeyCode.D) {
				if(CoolDownFire == 0) {FireTimes = 90;
				CoolDownFire = 150;
				}
				
				
			}
			if(KeyEvent.getCode() == KeyCode.S) {

				if(CoolDownBarrier == 0 && !isOver) {
					hero.barrier();
					CoolDownBarrier = 300;
				}
				

			}
			if (KeyEvent.getCode() == KeyCode.F) {
				if(CoolDownUltimateSkill == 0 ) {
					RenderableHolder.getinstance().UltimateSkill();
					CoolDownUltimateSkill = 90;
					CoolDownSpeed = 150;
					hero.isUltiOn = true;
				}
				
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
	
	public void fire() {
		if(FireTimes %30 ==  0 && FireTimes!=0 )
		{	
			fire.play();
			hero.attack('s');
			hero.attack('w');
			hero.attack('d');
			hero.attack('a');
		    hero.attack('r');
		    hero.attack('t');
		    hero.attack('u');
		    hero.attack('y');
		}	
	}
	

}
	