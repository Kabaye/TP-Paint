package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Circle extends Ellipse {
    public Circle(Point referencePoint, Point pointOnBorder, Color borderColor, Color innerColor, int brushSize) {
        super(referencePoint, pointOnBorder, borderColor, innerColor, brushSize);
    }

    @Override
    protected int getWidth() {
        return getRadius() * 2;
    }

    @Override
    protected int getHeight() {
        return getRadius() * 2;
    }

    private int getRadius() {
        return (int) Math.sqrt((super.getHeight() * super.getHeight() + super.getWidth() * super.getWidth()) / 4f);
    }

    @Override
    protected Point calculateLeftTopPoint() {
        return new Point(getReferencePoint().x - getRadius(), getReferencePoint().y - getRadius());
    }
}//end figure.Circle