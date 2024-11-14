import controller.ControlSimulationStates;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Grid;
import view.ColorCells;

public class Main extends Application {

    public final int SIZE = 600;
    public final int FRAMES_PER_SECOND = 10;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public final String TITLE = "Wildfire Simulation";
    public final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;
    private ControlSimulationStates simulationControls;
    private Grid grid;  
    private ColorCells view;  

    @Override
    public void start(Stage stage) {
        // Set up ColorCells and Grid for display and simulation control
        view = new ColorCells();
        grid = new Grid(3,8);
        simulationControls = new ControlSimulationStates();

        // Create and display the scene
        myScene = setupScene(SIZE, SIZE, BACKGROUND, view, grid, grid.getNumCols(), grid.getNumRows());
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // Set up the timeline for simulation steps
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setupScene(int width, int height, Paint background, ColorCells view, Grid grid, int rows, int cols) {
        Group root = view.createRootForDisplay(width, height, rows, cols);
        view.updateDisplay(grid);  // Initial display setup
        return new Scene(root, width, height, background);
    }

    private void step(double elapsedTime) {
        simulationControls.step(grid);  // Update the grid state
        view.updateDisplay(grid);       // Refresh the display with the updated grid
    }

    public static void main(String[] args) {
        launch(args);
    }
}
