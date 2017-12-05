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
	private static final Font TITLE_FONT = new Font("Courier New", 70);
	private static final Font MENU_FONT = new Font("Courier New", 50);
	private Stage primaryStage;
	private Canvas bg;
	private GraphicsContext gc;
	private AnimationTimer a;
	public int imageorder = 0;
	private int frame = 0;
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
		bg.requestFocus();
	}
	public void drawStartWindow() {
		StackPane root = new StackPane();
//		root.setLayoutX(40);
//		root.setLayoutY(40);
//		VBox title = new VBox();
//		title.setVgrow(bg, Priority.ALWAYS);
//		title.setPadding(new Insets(40,40,40,100));
//		title.setSpacing(70);
//		title.setAlignment(Pos.TOP_RIGHT);
		root.setPrefSize(800, 450);
//		Label gamename = new Label("PEWPEW\nPROGMETH");
//		gamename.setTextFill(Color.DEEPPINK);
//		gamename.setFont(TITLE_FONT);
//		title.getChildren().addAll(gamename);
		setBackground();
		setPressSpace();
		root.getChildren().addAll(bg);
		Scene scene = new Scene(root);
		bg.requestFocus();
		primaryStage.setScene(scene);
		bg.requestFocus();
		primaryStage.setTitle("PEWPEW_aaaaProgmeth");
	}
	
	public void setBackground() {
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, bg.getWidth(), bg.getHeight());
		background = new Image("bg.jpg");
		gc.drawImage(background, 0,0);
		gc.fillText("PEWPEW\nPROGMETH", 600, 100);
		
	}
	public void addAction(Button b) {
		b.setOnMouseClicked((MouseEvent) -> {
			    Main.stopMusic = true;
				GameWindow game = new GameWindow(primaryStage);
				game.drawGameWinDow();
				
			
		});
		bg.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.SPACE) {
				setBackground();
				setMenu();
				//disable space and go to menu
			}
			if (KeyEvent.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				if (numberselected !=0) numberselected--;
			}
			if (KeyEvent.getCode() == KeyCode.DOWN) {
				if (numberselected!=2) numberselected++;
			}
		});
	}
	public void setPressSpace() {
		gc.setFill(Color.DIMGREY);
		gc.setFont(MENU_FONT);
		gc.fillText("Press Space to Enter the Main Menu", 50	, 50); //508 270
	}
	public void setMenu() {
		setStart();
		setExit();
	}
	public void setStart() {
		Image start = new Image("start.png");
		gc.drawImage(start, 508, 270);
	}
	public void setExit() {
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.HOTPINK);
		gc.setFont(MENU_FONT);
		gc.fillText("EXIT", 520, 350);
	}
	
	public void drawSelectedColor(GraphicsContext gc) {
		setBackground();
		if (numberselected==0) {
			gc.setFill(Color.INDIANRED);
			gc.fillRect(500, 265, 70, 80);
			setStart();
		}
	}
	public void undrawSelectedColor() {
		
	}
	public void ActionSelected() {
		if (numberselected == 0) {
			
		}
	}
	
	
}
