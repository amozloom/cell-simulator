package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Grid;
import controller.ControlSimulationStates;
import controller.InitializeGridtates;

//Reed Gatfield
//Quincy Oldland
public class WildfireView extends Application {
	//GUI
	private final int MILLISECOND_DELAY = 2000;
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 600;
	//FX and Simulation
	private Scene myScene;
	private boolean paused = true;
	private Button pauseButton;
	private ColorCells colorCells;
	private Grid grid;
	private ControlSimulationStates simulationControls;
	//Inputs
	private TextField rowsField;
	private TextField colsField;
	private TextField burnTimeField;
	private TextField spreadProbField;
	private TextField forestDensityField;
	private TextField burningTreesField;

	@Override
	public void start(Stage stage) {
		//Initialize Simulation
		InitializeGridtates initialStates = new InitializeGridtates();
		simulationControls = new ControlSimulationStates();
		colorCells = new ColorCells();
		//Initialize grid with defaults.
		grid = new Grid(initialStates.getNumberOfRows(),
				initialStates.getNumberOfColumns(),
				initialStates.getBurnTime(),
				initialStates.getSpreadProbability(),
				initialStates.getForestDensity(),
				initialStates.getNumberOfBurningTrees());
		
		//setup the scene
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Wildfire Simulation");
		stage.show();
		//Animation timeline
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	//Sets up GUI components.
	private Scene setupScene() {
		//create a root with a default width and height
		Group simulationDisplay = colorCells.createRootForDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, 
				grid.getNumCols(), grid.getNumRows());
		HBox parameters = setupParameters();
		HBox controls = setupControlButtons();

		colorCells.updateDisplay(grid);
		//Main visual container.
		VBox root = new VBox(50);
		root.setAlignment(Pos.TOP_CENTER);
		root.setPadding(new Insets(10));
		//Add components in order: parameters at top, simulation in middle, controls at bottom
		root.getChildren().addAll(parameters, simulationDisplay, controls);

		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.AZURE);
	}
	//Setup inputs.
	private HBox setupParameters() {
		HBox parameters = new HBox(10);
		parameters.setAlignment(Pos.BASELINE_CENTER);

		rowsField = createTextField("8");
		colsField = createTextField("3");
		burnTimeField = createTextField("1");
		spreadProbField = createTextField("0.4");
		forestDensityField = createTextField("1.0");
		burningTreesField = createTextField("1");

		parameters.getChildren().addAll(
				new Label("Rows:"), rowsField,
				new Label("Cols:"), colsField,
				new Label("Burn Time:"), burnTimeField,
				new Label("Spread Prob:"), spreadProbField,
				new Label("Forest Density:"), forestDensityField,
				new Label("Burning Trees:"), burningTreesField
				);

		return parameters;
	}

	private TextField createTextField(String defaultValue) {
		TextField field = new TextField(defaultValue);
		field.setPrefWidth(50);
		return field;
	}
	
	//setup the buttons gui, calling the methods from controller
	private HBox setupControlButtons() {
		//create an hbox to position buttons at bottom of the screen
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);
		
		//setup the layout for button, on click call the control method
		Button newSimButton = new Button("New Simulation");
		newSimButton.setOnAction(value -> createNewSimulation());
		controls.getChildren().add(newSimButton);

		pauseButton = new Button("Start");
		pauseButton.setOnAction(value -> pressPause());
		controls.getChildren().add(pauseButton);

		Button stepButton = new Button("Step");
		stepButton.setOnAction(value -> {
			simulationControls.step(grid);
			redraw();
		});
		controls.getChildren().add(stepButton);

		return controls;
	}
	
	//create a new simulation with the variables gather from the text boxes
	private void createNewSimulation() {
		int rows = Integer.parseInt(rowsField.getText());
		int cols = Integer.parseInt(colsField.getText());
		int burnTime = Integer.parseInt(burnTimeField.getText());
		double spreadProb = Double.parseDouble(spreadProbField.getText());
		double forestDensity = Double.parseDouble(forestDensityField.getText());
		int burningTrees = Integer.parseInt(burningTreesField.getText());

		grid = new Grid(rows, cols, burnTime, spreadProb, forestDensity, burningTrees);
		redraw();
	}
	
	private void pressPause() {
		this.paused = !this.paused;
		pauseButton.setText(this.paused ? "Start" : "Pause");
	}

	//on every call of update display redraw each of the cells with there correct colors 
	private void redraw() {
		colorCells.updateDisplay(grid);
	}
	
	//on every step, update the grid and color in the cells
	private void step(double elapsedTime) {
		if (!paused) {
			simulationControls.step(grid);
			redraw();
		}
	}
}
