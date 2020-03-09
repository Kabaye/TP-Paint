package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashSet;
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
        int[] xCoordinates = getXCoordinates();
        int[] yCoordinates = getYCoordinates();
        graphics2D.setColor(getInnerColor());
        graphics2D.fillPolygon(xCoordinates, yCoordinates, points.size() + 1);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolygon(xCoordinates, yCoordinates, points.size() + 1);
    }

    @Override
    public void move(Point newPoint) {

    }

    @Override
    public boolean nextForRemoving() {
        if (points.size() != 1) {
            HashSet<Point> uniquePoints = new HashSet<>(points);
            uniquePoints.add(getReferencePoint());
            return uniquePoints.size() <= 1;
        }
        return false;
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void updateLastPoint(Point point) {
        points.remove(points.size() - 1);
        points.add(point);
    }

    int[] getXCoordinates() {
        return IntStream.concat(IntStream.of(getReferencePoint().x),
                points.stream().mapToInt(point -> point.x))
                .toArray();
    }

    int[] getYCoordinates() {
        return IntStream.concat(IntStream.of(getReferencePoint().y),
                points.stream().mapToInt(point -> point.y))
                .toArray();
    }
}//end figure.Polygon