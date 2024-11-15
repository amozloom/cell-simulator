package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.Grid;

public class ColorCells {

    private final int CELL_SIZE = 40;  // Size of each cell in pixels
    private Group root;  // Group for cell display
    
    public Group createRootForDisplay(int sceneWidth, int sceneHeight, int numCols, int numRows) {
        root = new Group();
        int gridWidth = numCols * CELL_SIZE;
        int gridHeight = numRows * CELL_SIZE;

        // Center horizontally and position towards the top (adjust the Y translation for more control)
        root.setTranslateX((sceneWidth - gridWidth) / 2.0);  // Center horizontally
        root.setTranslateY((sceneHeight - gridHeight) / 5.0); // Position closer to the top
        return root;
    }

    public void updateDisplay(Grid grid) {
        root.getChildren().clear();  // Clear existing cells to refresh the display

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Cell cell = grid.getCell(row, col);
                Rectangle rect = new Rectangle(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setFill(getColorForCell(cell));
                rect.setStroke(Color.BLACK);       
                rect.setStrokeWidth(0.5);   
                root.getChildren().add(rect);
            }
        }
    }

    // Define cell colors based on their type
    private Color getColorForCell(Cell cell) {
        if (cell instanceof model.BurningTreeCell) return Color.RED;
        if (cell instanceof model.LiveTreeCell) return Color.GREEN;
        if (cell instanceof model.BurntDownCell) return Color.YELLOW;
        if (cell instanceof model.EmptyCell) return Color.BROWN;
        return Color.BLACK;  // Default color for EdgeCell or undefined cells
    }
}
