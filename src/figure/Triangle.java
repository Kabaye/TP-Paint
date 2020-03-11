package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:43
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Triangle extends Polygon {
    public Triangle(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points, borderColor, innerColor, brushSize);
    }

    @Override
    public void setFigureVertex(Point figureVertex) {
        super.setFigureVertex(figureVertex);
    }

//    @Override
//    public boolean contains(Point point) {
////        if (getPoints().size() == 2) {
////            Point p1 = getReferencePoint();
////            Point p2 = getPoints().get(0);
////            Point p3 = getPoints().get(1);
////
////            int var0 = (p1.x - point.x) * (p2.y - p1.y) - (p2.x - p1.x) * (p1.y - point.y);
////            int var1 = (p2.x - point.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - point.y);
////            int var2 = (p3.x - point.x) * (p1.y - p3.y) - (p1.x - p3.x) * (p3.y - point.y);
////            return (var0 < 0 && var1 < 0 && var2 < 0) || (var0 == 0) || (var1 == 0) || (var2 == 0);
////        }
////        return false;
//    }
}//end figure.Triangle