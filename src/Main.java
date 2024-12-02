import javafx.application.Application;
import javafx.stage.Stage;
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

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose simulation:");
        System.out.println("1. Wildfire");
        System.out.println("2. Water World");
        
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            WildfireView fireView = new WildfireView();
            fireView.start(stage);
        } else if (choice == 2) {
            WaterWorldView waterView = new WaterWorldView();
            waterView.start(stage);
        }
        
        scanner.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}