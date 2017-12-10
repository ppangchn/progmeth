package graphic;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartWindow{
	private final Font TITLE_FONT = new Font("AvenueX", 70);
	private final Font CHAT_FONT = new Font("Digital tech", 40);
	private final Font MENU_FONT = new Font("Courier New", 40);
	private final Font SPACE_FONT = new Font("Courier New", 25);
	private final Font RANDOM_FONT = new Font("Courier New",18);
	private Stage primaryStage;
	private Canvas bg;
	private GraphicsContext gc;
	private AnimationTimer spaceanimation;
	private AnimationTimer soundanimation;
	private int framebg = 0;
	private int framespace = 0;
	private int numberselected = 0;
	private boolean isPressedSpace = false;
	private Random rand;
	public Image background;
	public AudioClip soundbg;
	public AudioClip buttonsound = new AudioClip(ClassLoader.getSystemResource("buttonsound.wav").toString());
	public String[] soundURL = {"Hello.mp3","Coward.mp3","JustBeFriend.mp3","TellYourWorld.mp3"};
	
	public StartWindow(Stage primaryStage) {
		rand = new Random();
		int x = rand.nextInt(soundURL.length);
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
		gc = bg.getGraphicsContext2D();
		soundbg = new AudioClip(ClassLoader.getSystemResource(soundURL[x]).toString());
		soundbg.play();
	}
	public void draw(GraphicsContext gc) {
		StackPane root = new StackPane();
		root.setPrefSize(800, 450);
		setBackground();
		setPressSpace();
		addAction();
		root.getChildren().addAll(bg);
		Scene scene = new Scene(root);
		bg.requestFocus();
		primaryStage.setScene(scene);

		primaryStage.setTitle("PEWPEW_Progmeth");

		spaceanimation = new AnimationTimer() {
			public void handle(long now) {
				if (framebg == 50) {
					setBackground();
				}
				if (framespace == 110) {
					setPressSpace();
					framebg=0;
					framespace=0;
				}
				framebg++;
				framespace++;
			}
			
		};
		spaceanimation.start();
		soundanimation = new AnimationTimer() {
			public void handle(long now) {
				if (soundbg.isPlaying()==false) playSong();
			}
		};
		soundanimation.start();
	}
	public void setBackground() {
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, bg.getWidth(), bg.getHeight());
		background = new Image("bg.jpg");
		gc.drawImage(background, 0,0);
		gc.setFill(Color.LIGHTSLATEGREY);
		gc.setFont(TITLE_FONT);
		gc.fillText("PEWPEW\nPROGMETH", 400, 100);
		Image wink = new Image("wink.png");
		gc.drawImage(wink, 400, 80);
		setRandomMusicSentence();
		
	}
	public void addAction() {
		bg.setOnKeyPressed((KeyEvent) -> {
			if (isPressedSpace) {
				if (KeyEvent.getCode() == KeyCode.UP) {
					if (numberselected !=0) {buttonsound.play() ;numberselected--;}
					drawSelectedColor();
				}
				if (KeyEvent.getCode() == KeyCode.DOWN) {
					if (numberselected!=1) {buttonsound.play();numberselected++;}
					drawSelectedColor();
				}
				if (KeyEvent.getCode() == KeyCode.SPACE) {
						if (numberselected==0) {
							GameWindow game = new GameWindow(primaryStage);
							game.drawGameWinDow();
							spaceanimation.stop();
							soundanimation.stop();
							soundbg.stop();
						}
						if (numberselected==1) {
							Platform.exit();
						}
						
				}
				
			}
			
			if (KeyEvent.getCode() == KeyCode.R) {
				soundbg.stop();
				playSong();
			}
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (isPressedSpace) {
					gc.setFill(Color.DODGERBLUE);
					gc.setStroke(Color.BLACK);
					gc.setFont(CHAT_FONT);
					gc.strokeText("PRESS SPACE\nTO INTERACT", 80, 350);
					gc.fillText("PRESS SPACE\nTO INTERACT", 80, 350);
				}
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				isPressedSpace = true;
				spaceanimation.stop();
				drawSelectedColor();
			}
			if (KeyEvent.getCode() == KeyCode.M) {
//				muteSong();
			}
		});
	}
	public void setPressSpace() {
		gc.setFill(Color.DIMGREY);
		gc.setFont(SPACE_FONT);
		gc.fillText("  Press Space to\nEnter the Main Menu", 440, 270); //508 270
		
	}
	public void setRandomMusicSentence() {
		gc.setFill(Color.DIMGREY);
		gc.setFont(RANDOM_FONT);
		gc.fillText("Press R to random the music", 500, 20); //508 270
	}
	public void setMenu() {
		setStart();
		setExit();
	}
	public void setStart() {
		gc.setFill(Color.GREY);
		gc.setFont(MENU_FONT);
		gc.fillText("START", 515, 260);
	}
	public void setExit() {
		gc.setFill(Color.GREY);
		gc.setFont(MENU_FONT);
		gc.fillText("EXIT", 525, 320);
	}
	public void drawSelectedColor() {
		setBackground();
		setMenu();
		if (numberselected==0) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(500, 223, 150, 50);
		}
		if (numberselected==1) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(506, 283, 130, 50);
		}
	}
	public void playSong() {
		int x = rand.nextInt(soundURL.length);
		soundbg = new AudioClip(ClassLoader.getSystemResource(soundURL[x]).toString());
		soundbg.play();
	}
	public void startAnimation() {
		draw(gc);
		
	}
	
}
