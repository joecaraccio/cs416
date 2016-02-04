package src;

/**
 * Created by Joe on 2/3/2016.
 */
import java.awt.geom.Point2D;

public interface Draggable {
    void setDraggable(boolean var1);

    boolean isDraggable();

    boolean contains(Point2D var1);

    void moveBy(int var1, int var2);
}
