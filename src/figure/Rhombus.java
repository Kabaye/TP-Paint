package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utils.LocalMathUtils;

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
        points.add(new Point(LocalMathUtils.getByUnit(x, figureVertex.x, getReferencePoint().x, LocalMathUtils.Unit.MIN),
                LocalMathUtils.getByUnit(y, figureVertex.y, getReferencePoint().y, LocalMathUtils.Unit.MIDDLE)));
        points.add(new Point(LocalMathUtils.getByUnit(x, figureVertex.x, getReferencePoint().x, LocalMathUtils.Unit.MIDDLE),
                LocalMathUtils.getByUnit(y, figureVertex.y, getReferencePoint().y, LocalMathUtils.Unit.MAX)));
        points.add(new Point(LocalMathUtils.getByUnit(x, figureVertex.x, getReferencePoint().x, LocalMathUtils.Unit.MAX),
                LocalMathUtils.getByUnit(y, figureVertex.y, getReferencePoint().y, LocalMathUtils.Unit.MIDDLE)));
        points.add(new Point(LocalMathUtils.getByUnit(x, figureVertex.x, getReferencePoint().x, LocalMathUtils.Unit.MIDDLE),
                LocalMathUtils.getByUnit(y, figureVertex.y, getReferencePoint().y, LocalMathUtils.Unit.MIN)));
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
        return Math.abs((point.x - getReferencePoint().x) / (getPoints().get(0).x - getPoints().get(2).x)) +
                Math.abs((point.y - getReferencePoint().y) / (getPoints().get(1).y - getPoints().get(3).y)) <= 2;
    }
}//end Rhombus