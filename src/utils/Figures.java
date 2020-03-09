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

    public static Drawable createCircle(Point referencePoint, Point firstPoint, Color borderColor, Color innerColor, int brushSize) {
        return new Circle(referencePoint, firstPoint, borderColor, innerColor, brushSize);
    }

    public static Drawable createEllipse(Point referencePoint, Point firstPoint, Point secondPoint, Color borderColor, Color innerColor, int brushSize) {
        return new Ellipse(referencePoint, firstPoint, secondPoint, borderColor, innerColor, brushSize);
    }

    public static Drawable createLine(Point referencePoint, Point secondPoint, Color borderColor, int brushSize) {
        return new Line(referencePoint, secondPoint, borderColor, brushSize);
    }

    public static Drawable createLineSegment(Point referencePoint, Point secondPoint, Color borderColor, int brushSize) {
        return new LineSegment(referencePoint, secondPoint, borderColor, brushSize);
    }

    public static Drawable createPolygon(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        return new Polygon(points, borderColor, innerColor, brushSize);
    }

    public static Drawable createPolyline(List<Point> points, Color borderColor, int brushSize) {
        return new Polyline(points, borderColor, brushSize);
    }

    public static Drawable createRay(Point referencePoint, Point secondPoint, Color borderColor, int brushSize) {
        return new Ray(referencePoint, secondPoint, borderColor, brushSize);
    }

    public static Drawable createRectangle(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        return new Rectangle(points, borderColor, innerColor, brushSize);
    }

    public static Drawable createRegularPolygon(Point referencePoint, Point secondPoint, Integer numOfSides, Color borderColor, Color innerColor, int brushSize) {
        return new RegularPolygon(referencePoint, secondPoint, numOfSides, borderColor, innerColor, brushSize);
    }

    public static Drawable createRhombus(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        return new Rhombus(points, borderColor, innerColor, brushSize);
    }

    public static Drawable createTriangle(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        return new Triangle(points, borderColor, innerColor, brushSize);
    }

}