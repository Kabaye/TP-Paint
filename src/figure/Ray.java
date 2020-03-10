package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ray extends LineSegment {
    public Ray(Point referencePoint, Point secondPoint, Color borderColor, int brushSize) {
        super(referencePoint, secondPoint, borderColor, brushSize);
    }

    @Override
    public void calculateSecondPoint() {
        /*int deltaX = getSecondPoint().x - getReferencePoint().x;
        int deltaY = getSecondPoint().y - getReferencePoint().y;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        if (getSecondPoint().x > 0 && getSecondPoint().x < width + 10 &&
                getSecondPoint().y > 0 && getSecondPoint().y < height + 10) {
            if (!(deltaX == 0)) {
                Function<Integer, Integer> lineFuncX = x -> (x - getReferencePoint().x) * (deltaY) / deltaX + getReferencePoint().y;
                Function<Integer, Integer> lineFuncY = y -> (y - getReferencePoint().y) * (deltaX) / deltaY + getReferencePoint().x;
                if (deltaX > 0 && deltaY > 0) {
                    int x = width + 10;
                    int y = height + 10;
                    int resY = lineFuncX.apply(x);
                    int resX = lineFuncY.apply(y);
                    if (resY>resX)
                    getSecondPoint().setLocation(x, resY);
                } else {
                    int x = -width - 10;
                    int y = lineFuncX.apply(x);
                    getSecondPoint().setLocation(x, y);
                }
            } else {
                if (deltaY < 0) {
                    getSecondPoint().y = -height - 10;
                } else if (deltaY > 0) {
                    getSecondPoint().y = height + 10;
                }
            }
        }*/
        if (getSecondPoint().x > 0 && getSecondPoint().x < Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 100 &&
                getSecondPoint().y > 0 && getSecondPoint().y < Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100) {
            Point referencePoint = getReferencePoint();
            double deltaX = getSecondPoint().x - referencePoint.x;
            double deltaY = getSecondPoint().y - referencePoint.y;
            if (deltaX == 0 && deltaY == 0)
                return;
            if (Math.abs(deltaX) < Math.abs(deltaY)) {
                double height;
                if (deltaY < 0)
                    height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * -2;
                else
                    height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2;
                getSecondPoint().setLocation(deltaX / deltaY * (height - referencePoint.y) + referencePoint.x, height);
            } else {
                double width;
                if (deltaX < 0)
                    width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * -2;
                else
                    width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2;
                getSecondPoint().setLocation(width, deltaY / deltaX * (width - referencePoint.x) + referencePoint.y);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (!getReferencePoint().equals(getSecondPoint())) {
            super.draw(graphics2D);
        }
    }

    @Override
    public boolean nextForRemoving() {
        return getReferencePoint().equals(getSecondPoint());
    }
}//end figure.Ray