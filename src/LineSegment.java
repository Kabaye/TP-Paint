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
public class LineSegment extends Figure1D {
	private Point secondPoint;

	public LineSegment(Point referencePoint, Point secondPoint, Color borderColor) {
		super(referencePoint, borderColor);
		this.secondPoint = secondPoint;
	}

	@Override
	public void draw(Graphics2D graphics2D) {

	}

	@Override
	public void move(Point newPoint) {

	}
}//end LineSegment