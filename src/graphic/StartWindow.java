package graphic;

import application.Main;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartWindow{
	private static final Font TITLE_FONT = new Font("Courier New", 70);
	private static final Font MENU_FONT = new Font("Courier New", 25);
	private Stage primaryStage;
	private Canvas bg;
	private AnimationTimer a;
	public int imageorder = 0;
	private int frame = 0;
	private Image background;
<<<<<<< HEAD
||||||| merged common ancestors

	public AudioClip sound;



=======

	public AudioClip sound;
	private int numberselected;
<<<<<<< HEAD
||||||| merged common ancestors
||||||| merged common ancestors
=======



>>>>>>> 5ab2f7bb22be4c88a108cbc44a8a3d1bfc9cb39d
=======
||||||| merged common ancestors
=======



>>>>>>> 5ab2f7bb22be4c88a108cbc44a8a3d1bfc9cb39d
>>>>>>> 39085c35f95523471cbc860dddf79aac67825512
>>>>>>> f92c48b68cea8e678322fab9b5e58e115249812d
	
	public StartWindow(Stage primaryStage) {
		this.primaryStage = primaryStage;
		bg = new Canvas(800,450);
<<<<<<< HEAD
		
		
||||||| merged common ancestors
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource("Hello.mp3").toString());
		sound.play();
		sound.setCycleCount(MediaPlayer.INDEFINITE);
=======
		bg.requestFocus();
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource("Hello.mp3").toString());
		sound.play();
		sound.setCycleCount(MediaPlayer.INDEFINITE);
>>>>>>> 39085c35f95523471cbc860dddf79aac67825512
	}
	public void drawStartWindow() {
		StackPane root = new StackPane();
		VBox vbox = new VBox();
		VBox title = new VBox();
		title.setSpacing(70);
		title.setAlignment(Pos.CENTER_RIGHT);
		root.setPrefSize(800, 450);
		vbox.setAlignment(Pos.CENTER_RIGHT);
		Label gamename = new Label("PEWPEW\nPROGMETH");
		gamename.setTextFill(Color.DEEPPINK);
		gamename.setFont(TITLE_FONT);
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
		bg.requestFocus();
		primaryStage.setTitle("PEWPEW_Progmeth");
<<<<<<< HEAD
//		a = new AnimationTimer() {
//			public void handle(long now) {
//				ActionSelected();
//			}
//		};
//		a.start();
||||||| merged common ancestors
		a = new AnimationTimer() {
			public void handle(long now) {
				ActionSelected();
			}
		};
		a.start();
=======
		a = new AnimationTimer() {
			public void handle(long now) {
				ActionSelected();
			}
		};
		a.start();
		
>>>>>>> f92c48b68cea8e678322fab9b5e58e115249812d
	}
	
	public void setBackground(Canvas bg) {
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, bg.getWidth(), bg.getHeight());
		background = new Image("bg.jpg");
		gc.drawImage(background, 0,0);
		
	}
	public void addAction(Button b) {
		b.setOnMouseClicked((MouseEvent) -> {
<<<<<<< HEAD
//				a.sto                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       p();
//				audio.stop();
||||||| merged common ancestors
				a.stop();
//				audio.stop();
=======
			    
			    a.stop();
			    Main.stopMusic = true;
>>>>>>> f92c48b68cea8e678322fab9b5e58e115249812d
				GameWindow game = new GameWindow(primaryStage);
				game.drawGameWinDow();
				
			
		});
		bg.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				if (numberselected !=0) numberselected++;
			}
		});
	}
//	public VBox setMenu() {
//		VBox v = new VBox();
//		v.setSpacing(15);
//		v.setAlignment(Pos.CENTER_LEFT);
//		Text start = new Text("START THE GAME");
//		start.setFont(MENU_FONT);
//		start.setFill(Color.HOTPINK);
//		Text exit = new Text("Exit");
//		exit.setFont(MENU_FONT);
//		exit.setFill(Color.HOTPINK);
//		v.getChildren().addAll(start,exit);
//		return v;
////		start.setPrefSize(150, 30);
////		Button exit = new Button("EXIT");
////		exit.setPrefSize(150, 30);
////		Button highscore = new Button("HIGHSCORE");
////		highscore.setPrefSize(150, 30);
////		vbox.setSpacing(15);
////		title.getChildren().addAll(gamename,vbox);
////		vbox.getChildren().addAll(start,exit,highscore);
////		addAction(start);
//	}
//	public void drawSelectedColor() {
//		
//	}
//	public void undrawSelectedColor() {
//		
//	}
//	public void ActionSelected() {
//		if (numberselected == 0) {
//			
//		}
//	}
	
	
}
