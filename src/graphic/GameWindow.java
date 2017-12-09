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
	private AudioClip soundgame;
	private Random rand;
	private StageWindow stagewindow;
	private StageWindow2 stagewindow2;
	private StageWindow3 stagewindow3;
	private StageWindow4 stagewindow4;
	private StageWindow5 stagewindow5;
	private boolean stageON = false ;
	private AudioClip fire = new AudioClip(ClassLoader.getSystemResource("fire.wav").toString());
	private AudioClip bosssound = new AudioClip(ClassLoader.getSystemResource("BossSound130.wav").toString());
	private int FireTimes = 0;
	private boolean isOver = false;
	private boolean isStateFive = false;
	private boolean AddedBoss = false;
	private int CoolDownUltimateSkill;
	private int CoolDownFire;
	private int CoolDownBarrier;
	private int CoolDownSpeed;
	private Boss boss;
	public String control = "";
	public GraphicsContext gc;
	public Scene scene;
	public Stage primaryStage;
	public char c = 'a';
	public boolean hasBullet = false;
	public int frame = 0;
	public String[] soundgameURL = {"Caramelldansen.mp3","PonPonPon.mp3","Senbonzakura.mp3","Melancholic.mp3","LevanPolkka.mp3"};
	
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
					if (frame%60 ==0 && !isStateFive)addMonster();
					if(frame %300==0)addItem();
				}
				RenderableHolder.getinstance().remove();
				RenderableHolder.getinstance().draw(gc);
				int exp = RenderableHolder.getinstance().setVisible();
				RenderableHolder.getinstance().Collision(hero);
				hero.setExp(hero.getExp()+exp);
				hero.updateLv();
				gamescreen.setHeroData(hero.getLv(),hero.getExp(),hero.getMaxexp(),hero.getLife(),gc);
				gamescreen.setSkillData(hero.getBariaCount(), FireTimes);
				fire();
				if(FireTimes != 0 ) FireTimes--;
				if(CoolDownUltimateSkill != 0) CoolDownUltimateSkill--;
				if(CoolDownFire !=0 )CoolDownFire--;
				if(CoolDownBarrier != 0 )CoolDownBarrier--;
				if(CoolDownSpeed != 0) {
					CoolDownSpeed--;
					hero.setSpeed(6);
				}
				
				else {hero.setSpeed(hero.getSpeed()); hero.isUltiOn =false;}
				gamescreen.setCoolDown(CoolDownFire, CoolDownBarrier, CoolDownUltimateSkill);
				
				RenderableHolder.getinstance().update(control);
				if (soundgame.isPlaying()==false && !isOver && !isStateFive) playSong();

				if (hero.getLv()==3 && hero.isLvthreebefore()==false && !isOver) {
					hero.setLvthreebefore(true);
					gamewindowanimation.stop();
					stagewindow.draw();
					stageON = true;
				}
				
				if (hero.getLv()==4 && hero.isLvfourbefore()==false && !isOver) {
					hero.setLvsixbefore(true);
					gamewindowanimation.stop();
					stagewindow2.draw();
					stageON = true;
				}
				if (hero.getLv()==5 && hero.isLvfivebefore()==false && !isOver) {
					hero.setLvfivebefore(true);
					gamewindowanimation.stop();
					stagewindow3.draw();
					stageON = true;
				}
				if (hero.getLv()==6 && hero.isLvsixbefore()==false && !isOver) {
					hero.setLvsixbefore(true);
					gamewindowanimation.stop();
					stagewindow4.draw();
					stageON = true;					
				}
				if (hero.getLv()==7 && hero.isLvsevenbefore()==false && !isOver) {
					hero.setLvsevenbefore(true);
					soundgame.stop();
					gamewindowanimation.stop();
					bosssound.play();
					addBoss();
					stagewindow5.draw();
					isStateFive = true;
					stageON = true;
					
				}
				
				if (hero.getLife()==0) {
					RenderableHolder.getinstance().clearList();
					gamewindowanimation.stop();
					GameOver.draw(gc);
					isOver = true;
					soundgame.stop();
				}
				if (AddedBoss) {
					if (boss.isDead()) {
						RenderableHolder.getinstance().clearList();
						gamewindowanimation.stop();
						GameWinner.draw(gc);
						isOver = true;
					}
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
				if (!isOver && !stageON) {
					fire.play();
					hero.attack(c);
				}
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (isOver) {
					soundgame.stop();
					GameOver.stopAnimationTimer();
					StartWindow startwindow = new StartWindow(primaryStage);
					startwindow.drawStartWindow();
					}
				if(stageON) {
					if(hero.getLv() >= 3)stagewindow.StopAnimationTimer();
					if(hero.getLv() >= 4)stagewindow2.StopAnimationTimer();
					if(hero.getLv() >= 5)stagewindow3.StopAnimationTimer();
					if(hero.getLv() >= 6)stagewindow4.StopAnimationTimer();
					if(hero.getLv() >= 7)stagewindow5.StopAnimationTimer();
					gamewindowanimation.start();
					stageON = false ;
					
				}
			}
			if(KeyEvent.getCode() == KeyCode.D) {
				if(!isOver) {
				if(CoolDownFire == 0) {FireTimes = 90;
				CoolDownFire = 150;
				}
				}

			}
			if(KeyEvent.getCode() == KeyCode.S) {
				
				if(!isOver) {
				if(CoolDownBarrier == 0) {

					hero.barrier();
					CoolDownBarrier = 300;
				}
				}


			}
			if (KeyEvent.getCode() == KeyCode.F) {
				if(!isOver) {
				if(CoolDownUltimateSkill == 0 ) {
					RenderableHolder.getinstance().UltimateSkill();
					CoolDownUltimateSkill = 90;
					CoolDownSpeed = 150;
					hero.isUltiOn = true;
					}
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
	public void addBoss() {
		boss = new Boss();
		RenderableHolder.getinstance().add(boss);
		AddedBoss = true;
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
	public void setLV() {
		if(hero.getLv() ==3) {
			Monster.speed = 1.2;
			hero.setSpeed(4);
		
		}
		if(hero.getLv() == 4) {
			Monster.speed = 1.4;
			
		}
		if(hero.getLv() == 5) {
			Monster.speed = 1.6;
		
		}
		if(hero.getLv() == 6) {
			Monster.speed = 1.8;
		}
	}

}
	