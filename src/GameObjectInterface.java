import java.awt.Graphics;
import java.awt.Point;


public interface GameObjectInterface
{
	abstract void Draw(Graphics G);
	abstract void Update(long Tick);
	abstract void MouseClick(Point P);
	abstract void KeyDown(int KeyCode);
	abstract void KeyUp(int KeyCode);
	abstract void KeyPressed(int KeyCode, char KeyChar);
}
