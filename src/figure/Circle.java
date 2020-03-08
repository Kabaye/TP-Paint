package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;

/**
 * @author svkul
 * @version 1.0
 * @created 18-���-2020 21:18:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Circle extends Ellipse {
	public Circle(Point referencePoint, Point firstPoint, Color borderColor, Color innerColor) {
		super(referencePoint, firstPoint, firstPoint, borderColor, innerColor);
	}
}//end figure.Circle