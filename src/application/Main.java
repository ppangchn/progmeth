package application;
	
import graphic.StartWindow;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class Main extends Application {
	public  AudioClip sound;
	public static boolean stopMusic =  false;
	@Override
	public void start(Stage primaryStage) {
		StartWindow s = new StartWindow(primaryStage);
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource("Hello.mp3").toString());
		sound.play();
		s.drawStartWindow();
		//primaryStage.setTitle("SEDDFSF");
		primaryStage.show();
		if(stopMusic) sound.stop();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
