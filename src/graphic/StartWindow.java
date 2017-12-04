package graphic;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartWindow{
	private static final Font TEXT_FONT = new Font("Monospace", 50);
	private Stage primaryStage;
	private Canvas bg;
	private AnimationTimer a;
	public int imageorder = 0;
	private int frame = 0;
	private Image background;
	public AudioClip sound;
	
	public StartWindow(Stage primaryStage) {
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource("Hello.mp3").toString());
		sound.play();
		sound.setCycleCount(MediaPlayer.INDEFINITE);
	}
	public void drawStartWindow() {
		StackPane root = new StackPane();
		VBox vbox = new VBox();
		VBox title = new VBox();
		title.setSpacing(70);
		title.setAlignment(Pos.CENTER);
		root.setPrefSize(800, 450);
		vbox.setAlignment(Pos.CENTER);
		Label gamename = new Label("PEWPEW PROGMETH");
		gamename.setTextFill(Color.DEEPPINK);
		gamename.setFont(TEXT_FONT);
		Button start = new Button("START");
		start.setPrefSize(150, 30);
		Button exit = new Button("EXIT");
		exit.setPrefSize(150, 30);
		Button highscore = new Button("HIGHSCORE");
		highscore.setPrefSize(150, 30);
		vbox.setSpacing(15);
		title.getChildren().addAll(gamename,vbox);
		vbox.getChildren().addAll(start,exit,highscore);
		addAction(start);
		setBackground(bg);
		root.getChildren().addAll(bg,title);
		Scene scene = new Scene(root);
		bg.requestFocus();
		primaryStage.setScene(scene);
		primaryStage.setTitle("PEWPEW_Progmeth");
		a = new AnimationTimer() {
			public void handle(long now) {
				frame++;
				if (frame%50==0) setBackground(bg);
			}
		};
		a.start();
	}
	
	public void setBackground(Canvas bg) {
		GraphicsContext gc = bg.getGraphicsContext2D();
		background = new Image("bg.jpg");
		gc.drawImage(background, 0,0);
		
	}
	public void addAction(Button b) {
		b.setOnMouseClicked((MouseEvent) -> {
				a.stop();
//				audio.stop();
				GameWindow game = new GameWindow(primaryStage);
				game.drawGameWinDow();
			
		});
		bg.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		});
	}
	
	
}
