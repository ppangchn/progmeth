package application;
	
import graphic.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		StartWindow s = new StartWindow(primaryStage);
		s.drawStartWindow();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
