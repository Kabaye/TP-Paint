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
public class Polyline extends Figure1D {
    private List<Point> points;

    public Polyline(List<Point> points, Color borderColor, int brushSize) {
        super(points.get(0), borderColor, brushSize);
        this.points = points.subList(1, points.size());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(getBrushSize()));
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolyline(getXCoordinates(), getYCoordinates(), points.size() + 1);
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

    public void setLastPoint(Point point) {
        points.remove(points.size() - 1);
        points.add(point);
    }

    protected int[] getXCoordinates() {
        return IntStream.concat(IntStream.of(getReferencePoint().x),
                points.stream().mapToInt(point -> point.x))
                .toArray();
    }

    protected int[] getYCoordinates() {
        return IntStream.concat(IntStream.of(getReferencePoint().y),
                points.stream().mapToInt(point -> point.y))
                .toArray();
    }
}//end figure.Polyline