import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class FigureCreator {
    private FigureCreator() {

    }

    public static Figure createCircle(Point referencePoint, Color borderColor, Color innerColor, Point firstPoint) {
        return new Circle(referencePoint, firstPoint, borderColor, innerColor);
    }

    public static Figure createEllipse(Point referencePoint, Color borderColor, Color innerColor, Point firstPoint, Point secondPoint) {
        return new Ellipse(referencePoint, firstPoint, secondPoint, borderColor, innerColor);
    }

    public static Figure createLine(Point referencePoint, Color borderColor, Point secondPoint) {
        return new Line(referencePoint, secondPoint, borderColor);
    }

    public static Figure createLineSegment(Point referencePoint, Color borderColor, Point secondPoint) {
        return null;
    }

    public static Figure createPolygon(Color borderColor, Color innerColor, List<Point> points) {
        return null;
    }

    public static Figure createPolyline(Color borderColor, List<LineSegment> lineSegments) {
        return null;
    }

    public static Figure createRay(Point referencePoint, Color borderColor, Point secondPoint) {
        return null;
    }

    public static Figure createRectangle(Color borderColor, Color innerColor, List<Point> points) {
        return null;
    }

    public static Figure createRegularPolygon(Point referencePoint, Color borderColor, Color innerColor, Integer numOfSides) {
        return null;
    }

    public static Figure createRhombus(Color borderColor, Color innerColor, List<Point> points) {
        return null;
    }

    public static Figure createTriangle(Color borderColor, Color innerColor, List<Point> points) {
        return null;
    }
}
