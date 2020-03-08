import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class RegularPolygon extends Polygon {
    public RegularPolygon(Point referencePoint, Color borderColor, Color innerColor, int numOfSides, Point secondPoint) {
        super(new ArrayList<>(), borderColor, innerColor);
    }
}
