package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import controller.InitializeGridStates;
import model.FireWorldGrid;

/* 
 * 
 * @author Reed Gatfield
 * 
 * This class represents the Wilfire view.
 * 
 * */

public class WildfireView extends SimulationView {
	private TextField burnTimeField;
	private TextField spreadProbField;
	private TextField forestDensityField;
	private TextField burningTreesField;

	@Override
	protected void initializeGrid() {
		InitializeGridStates initialStates = new InitializeGridStates();
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