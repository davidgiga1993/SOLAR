import java.awt.Graphics;
import java.awt.Point;


public interface GameObjectInterface
{
	abstract void Draw(Graphics G);
	abstract void Update(long Tick);
	abstract void MouseClick(Point P);
}
