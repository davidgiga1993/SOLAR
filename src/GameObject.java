import java.awt.Point;
import java.awt.Rectangle;


public abstract class GameObject implements GameObjectInterface
{
	public Point Position = new Point(0,0);
	public Rectangle ClickArea;
}
