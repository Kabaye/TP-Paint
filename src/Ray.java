import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ray extends LineSegment {
	public Ray(Point referencePoint, Point secondPoint, Color borderColor) {
		super(referencePoint, secondPoint, borderColor);
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		super.draw(graphics2D);
	}
}//end Ray