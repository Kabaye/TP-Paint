package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ray extends LineSegment {

    public Ray(Point referencePoint, Point secondPoint, Color borderColor) {
        super(referencePoint, secondPoint, borderColor);
    }

    @Override
    protected void calculateSecondPoint() {
        System.out.println(getSecondPoint());
        int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int windowHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int deltaX = getSecondPoint().x - getReferencePoint().x;
        int deltaY = getSecondPoint().y - getReferencePoint().y;
        if (!(deltaY == 0 && deltaX == 0)) {
            if (getSecondPoint().x > 0 && getSecondPoint().x < windowWidth &&
                    getSecondPoint().y > 0 && getSecondPoint().y < windowHeight) {
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    int width;
                    if (deltaX < 0) {
                        width = -10;
                    } else {
                        width = windowWidth + 10;
                    }
                    getSecondPoint().setLocation(width, deltaY / deltaX * (width - getReferencePoint().x) + getReferencePoint().y);
                } else {
                    int height;
                    if (deltaX < 0) {
                        height = -10;
                    } else {
                        height = windowHeight + 10;
                    }
                    getSecondPoint().setLocation(deltaX / deltaY * (height - getReferencePoint().y) + getReferencePoint().x, height);
                }
            }
        }
        System.out.println(getSecondPoint());
    }

    @Override
    public boolean nextForRemoving() {
        return getReferencePoint().equals(getSecondPoint());
    }
}//end figure.Ray