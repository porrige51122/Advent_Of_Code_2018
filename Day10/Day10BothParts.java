import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Day10BothParts extends Application {
	public static void main(String args[]) throws FileNotFoundException {
		launch(args);
	}

	public void start(Stage stage) throws FileNotFoundException {
		int seconds = 0;
		int[][][] data = new int[356][2][2];
		Scanner sc = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day10\\Input.txt"));
		for (int i = 0; i < data.length; i++) {
			data[i][0][0] = sc.nextInt();
			data[i][1][0] = sc.nextInt();
			data[i][0][1] = sc.nextInt();
			data[i][1][1] = sc.nextInt();
		}
		sc.close();
		int maximumSplit = 500000;
		for (int i = 0; i < 15000; i++) {
			int high = 0;
			int low = 0;
			for (int j = 0; j < data.length; j++) {
				if ((data[j][1][0] + (data[j][1][1] * i)) > high) {
					high = (data[j][1][0] + (data[j][1][1] * i));
				} else if ((data[j][1][0] + (data[j][1][1] * i)) < low) {
					low = (data[j][1][0] + (data[j][1][1] * i));
				}
			}
			if (maximumSplit > (high - low)) {
				maximumSplit = high - low;
				seconds = i;
			}
		}
		System.out.println("After: "+seconds+ " seconds");
		// finds the highest x value or y value, whichever is higher and stores it as
		// the width + 1
		double width = 0;
		for (int i = 0; i < data.length; i++) {
			if ((data[i][0][0] + (data[i][0][1] * seconds)) > width) {
				width = (data[i][0][0] + (data[i][0][1] * seconds));
			}
			if ((data[i][1][0] + (data[i][1][1] * seconds)) > width) {
				width = (data[i][1][0] + (data[i][1][1] * seconds));
			}
		}
		// I want the chart to always be as close to 1000 as possible and always a
		// square
		double scale = 8;
		double offset = -700;
		double sceneWidth = 800;
		Path path = new Path();

		// plots each coordinate
		for (int i = 0; i < data.length; i++) {
			path.getElements().add(new MoveTo((((data[i][0][0] + (data[i][0][1] * seconds)) * scale)) + offset,
					((data[i][1][0] + (data[i][1][1] * seconds)) * scale)+offset));
			path.getElements().add(new LineTo((((data[i][0][0] + (data[i][0][1] * seconds)) * scale)) + offset,
					((data[i][1][0] + (data[i][1][1] * seconds)) * scale) + 5+offset));
			path.getElements().add(new LineTo((((data[i][0][0] + (data[i][0][1] * seconds)) * scale)) + offset,
					((data[i][1][0] + (data[i][1][1] * seconds)) * scale)+offset));
			path.getElements().add(new LineTo((((data[i][0][0] + (data[i][0][1] * seconds)) * scale)) + offset + 5,
					((data[i][1][0] + (data[i][1][1] * seconds)) * scale)+offset));
		}
		Group root = new Group(path);
		Scene scene = new Scene(root, sceneWidth, sceneWidth);
		stage.setTitle("ConvexHull");
		stage.setScene(scene);
		stage.show();
	}

}
