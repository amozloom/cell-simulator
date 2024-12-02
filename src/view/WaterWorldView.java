//package view;
//
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import model.WaterWorldGrid;
//
///* 
// * 
// * @author Reed Gatfield
// * 
// * This class represents the WaterWorld view.
// * 
// * */
//
//public class WaterWorldView extends SimulationView {
//	private TextField fishChanceField;
//	private TextField sharkChanceField;
//	private TextField fishBreedTimeField;
//	private TextField sharkBreedTimeField;
//	private TextField sharkStarveTimeField;
//
//	@Override
//	protected void initializeGrid() {
//		grid = new WaterWorldGrid(8, 3);
//	}
//
//	@Override
//	protected HBox setupParameters() {
//		HBox parameters = new HBox(10);
//		parameters.setAlignment(Pos.BASELINE_CENTER);
//
//		rowsField = createTextField("8");
//		colsField = createTextField("3");
//		fishChanceField = createTextField("0.4");
//		sharkChanceField = createTextField("0.1");
//		fishBreedTimeField = createTextField("1");
//		sharkBreedTimeField = createTextField("20");
//		sharkStarveTimeField = createTextField("5");
//
//		parameters.getChildren().addAll(
//				new Label("Rows:"), rowsField,
//				new Label("Cols:"), colsField,
//				new Label("Fish Chance:"), fishChanceField,
//				new Label("Shark Chance:"), sharkChanceField,
//				new Label("Fish Breed:"), fishBreedTimeField,
//				new Label("Shark Breed:"), sharkBreedTimeField,
//				new Label("Shark Starve:"), sharkStarveTimeField
//				);
//
//		return parameters;
//	}
//
//	private TextField createTextField(String defaultValue) {
//		TextField field = new TextField(defaultValue);
//		field.setPrefWidth(50);
//		return field;
//	}
//
//	@Override
//	protected void createNewSimulation() {
//
//		int rows = Integer.parseInt(rowsField.getText());
//		int cols = Integer.parseInt(colsField.getText());
//		grid = new WaterWorldGrid(rows, cols);
//		redraw();
//	}
//
//	@Override
//	protected String getSimulationTitle() {
//		return "Water World Simulation";
//	}
//}