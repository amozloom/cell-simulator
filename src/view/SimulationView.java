package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.FireWorldGrid;
import model.Grid;
import controller.ColorCellsForWildfire;
import controller.ControlSimulationStates;

/* 
 * 
 * @author Reed Gatfield, Quincy Oldland
 * 
 * Abstract class for simulation views.
 * 
 * */

public abstract class SimulationView extends Application {
    //GUI
    protected final int MILLISECOND_DELAY = 2000;
    protected final int WINDOW_WIDTH = 800;
    protected final int WINDOW_HEIGHT = 600;
    
    //FX and Simulation
    protected Scene myScene;
    protected boolean paused = true;
    protected Button pauseButton;
    protected ColorCellsForWildfire colorCells;
    protected Grid grid;
    protected ControlSimulationStates simulationControls;
    
    //Basic inputs all simulations need
    protected TextField rowsField;
    protected TextField colsField;
    
    public SimulationView() {
    	initializeGrid();
    }
    

    @Override
    public void start(Stage stage) {
        simulationControls = new ControlSimulationStates();
        colorCells = new ColorCellsForWildfire();
        initializeGrid();  // Each subclass defines this

        myScene = setupScene();
        stage.setScene(myScene);
        stage.setTitle(getSimulationTitle());  // Each subclass defines this
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    protected Scene setupScene() {
        Group simulationDisplay = colorCells.createRootForDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, 
                grid.getNumCols(), grid.getNumRows());
        HBox parameters = setupParameters();
        HBox controls = setupControlButtons();
        
        colorCells.updateDisplay(grid);

        VBox root = new VBox(50);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(parameters, simulationDisplay, controls);

        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.AZURE);
    }

    protected HBox setupControlButtons() {
        HBox controls = new HBox();
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setSpacing(10);

        Button newSimButton = new Button("New Simulation");
        newSimButton.setOnAction(value -> createNewSimulation());

        pauseButton = new Button("Start");
        pauseButton.setOnAction(value -> pressPause());

        Button stepButton = new Button("Step");
        stepButton.setOnAction(value -> {
            simulationControls.step(grid);
            redraw();
        });

        controls.getChildren().addAll(newSimButton, pauseButton, stepButton);
        return controls;
    }

    protected void pressPause() {
        this.paused = !this.paused;
        pauseButton.setText(this.paused ? "Start" : "Pause");
    }

    protected void redraw() {
        colorCells.updateDisplay(grid);
    }

    protected void step(double elapsedTime) {
        if (!paused) {
            simulationControls.step(grid);
            redraw();
        }
    }

    // Abstract methods that subclasses must implement
    protected abstract void initializeGrid();
    protected abstract HBox setupParameters();
    protected abstract void createNewSimulation();
    protected abstract String getSimulationTitle();
}