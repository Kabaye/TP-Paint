package figure;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Drawable {
    void draw(Graphics2D graphics2D);

    void move(Point newPoint);

    boolean nextForRemoving();

    boolean contains(Point point);
}
