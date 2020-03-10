package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularPolygon extends Polygon {
    private int numOfSides;
    private float angle;

    public RegularPolygon(Point referencePoint, Point figureVertex, int numOfSides, Color borderColor, Color innerColor, int brushSize) {
        super(Arrays.asList(referencePoint, figureVertex), borderColor, innerColor, brushSize);
        this.numOfSides = numOfSides;
        this.angle = 360F / numOfSides;
    }

    public void setFigureVertex(Point secondPoint) {
        calculatePoints(secondPoint);
    }

    private void calculatePoints(Point secondPoint) {
        ArrayList<Point> points = new ArrayList<>(numOfSides + 1);
        double radius = Math.sqrt((getReferencePoint().x - secondPoint.x) * (getReferencePoint().x - secondPoint.x)
                + (getReferencePoint().y - secondPoint.y) * (getReferencePoint().y - secondPoint.y));

        double temp = Math.asin((getReferencePoint().y - secondPoint.y) / radius) * 180 / Math.PI;
        temp = secondPoint.x < getReferencePoint().x ? 180.0 - temp : temp;
        for (int i = 0; i < numOfSides; i++) {
            points.add(new Point(getReferencePoint().x + (int) (Math.cos(temp / 180 * Math.PI) * radius),
                    getReferencePoint().y - (int) (Math.sin(temp / 180 * Math.PI) * radius)));
            temp = temp + angle;
        }
        setPoints(points);
    }

    protected int[] getXCoordinates() {
        return getPoints().stream().mapToInt(point -> point.x)
                .toArray();
    }

    protected int[] getYCoordinates() {
        return getPoints().stream().mapToInt(point -> point.y)
                .toArray();
    }
}
