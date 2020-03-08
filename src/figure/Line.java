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
public class Line extends Ray {
    public Line(Point referencePoint, Point secondPoint, Color borderColor) {
        super(referencePoint, secondPoint, borderColor);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
        getSecondPoint().setLocation(getReferencePoint().x * 2 - getReferencePoint().x, getReferencePoint().y * 2 - getReferencePoint().y);
        super.draw(graphics2D);
    }
}//end figure.Line