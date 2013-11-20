package solar;

import java.awt.Point;
import java.awt.event.MouseWheelEvent;

public interface MouseMoveInterface
{
    abstract boolean MouseMove(Point P);

    abstract boolean MouseDown(Point P);

    abstract boolean MouseRelease(Point P);
    
    abstract boolean MouseWheelMoved(MouseWheelEvent event);
}
