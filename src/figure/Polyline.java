package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;

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

    }

    @Override
    public void move(Point newPoint) {

    }

    @Override
    public boolean nextForRemoving() {
        HashSet<Point> uniquePoints = new HashSet<>(points);
        uniquePoints.add(getReferencePoint());
        return uniquePoints.size() <= 1;
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }
}//end figure.Polyline