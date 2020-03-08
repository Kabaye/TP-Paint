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
public class Polyline extends Figure1D {
	private List<LineSegment> lineSegments;

	public Polyline(Point referencePoint, Color borderColor, List<LineSegment> lineSegments) {
		super(referencePoint, borderColor);
		this.lineSegments = lineSegments;
	}

	@Override
	public void draw(Graphics2D graphics2D) {

	}

	@Override
	public void move(Point newPoint) {

	}
}//end Polyline