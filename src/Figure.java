import lombok.Data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:42
 */
@Data
public abstract class Figure {

    private Point referencePoint;
    private Color borderColor;

    public Figure(Point referencePoint, Color borderColor) {
        this.referencePoint = referencePoint;
        this.borderColor = borderColor;
    }

    public abstract void draw(Graphics2D graphics2D);

    public abstract void move(Point newPoint);
}//end Figure