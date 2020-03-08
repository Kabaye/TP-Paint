import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:43
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Rhombus extends Polygon {

	public Rhombus(Color borderColor, Color innerColor, List<Point> points) {
		super(points, borderColor, innerColor);
	}
}//end Rhomb