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
	public String[] image = {"bg1.jpg","bg2.jpg","bg3.jpg","bg4.jpg"};
	public int imageorder = 0;
	private int frame = 0;
//	public AudioClip audio;
	
	public StartWindow(Stage primaryStage) {
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
//		audio = new AudioClip("Hello.mp3");
//		audio.play();
//		audio.setCycleCount(MediaPlayer.INDEFINITE);
	}
	public void drawStartWindow() {
		StackPane root = new StackPane();
		VBox vbox = new VBox();
		VBox title = new VBox();
		title.setSpacing(70);
		title.setAlignment(Pos.CENTER);
		root.setPrefSize(800, 450);
		vbox.setAlignment(Pos.CENTER);
		Label gamename = new Label("CLOUDY PEWPEW");
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
		primaryStage.setTitle("PANGandEarthGamming not toei but toei is noob la");
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
		Image background = new Image(image[imageorder]);
		gc.drawImage(background, 0,0);
		imageorder++;
		if (imageorder==image.length) imageorder=0;
		
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
