package graphic;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
	private int framebg = 0;
	private int framespace = 0;
	private Image background;
	public AudioClip sound;
	private int numberselected;
	public StartWindow(Stage primaryStage) {
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
		gc = bg.getGraphicsContext2D();
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource("Hello.mp3").toString());
		sound.play();
		sound.setCycleCount(MediaPlayer.INDEFINITE);
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
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				if (numberselected !=0) numberselected--;
				drawSelectedColor();
				undrawSelectedColor();
			}
			if (KeyEvent.getCode() == KeyCode.DOWN) {
				if (numberselected!=2) numberselected++;
				drawSelectedColor();
				undrawSelectedColor();
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
					Main.stopMusic = true;
					GameWindow game = new GameWindow(primaryStage);
					game.drawGameWinDow();
			}
			if (KeyEvent.getCode() == KeyCode.SPACE) {
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
			gc.strokeRect(500, 283, 130, 50);
		}
		if (numberselected==2) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(460, 343, 250, 50);
		}
	}
	public void undrawSelectedColor() {
		
	}
	public void ActionSelected() {
		if (numberselected == 0) {
			
		}
	}
	
	
}
