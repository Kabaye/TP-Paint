package figure;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.function.Function;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ray extends LineSegment {
    private Function<Integer, Integer> lineFunc;

    public Ray(Point referencePoint, Point secondPoint, Color borderColor, int brushSize) {
        super(referencePoint, secondPoint, borderColor, brushSize);
    }

    @Override
    public void calculateSecondPoint() {
        int deltaX = getSecondPoint().x - getReferencePoint().x;
        int deltaY = getSecondPoint().y - getReferencePoint().y;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        if (getSecondPoint().x > 0 && getSecondPoint().x < width + 100 &&
                getSecondPoint().y > 0 && getSecondPoint().y < height + 100) {
            if (!(deltaX == 0)) {
                Function<Integer, Integer> lineFunc = x -> (x - getReferencePoint().x) * (deltaY) / deltaX + getReferencePoint().y;
                if (deltaX > 0) {
                    int x = width + 100;
                    int y = lineFunc.apply(x);
                    getSecondPoint().setLocation(x, y);
                } else {
                    int x = -100;
                    int y = lineFunc.apply(x);
                    getSecondPoint().setLocation(x, y);
                }
            } else {
                if (deltaY < 0) {
                    getSecondPoint().y = -100;
                } else if (deltaY > 0) {
                    getSecondPoint().y = height + 100;
                }
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

    @Override
    public boolean contains(Point point) {
        return false;
    }


}//end figure.Ray