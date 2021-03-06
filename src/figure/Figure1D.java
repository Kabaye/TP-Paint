package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Figure1D extends Figure {
	public Figure1D(Point referencePoint, Color borderColor, int brushSize) {
		super(referencePoint, borderColor, brushSize);
	}
}//end figure.Figure1D