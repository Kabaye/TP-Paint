package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:43
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Rhombus extends Polygon {

    public Rhombus(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points, borderColor, innerColor, brushSize);
    }

    private void calculatePoints(Point figureVertex) {
        ArrayList<Point> points = new ArrayList<>();
        int x = 2 * getReferencePoint().x - figureVertex.x;
        int y = 2 * getReferencePoint().y - figureVertex.y;
        Point diagonalFigureVertex = new Point(x, y);
        points.add(new Point(diagonalFigureVertex.x, getReferencePoint().y));
        points.add(new Point(getReferencePoint().x, diagonalFigureVertex.y));
        points.add(new Point(figureVertex.x, getReferencePoint().y));
        points.add(new Point(getReferencePoint().x, figureVertex.y));
        setPoints(points);
    }

    public void setFigureVertex(Point figureVertex) {
        calculatePoints(figureVertex);
    }

    @Override
    protected int[] getXCoordinates(boolean withReferencePoint) {
        return super.getXCoordinates(false);
    }

    @Override
    protected int[] getYCoordinates(boolean withReferencePoint) {
        return super.getYCoordinates(false);
    }
}//end Rhombus