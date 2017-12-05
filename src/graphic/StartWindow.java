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
	private static final Font TITLE_FONT = new Font("AvenueX", 70);
	private static final Font MENU_FONT = new Font("Courier New", 40);
	private static final Font SPACE_FONT = new Font("Courier New", 25);
	private Stage primaryStage;
	private Canvas bg;
	private GraphicsContext gc;
	private AnimationTimer a;
	public int imageorder = 0;
	public AudioClip sound;
	public String[] soundURL = {"Hello.mp3","Coward.mp3","JustBeFriend.mp3","TellYourWorld.mp3"};
	public Random rand;
	private int framebg = 0;
	private int framespace = 0;
	private Image background;
	private int numberselected = 0;
	private boolean isPressSpace = false;

	public StartWindow(Stage primaryStage) {
		rand = new Random();
		int x = rand.nextInt(soundURL.length);
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
		gc = bg.getGraphicsContext2D();
		sound = new AudioClip(ClassLoader.getSystemResource(soundURL[x]).toString());
		sound.play();
	}
	public void drawStartWindow() {
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

		a = new AnimationTimer() {
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
//				if (sound.isPlaying()==false) playSong();
			}
			
		};
		a.start();
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
		
	}
	public void addAction() {
		bg.setOnKeyPressed((KeyEvent) -> {
			if (isPressSpace) {
				if (KeyEvent.getCode() == KeyCode.UP) {
					if (numberselected !=0) numberselected--;
					drawSelectedColor();
				}
				if (KeyEvent.getCode() == KeyCode.DOWN) {
					if (numberselected!=2) numberselected++;
					drawSelectedColor();
				}
				if (KeyEvent.getCode() == KeyCode.SPACE) {
						if (numberselected==0) {
							GameWindow game = new GameWindow(primaryStage);
							game.drawGameWinDow();
							a.stop();
							sound.stop();
						}
						if (numberselected==1) {
							Platform.exit();
						}
						if (numberselected==2) {
							
						}
						
				}
			}
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				isPressSpace = true;
				a.stop();
				drawSelectedColor();
			}
		});
	}
	public void setPressSpace() {
		gc.setFill(Color.DIMGREY);
		gc.setFont(SPACE_FONT);
		gc.fillText("  Press Space to\nEnter the Main Menu", 440, 270); //508 270
		
	}
	public void setMenu() {
		setStart();
		setExit();
		setHighScore();
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
	public void setHighScore() {
		gc.setFill(Color.GREY);
		gc.setFont(MENU_FONT);
		gc.fillText("HIGHSCORE",468, 380);
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
		if (numberselected==2) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(450, 343, 250, 50);
		}
	}
	public void playSong() {
		int x = rand.nextInt(soundURL.length);
		sound = new AudioClip(ClassLoader.getSystemResource(soundURL[1]).toString());
		sound.play();
	}
	
	
}
