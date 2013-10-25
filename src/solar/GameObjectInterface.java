package solar;
import java.awt.Graphics2D;

public interface GameObjectInterface
{
	abstract void Draw(Graphics2D G);
	abstract void Update(long Tick);
}
