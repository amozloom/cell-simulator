import controller.ControlSimulationStates;
import controller.InitializeCellStates;
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
    public final int FRAMES_PER_SECOND = 1 / 2;         // 0.5 frames per second
    public final int MILLISECOND_DELAY = 2000;           // 2000 milliseconds delay for 2 seconds
    public final double SECOND_DELAY = 2.0;              // 2 seconds per step
    public final String TITLE = "Wildfire Simulation";
    public final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;
    private ControlSimulationStates simulationControls;
    private Grid grid;  
    private ColorCells view;  
    private InitializeCellStates cellStates;

    @Override
    public void start(Stage stage) {
        //get user input for cell variables
    	cellStates = new InitializeCellStates();
    	cellStates.getUserInputForCellState();
    	
    	// Set up ColorCells and Grid for display and simulation control
        view = new ColorCells();
        //
        grid = new Grid(cellStates.getNumberOfRows(), cellStates.getNumberOfColumns(),
        		cellStates.getBurnTime(), cellStates.getSpreadProbability(),
        		cellStates.getForestDensity(), cellStates.getNumberOfBurningTrees());
        
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
        view.updateDisplay(grid);// Initial display setup
        grid.printGrid();
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
