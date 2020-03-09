package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularPolygon extends Polygon {
    public RegularPolygon(Point referencePoint, Point secondPoint, int numOfSides, Color borderColor, Color innerColor, int brushSize) {
        super(new ArrayList<>(), borderColor, innerColor, brushSize);
    }
}
