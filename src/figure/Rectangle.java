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
public class Rectangle extends Polygon {

    public Rectangle(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points, borderColor, innerColor, brushSize);
    }

    private void calculatePoints(Point figureVertex) {
        ArrayList<Point> points = new ArrayList<>();
        int x = 2 * getReferencePoint().x - figureVertex.x;
        int y = 2 * getReferencePoint().y - figureVertex.y;
        points.add(new Point(Math.min(x, figureVertex.x), Math.min(y, figureVertex.y)));
        points.add(new Point(Math.min(x, figureVertex.x), Math.max(y, figureVertex.y)));
        points.add(new Point(Math.max(x, figureVertex.x), Math.max(y, figureVertex.y)));
        points.add(new Point(Math.max(x, figureVertex.x), Math.min(y, figureVertex.y)));
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

    @Override
    public boolean contains(Point point) {
        Point leftTopPoint = getPoints().get(0);
        Point rightBottomPoint = getPoints().get(2);
        return point.x <= rightBottomPoint.x + getBrushSize() / 2 && point.x >= leftTopPoint.x - getBrushSize() / 2 &&
                point.y >= leftTopPoint.y - getBrushSize() / 2 && point.y <= rightBottomPoint.y + getBrushSize() / 2;
    }
}//end figure.Rectangle