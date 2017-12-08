package graphic;

import java.util.Random;

import character.Heart;
import character.Hero;
import character.Item;
import character.Monster;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameWindow extends Canvas{
	private Hero hero;
	private GameScreen gamescreen;
	private Monster monster;
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
	private int CoolDown = 0;
	private boolean isOver = false;
	private boolean nameable = false;
	private String playername= "";
	public int time;
	

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
					if(frame %300==0)addItem();
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
				fire();
				if(CoolDown != 0 ) CoolDown--;
	
				
				RenderableHolder.getinstance().update(control);
				if (soundgame.isPlaying()==false && !isOver) playSong();
				if (soundgame.isPlaying()==false) playSong();
				if (soundgame.isPlaying()==false) playSong();
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
				if(stageON) {
					gamewindowanimation.start();
					stageON = false ;
					if(hero.getLv() >= 3)stagewindow.StopAnimationTimer();
					if(hero.getLv() >= 4)stagewindow2.StopAnimationTimer();
					if(hero.getLv() >= 5)stagewindow3.StopAnimationTimer();
					if(hero.getLv() >= 6)stagewindow4.StopAnimationTimer();
					if(hero.getLv() >= 7)stagewindow5.StopAnimationTimer();
			}
			}
			if(KeyEvent.getCode() == KeyCode.D) {
				if (!isOver) {
					if(CoolDown == 0) {
					    fire.play();
						hero.attack('s');
						hero.attack('w');
						hero.attack('d');
						hero.attack('a');
					CoolDown = 90;
					}
					if(CoolDown == 0) {
					    fire.play();
						hero.attack('s');
						hero.attack('w');
						hero.attack('d');
						hero.attack('a');
						CoolDown = 90;
						}
					CoolDown = 90;
				}
			}
			if(KeyEvent.getCode() == KeyCode.S) {
				if (!isOver) {
				hero.barrier();
				}
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
		// TODO Auto-generated method stub
		Item item = new Heart();
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
		if(CoolDown %30 ==  0 && CoolDown!=0)
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
	