package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.FireWorldGrid;
import model.Grid;

import java.awt.Button;

import controller.ColorCellsForWildfire;
import controller.ControlSimulationStates;

import controller.InitializeGridStates;
import model.FireWorldGrid;

/* 
 * 
 * @author Reed Gatfield, Quincy Oldland
 * 
 * This class represents the Wilfire view.
 * 
 * */


public class WildfireView extends SimulationView {

	//GUI
	private final int MILLISECOND_DELAY = 2000;
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 600;
	
	//Inputs
	private TextField rowsField;
	private TextField colsField;

	private TextField burnTimeField;
	private TextField spreadProbField;
	private TextField forestDensityField;
	private TextField burningTreesField;

	@Override
	protected void initializeGrid() {
		InitializeGridStates initialStates = new InitializeGridStates();

		simulationControls = new ControlSimulationStates();
		colorCells = new ColorCellsForWildfire();
		//Initialize grid with defaults.

		grid = new FireWorldGrid(initialStates.getNumberOfRows(),
				initialStates.getNumberOfColumns(),
				initialStates.getBurnTime(),
				initialStates.getSpreadProbability(),
				initialStates.getForestDensity(),
				initialStates.getNumberOfBurningTrees());
	}

	@Override
	protected HBox setupParameters() {
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

	@Override
	protected void createNewSimulation() {
		int rows = Integer.parseInt(rowsField.getText());
		int cols = Integer.parseInt(colsField.getText());
		int burnTime = Integer.parseInt(burnTimeField.getText());
		double spreadProb = Double.parseDouble(spreadProbField.getText());
		double forestDensity = Double.parseDouble(forestDensityField.getText());
		int burningTrees = Integer.parseInt(burningTreesField.getText());

		grid = new FireWorldGrid(rows, cols, burnTime, spreadProb, forestDensity, burningTrees);
		redraw();
	}

	@Override
	protected String getSimulationTitle() {
		return "Wildfire Simulation";
	}
}
