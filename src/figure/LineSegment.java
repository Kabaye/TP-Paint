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
 * @created 18-фев-2020 21:18:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LineSegment extends Figure1D {
    private Point secondPoint;

    public LineSegment(Point referencePoint, Point secondPoint, Color borderColor) {
        super(referencePoint, borderColor);
        this.secondPoint = secondPoint;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(getBorderColor());
        graphics2D.setStroke(new BasicStroke(5));
        calculateSecondPoint();
        graphics2D.drawLine(getReferencePoint().x, getReferencePoint().y, getSecondPoint().x, getSecondPoint().y);
    }

    @Override
    public void move(Point newPoint) {
        secondPoint.setLocation(secondPoint.x + newPoint.x - getReferencePoint().x,
                secondPoint.y + newPoint.y - getReferencePoint().y);
        super.move(newPoint);
    }

    protected void calculateSecondPoint(){
    }

    @Override
    public boolean nextForRemoving() {
        return false;
    }
}//end figure.LineSegment