package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Ellipse extends Figure2D {
    private Point firstPoint;
    private Point secondPoint;

    public Ellipse(Point referencePoint, Point firstPoint, Point secondPoint, Color borderColor, Color innerColor) {
        super(referencePoint, borderColor, innerColor);
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public void move(Point newPoint) {

    }

    @Override
    public void draw(Graphics2D graphics2D) {

    }

    @Override
    public boolean nextForRemoving() {
        return getReferencePoint().equals(getFirstPoint()) && getReferencePoint().equals(getSecondPoint());
    }
}//end figure.Ellipse