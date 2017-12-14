 package graphic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StageWindow {
	private static AnimationTimer stageanimation;
	public static String[][] stagedialog = {{"Y","o","u"," ","s","o"," ","n","o","o","b"," ","l","a","~"," ","C","a","t","c","h"," ","m","e"," ","i","f"," ","y","o","u"," ","c","a","n"," ",":","P"}
									,{"I","s"," ","t","h","i","s"," ","t","h","e"," ","b","e","s","t"," ","y","o","u"," ","c","a","n"," ","d","o","?"}
									,{"T","i","m","e"," ","t","o"," ","h","a","v","e"," ","f","u","n","~"}
									,{"H","o","w"," ","a","n","n","o","y","y","i","n","g"," ",":","("}
									,{"I"," ","h","a","v","e"," ","b","e","e","n"," ","w","a","i","t","i","n","g"," ","f","o","r"
									," ","y","o","u","."," ","C","o","m","e"," ","a","n","d"," ","g","e","t"," ","m","e","!","\n","H","i","n","t"," ",":"
									," ","O","n","l","y"," ","H","E","A","D","S","H","O","T"," ","c","a","n"," ","k","i","l","l"," ","t","h","e"," ","b","o","s","s"}};
	public static List<Image> boss = new ArrayList<>();
	public static final Font font = Font.loadFont(ClassLoader.getSystemResourceAsStream("CourierNew.ttf"),18);
	public static final Font enter = Font.loadFont(ClassLoader.getSystemResourceAsStream("CourierNew.ttf"),12);
	private static int count = 0;
	public GraphicsContext gc;
	private static boolean isVisible = true;
	public int time = 90;
	private static String sentence = "" ;
	
	
	public StageWindow() {
	}
	public static void draw(int stage,int numboss,GraphicsContext gc) {
		sentence = "";
		count =0;
		setImage();
		stageanimation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (isVisible) {
					Image dialog = new Image("DialogBox.png");
					gc.drawImage(boss.get(numboss), 30, 150);
					gc.drawImage(dialog, 190, 350);
					gc.setFill(Color.ALICEBLUE);
					gc.setFont(font);
					if(count <= stagedialog[stage].length-1) sentence+=stagedialog[stage][count];
					gc.fillText(sentence, 250, 380);
					if (count<=stagedialog[stage].length-1) count++;
					gc.setFont(enter);
				    gc.fillText("Press Enter", 700, 420);
				
					
					
				}
				
			}
		};
		stageanimation.start();
	
	}
	
	public static void StopAnimationTimer() {
		stageanimation.stop();
	}
	public static void setImage() {
		boss.add(new Image("bossblack.png"));
		boss.add(new Image("boss.png"));
	}
}
