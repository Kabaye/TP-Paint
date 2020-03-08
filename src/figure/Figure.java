package figure;

import lombok.Data;

import java.awt.Color;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:42
 */
@Data
public abstract class Figure implements Drawable {
    private Point referencePoint;
    private Color borderColor;

    public Figure(Point referencePoint, Color borderColor) {
        this.referencePoint = referencePoint;
        this.borderColor = borderColor;
    }

    @Override
    public void move(Point newPoint) {
        referencePoint = newPoint;
    }
}//end figure.Figure