package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-���-2020 21:18:43
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Rhombus extends Polygon {

    public Rhombus(List<Point> points, Color borderColor, Color innerColor, int brushSize) {
        super(points, borderColor, innerColor, brushSize);
    }
}//end Rhomb