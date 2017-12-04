package main;

import graphic.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		StartWindow sw = new StartWindow(primaryStage);
		sw.drawStartWindow();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
