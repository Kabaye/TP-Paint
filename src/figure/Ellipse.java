package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-���-2020 21:18:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Ellipse extends Figure2D {
    private Point pointOnBorder;

    public Ellipse(Point referencePoint, Point pointOnBorder, Color borderColor, Color innerColor, int brushSize) {
        super(referencePoint, borderColor, innerColor, brushSize);
        this.pointOnBorder = pointOnBorder;
    }

    @Override
    public void move(Point newPoint) {
        pointOnBorder.setLocation(pointOnBorder.x + newPoint.x - getReferencePoint().x, pointOnBorder.y + newPoint.y - getReferencePoint().y);
        getReferencePoint().setLocation(newPoint);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Point leftTopPoint = calculateLeftTopPoint();
        int width = getWidth();
        int height = getHeight();

        graphics2D.setColor(getInnerColor());
        graphics2D.fillOval(leftTopPoint.x, leftTopPoint.y, width, height);
        graphics2D.setColor(getBorderColor());
        graphics2D.setStroke(new BasicStroke(getBrushSize()));
        graphics2D.drawOval(leftTopPoint.x, leftTopPoint.y, width, height);
    }

    protected Point calculateLeftTopPoint() {
        Point diagPoint = new Point(2 * getReferencePoint().x - pointOnBorder.x, 2 * getReferencePoint().y - pointOnBorder.y);
        return new Point(Math.min(pointOnBorder.x, diagPoint.x), Math.min(pointOnBorder.y, diagPoint.y));
    }

    protected int getWidth() {
        return Math.abs(2 * (getReferencePoint().x - pointOnBorder.x));
    }

    protected int getHeight() {
        return Math.abs(2 * (getReferencePoint().y - pointOnBorder.y));
    }

    @Override
    public boolean contains(Point point) {
        double alpha = (double) (point.x - getReferencePoint().x) / getWidth() * 2;
        double beta = (double) (point.y - getReferencePoint().y) / getHeight() * 2;
        return alpha * alpha + beta * beta < 1;
    }
}//end figure.Ellipse