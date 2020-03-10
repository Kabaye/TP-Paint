package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Polygon extends Figure2D {
    private List<Point> points;

    public Polygon(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points.get(0), borderColor, innerColor, brushSize);
        this.points = points.subList(1, points.size());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(getBrushSize()));
        int[] xCoordinates = getXCoordinates(true);
        int[] yCoordinates = getYCoordinates(true);
        graphics2D.setColor(getInnerColor());
        graphics2D.fillPolygon(xCoordinates, yCoordinates, xCoordinates.length);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolygon(xCoordinates, yCoordinates, xCoordinates.length);
    }

    @Override
    public void move(Point newPoint) {
    }

    @Override
    public boolean nextForRemoving() {
        return false;
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setFigureVertex(Point figureVertex) {
        points.remove(points.size() - 1);
        points.add(figureVertex);
    }

    protected int[] getXCoordinates(boolean withReferencePoint) {
        return withReferencePoint ? IntStream.concat(IntStream.of(getReferencePoint().x),
                points.stream().mapToInt(point -> point.x)).toArray() :
                getPoints().stream().mapToInt(point -> point.x).toArray();
    }

    protected int[] getYCoordinates(boolean withReferencePoint) {
        return withReferencePoint ? IntStream.concat(IntStream.of(getReferencePoint().y),
                points.stream().mapToInt(point -> point.y)).toArray() :
                getPoints().stream().mapToInt(point -> point.y).toArray();
    }
}//end figure.Polygon