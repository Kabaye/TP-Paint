package utils;



import figure.Circle;
import figure.Drawable;
import figure.Ellipse;
import figure.Line;
import figure.LineSegment;
import figure.Polygon;
import figure.Polyline;
import figure.Ray;
import figure.Rectangle;
import figure.RegularPolygon;
import figure.Rhombus;
import figure.Triangle;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Figures {
    private Figures() {
    }

    public static Drawable createCircle(Point referencePoint, Point firstPoint, Color borderColor, Color innerColor) {
        return new Circle(referencePoint, firstPoint, borderColor, innerColor);
    }

    public static Drawable createEllipse(Point referencePoint, Point firstPoint, Point secondPoint, Color borderColor, Color innerColor) {
        return new Ellipse(referencePoint, firstPoint, secondPoint, borderColor, innerColor);
    }

    public static Drawable createLine(Point referencePoint, Point secondPoint, Color borderColor) {
        return new Line(referencePoint, secondPoint, borderColor);
    }

    public static Drawable createLineSegment(Point referencePoint, Point secondPoint, Color borderColor) {
        return new LineSegment(referencePoint, secondPoint, borderColor);
    }

    public static Drawable createPolygon(List<Point> points, Color borderColor, Color innerColor) {
        return new Polygon(points, borderColor, innerColor);
    }

    public static Drawable createPolyline(List<Point> points, Color borderColor) {
        return new Polyline(points, borderColor);
    }

    public static Drawable createRay(Point referencePoint, Point secondPoint, Color borderColor) {
        return new Ray(referencePoint, secondPoint, borderColor);
    }

    public static Drawable createRectangle(List<Point> points, Color borderColor, Color innerColor) {
        return new Rectangle(points, borderColor, innerColor);
    }

    public static Drawable createRegularPolygon(Point referencePoint, Point secondPoint, Integer numOfSides, Color borderColor, Color innerColor) {
        return new RegularPolygon(referencePoint, secondPoint, numOfSides, borderColor, innerColor);
    }

    public static Drawable createRhombus(List<Point> points, Color borderColor, Color innerColor) {
        return new Rhombus(points, borderColor, innerColor);
    }

    public static Drawable createTriangle(List<Point> points, Color borderColor, Color innerColor) {
        return new Triangle(points, borderColor, innerColor);
    }

}