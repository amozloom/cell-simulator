import javafx.application.Application;
import javafx.stage.Stage;
import view.WildfireView;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        WildfireView view = new WildfireView();
        view.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}