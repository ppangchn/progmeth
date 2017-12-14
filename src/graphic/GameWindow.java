package graphic;

import java.util.Random;

import character.Boss;
import character.Heart;
import character.Hero;
import character.Item;
import character.Monster;
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
	private static AnimationTimer gamewindowanimation;
	private Hero hero;
	private GameScreen gamescreen;
	private Monster monster;
	private Random rand = new Random();
	private boolean stageON = false ;
	private boolean isOver = false;
	private boolean isStateFive = false;
	private boolean AddedBoss = false;
	private boolean toMainMenu = false;
	private int FireTimes = 0;
	private int CoolDownUltimateSkill;
	private int CoolDownFire;
	private int CoolDownBarrier;
	private int CoolDownSpeed;
	private Boss boss;
	public AudioClip soundgame;
	public AudioClip fire = new AudioClip(ClassLoader.getSystemResource("Laser.mp3").toString());
	public AudioClip bosssound = new AudioClip(ClassLoader.getSystemResource("BossSound130.wav").toString());
	public AudioClip winnersound = new AudioClip(ClassLoader.getSystemResource("win.wav").toString());
	public String control = "";
	public GraphicsContext gc;
	public Scene scene;
	public Stage primaryStage;
	public char c = 'a';
	public boolean hasBullet = false;
	public int frame = 0;
	public String[] soundgameURL = {"Caramelldansen.mp3","PonPonPon.mp3","Senbonzakura.mp3","Melancholic.mp3","LevanPolkka.mp3"};
	private int monsteramount = 1;
	private int InitialSpeed;

	public GameWindow(Stage primaryStage) {
		setWidth(800);
		setHeight(450);
		this.primaryStage = primaryStage;
		gc = getGraphicsContext2D();
		StackPane s = new StackPane();
		s.getChildren().add(gc.getCanvas());
		scene = new Scene(s);
		this.primaryStage.setScene(scene);
		addAll();
		int x = rand.nextInt(soundgameURL.length);
		soundgame = new AudioClip(ClassLoader.getSystemResource(soundgameURL[x]).toString());
		soundgame.play();
		InitialSpeed = hero.getSpeed();
		requestFocus();
	}
	public void drawGameWinDow() {
		addMoving(gc);
		frame=0;
		gamewindowanimation = new AnimationTimer() {
			public void handle(long now) {
				//System.out.println(isStateFive);
				updateDetail();
				updateState();
				updateSong();
				isGameEnd();
			}
		};
		gamewindowanimation.start();
	}
	public void addMoving(GraphicsContext gc) {
		this.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.L) hero.setLv(this.hero.getLv()+1);
			if (KeyEvent.getCode() == KeyCode.B) hero.setLv(7);
			if (KeyEvent.getCode() == KeyCode.O) hero.setLife(0);
			if(KeyEvent.getCode() == KeyCode.P) {
				hero.setLv(7);
				hero.setLife(1000000000);
			
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
				if (!isOver && !stageON && !toMainMenu) {
					fire.play();
					hero.attack(c);
				}
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (isOver && GameOver.isFinished()) {
					soundgame.stop();
					StartWindow startwindow =new StartWindow(primaryStage);
					startwindow.startAnimation();
					}
				if(stageON) {
					StageWindow.StopAnimationTimer();
					gamewindowanimation.start();
					stageON = false ;
				
				}
				if (toMainMenu && GameWinner.isFinished) 	{
					
					StartWindow startwindow = new StartWindow(primaryStage);
					startwindow.startAnimation();
					bosssound.stop();
					toMainMenu = false;
				}
			}
			if(KeyEvent.getCode() == KeyCode.D) {
				if(!isOver) {
				if(CoolDownFire == 0) {FireTimes = 90;
				CoolDownFire = 300;
				}
				}

			}
			if(KeyEvent.getCode() == KeyCode.S) {
				
				if(!isOver) {
				if(CoolDownBarrier == 0) {

					hero.barrier();
					CoolDownBarrier = 600;
				}
				}


			}
			if (KeyEvent.getCode() == KeyCode.F) {
				if(!isOver) {
				if(CoolDownUltimateSkill == 0 ) {
					int exp = RenderableHolder.getinstance().UltimateSkill();
					hero.setExp(hero.getExp()+exp);
					hero.updateLv();
					gamescreen.setHeroData(hero.getLv(),hero.getExp(),hero.getMaxexp(),hero.getLife(),gc);
					CoolDownUltimateSkill = 1500;//note
					CoolDownSpeed = 150;
					hero.setUltiOn(true);
					}
				}
			}			
		});
		this.setOnKeyReleased((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.LEFT) {
				control = control.replace("a","");
				RenderableHolder.getinstance().updatePos(control);
			}
			if (KeyEvent.getCode() == KeyCode.RIGHT) {
				control = control.replace("d", "");
				RenderableHolder.getinstance().updatePos(control);
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				control = control.replace("w", "");
				RenderableHolder.getinstance().updatePos(control);
			}
			if (KeyEvent.getCode() == KeyCode.DOWN ) {
				control = control.replace("s", "");
				RenderableHolder.getinstance().updatePos(control);
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				RenderableHolder.getinstance().updatePos(control);
			}
		});
		
	}
	public void updateDetail() {
		frame++;
		if ((frame%600)<500)
			{
				if (frame%60 ==0 && !isStateFive) {
					for(int i = 0 ; i<monsteramount ; i++) addMonster();
				}
				if(frame %300==0) addItem();
			}
			RenderableHolder.getinstance().remove();
			RenderableHolder.getinstance().draw(gc);
			int exp = RenderableHolder.getinstance().setVisible();
			RenderableHolder.getinstance().Collision(hero);
			if (AddedBoss) RenderableHolder.getinstance().Collision(boss);
			hero.setExp(hero.getExp()+exp);
			hero.updateLv();
			gamescreen.setHeroData(hero.getLv(),hero.getExp(),hero.getMaxexp(),hero.getLife(),gc);
			fire();
			if(FireTimes != 0 ) FireTimes--;
			if(CoolDownUltimateSkill != 0) CoolDownUltimateSkill--;
			if(CoolDownFire !=0 )CoolDownFire--;
			if(CoolDownBarrier != 0 )CoolDownBarrier--;
			if(CoolDownSpeed != 0) {
				CoolDownSpeed--;
				hero.setSpeed(8);
			}
			
			else {hero.setSpeed(InitialSpeed); hero.setUltiOn(false);}
			gamescreen.setCoolDown(CoolDownFire, CoolDownBarrier, CoolDownUltimateSkill);
			RenderableHolder.getinstance().updatePos(control);
	}
	public void updateSong() {
		if (soundgame.isPlaying()==false && !isOver && !isStateFive) playSong();
		setStateDetails();
		if (bosssound.isPlaying() == false && AddedBoss && !toMainMenu) bosssound.play();
	}
	public void updateState() {
		if (hero.getLv()==3 && hero.isLvthreebefore()==false && !isOver) {
			hero.setLvthreebefore(true);
			gamewindowanimation.stop();
			StageWindow.draw(0, 0, gc);
			stageON = true;
		}
		
		if (hero.getLv()==4 && hero.isLvfourbefore()==false && !isOver) {
			hero.setLvfourbefore(true);
			gamewindowanimation.stop();
			StageWindow.draw(1, 0, gc);
			stageON = true;
		}
		if (hero.getLv()==5 && hero.isLvfivebefore()==false && !isOver) {
			hero.setLvfivebefore(true);
			gamewindowanimation.stop();
			StageWindow.draw(2, 0, gc);
			stageON = true;
		}
		if (hero.getLv()==6 && hero.isLvsixbefore()==false && !isOver) {
			hero.setLvsixbefore(true);
			gamewindowanimation.stop();
			StageWindow.draw(3, 0, gc);
			stageON = true;					
		}
		if (hero.getLv()==7 && hero.isLvsevenbefore()==false && !isOver) {
			hero.setLvsevenbefore(true);
			gamewindowanimation.stop();
			StageWindow.draw(4, 1, gc);
			stopAllSong();
			addBoss();
			
			isStateFive = true;
			stageON = true;
			
		}
	}
	public void isGameEnd() {
		if (hero.getLife()<=0) {
			RenderableHolder.getinstance().clearList();
			gamewindowanimation.stop();
			GameOver.startAnimation(gc);
			GameOver.setFinished(false);
			isOver = true;
			stopAllSong();
		}
		if (AddedBoss && boss.isDead()) {
			RenderableHolder.getinstance().clearList();
			gamewindowanimation.stop();
			stopAllSong();
			toMainMenu = true;
			winnersound.play();
			GameWinner.startAnimation(gc);
		}
	}
	public void addAll() {
		addGameScreen();
		addHero();
		addMonster();	
	}
	public void addMonster() {
		monster = new Monster(hero);
		RenderableHolder.getinstance().add(monster);
	}
	public void addBoss() {
		boss = new Boss();
		RenderableHolder.getinstance().add(boss);
		AddedBoss = true;
	}
	public void addItem() {
		Item item = new Heart();
		RenderableHolder.getinstance().add(item);
	}
	public void addHero() {
		hero = new Hero();
		RenderableHolder.getinstance().add(hero);
	}
	public void addGameScreen() {
		gamescreen = new GameScreen();
		RenderableHolder.getinstance().add(gamescreen);
	}
	public void playSong() {
		int x = rand.nextInt(soundgameURL.length);
		soundgame = new AudioClip(ClassLoader.getSystemResource(soundgameURL[x]).toString());
		soundgame.play();
	}
	public void stopAllSong() {
		soundgame.stop();
		bosssound.stop();
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
	public void setStateDetails() {
		if(hero.getLv() ==3) {
			Monster.setSpeed(1.5);
			hero.setSpeed(5);
			InitialSpeed = 5;
		}
		if(hero.getLv() == 4) {
			Monster.setSpeed(2);
		}
		if(hero.getLv() == 5) {
			Monster.setSpeed(2.5);
			hero.setSpeed(6);
			InitialSpeed = 6;
		}
		if(hero.getLv() == 6) {
			monsteramount =2;
			Monster.setSpeed(1.5);
		}
	}

}
	