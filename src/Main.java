import javafx.application.Application;
import javafx.stage.Stage;
import model.FireWorldGrid;
import model.NewWaterWorldGrid;
import view.WildfireView;
import view.WaterWorldView;
import java.util.Scanner;

/* 
 * 
 * @author Reed Gatfield
 * 
 * Entry point, queries user which simulation to run.
 * 
 * */

public class Main {
//    @Override
//    public void start(Stage stage) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Choose simulation:");
//        System.out.println("1. Wildfire");
//        System.out.println("2. Water World");
//        
//        int choice = scanner.nextInt();
//        
//        if (choice == 1) {
//            WildfireView fireView = new WildfireView();
//            fireView.start(stage);
//        } else if (choice == 2) {
//            WaterWorldView waterView = new WaterWorldView();
//            waterView.start(stage);
//        }
//        
//        scanner.close();
//    }

    public static void main(String[] args) {
        //launch(args);
        System.out.println("start");
       	NewWaterWorldGrid grid = new NewWaterWorldGrid(3,3);
       	int[][] testingGrid = grid.getCells();
       	int states = 5;
       	
       	while (states > 0) {
       	for (int i = 0; i < testingGrid.length; i++) {
       		for (int j = 0; j < testingGrid[0].length; j++) {
       			//System.out.print(testingGrid[i][j]);
       			if (testingGrid[i][j] == 0) {
       				System.out.print("Edgee ");
       			} else if (testingGrid[i][j] == 1) {
       				System.out.print("Water ");
       			}else if (testingGrid[i][j] == 2) {
       				System.out.print("Fishh ");
       			}else if (testingGrid[i][j] == 3) {
       				System.out.print("Shark ");
       			}
       		}
       		System.out.println();
       	}
       	grid.updateGrid();
       	testingGrid = grid.getCells();
       	states--;
       	
       	System.out.println();
       	System.out.println();
       	System.out.println();
       	}
    }
}