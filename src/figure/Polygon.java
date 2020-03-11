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
public class Polygon extends Figure2D {
    private List<Point> points;

    public Polygon(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points.get(0), borderColor, innerColor, brushSize);
        this.points = points.subList(1, points.size());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(getBrushSize()));
        int[] xCoordinates = getXCoordinates(true);
        int[] yCoordinates = getYCoordinates(true);
        graphics2D.setColor(getInnerColor());
        graphics2D.fillPolygon(xCoordinates, yCoordinates, xCoordinates.length);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolygon(xCoordinates, yCoordinates, xCoordinates.length);
    }

    @Override
    public void move(Point newPoint) {
        points.forEach(point -> point.setLocation(point.x + newPoint.x - getReferencePoint().x,
                point.y + newPoint.y - getReferencePoint().y));
        getReferencePoint().setLocation(newPoint.x, newPoint.y);
    }

    @Override
    public boolean contains(Point point) {
        final int[] xCoordinates = getXCoordinates(true);
        final int[] yCoordinates = getYCoordinates(true);
        return new java.awt.Polygon(xCoordinates, yCoordinates,xCoordinates.length).contains(point);
//        int hits = 0;
//
//        int lastx = points.get(points.size() - 1).x;
//        int lasty = points.get(points.size() - 1).y;
//        int curx, cury;
//
//        // Walk the edges of the polygon
//        for (int i = 0; i < points.size(); lastx = curx, lasty = cury, i++) {
//            curx = points.get(i).x;
//            cury = points.get(i).y;
//
//            if (cury == lasty) {
//                continue;
//            }
//
//            int leftx;
//            if (curx < lastx) {
//                if (point.x >= lastx) {
//                    continue;
//                }
//                leftx = curx;
//            } else {
//                if (point.x >= curx) {
//                    continue;
//                }
//                leftx = lastx;
//            }
//
//            double test1, test2;
//            if (cury < lasty) {
//                if (point.y < cury || point.y >= lasty) {
//                    continue;
//                }
//                if (point.x < leftx) {
//                    hits++;
//                    continue;
//                }
//                test1 = point.x - curx;
//                test2 = point.y - cury;
//            } else {
//                if (point.y < lasty || point.y >= cury) {
//                    continue;
//                }
//                if (point.x < leftx) {
//                    hits++;
//                    continue;
//                }
//                test1 = point.x - lastx;
//                test2 = point.y - lasty;
//            }
//
//            if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
//                hits++;
//            }
//        }
//        return ((hits & 1) != 0);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setFigureVertex(Point figureVertex) {
        points.remove(points.size() - 1);
        points.add(figureVertex);
    }

    protected int[] getXCoordinates(boolean withReferencePoint) {
        return withReferencePoint ? IntStream.concat(IntStream.of(getReferencePoint().x),
                points.stream().mapToInt(point -> point.x)).toArray() :
                getPoints().stream().mapToInt(point -> point.x).toArray();
    }

    protected int[] getYCoordinates(boolean withReferencePoint) {
        return withReferencePoint ? IntStream.concat(IntStream.of(getReferencePoint().y),
                points.stream().mapToInt(point -> point.y)).toArray() :
                getPoints().stream().mapToInt(point -> point.y).toArray();
    }
}//end figure.Polygon