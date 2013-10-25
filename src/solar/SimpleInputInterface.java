package solar;
import java.awt.Point;


public interface SimpleInputInterface {

	abstract boolean MouseClick(Point P);
	abstract boolean KeyDown(int KeyCode);
	abstract boolean KeyUp(int KeyCode);
	abstract boolean KeyPressed(int KeyCode, char KeyChar);
}
