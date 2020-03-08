import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:43
 */
public class Rectangle extends Polygon {

	public Rectangle(Color borderColor, Color innerColor, List<Point> points) {
		super(points, borderColor, innerColor);
	}
}//end Rectangle