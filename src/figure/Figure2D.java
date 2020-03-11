package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:24:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Figure2D extends Figure {
    private Color innerColor;

    public Figure2D(Point referencePoint, Color borderColor, Color innerColor, int brushSize) {
        super(referencePoint, borderColor, brushSize);
        this.innerColor = innerColor;
    }

    @Override
    public boolean nextForRemoving() {
        return false;
    }
}//end figure.Figure2D