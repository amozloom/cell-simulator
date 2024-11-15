package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.Grid;

public class ColorCells {
    private final int CELL_SIZE = 40;
    private Group root;
    
    public Group createRootForDisplay(int width, int height, int numCols, int numRows) {
        root = new Group();
        return root;
    }

    public void updateDisplay(Grid grid) {
        root.getChildren().clear(); // Clear existing cells to refresh the display

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Cell cell = grid.getCell(row, col);
                Rectangle rect = new Rectangle(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setFill(cell.getCellColor());
                rect.setStroke(Color.BLACK);       
                rect.setStrokeWidth(0.5);   
                root.getChildren().add(rect);
            }
        }
    }

}