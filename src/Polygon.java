import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

/**
 * @author svkul
 * @version 1.0
 * @created 18-фев-2020 21:18:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class Polygon extends Figure2D {
	private List<Point> points;

	public Polygon(List<Point> points, Color borderColor, Color innerColor) {
		super(points.get(0), borderColor, innerColor);
		this.points = points.subList(1, points.size());
	}

	@Override
	public void draw(Graphics2D graphics2D) {

	}

	@Override
	public void move(Point newPoint) {

	}
}//end Polygon