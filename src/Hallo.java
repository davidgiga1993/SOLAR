import java.awt.Graphics;
import java.awt.Point;


public class Hallo extends GameObject implements GameObjectInterface 
{	
	public Hallo()
	{
		Position.y = 100;
	}
	@Override
	public void Draw(Graphics G)
	{
		G.drawLine(0, 0, Position.x, Position.y);
		
	}

	@Override
	public void Update(long Tick)
	{
		Position.x++;
		if(Position.x > 200)
			Position.x = 0;
	}

	@Override
	public void MouseClick(Point P)
	{
		// TODO Auto-generated method stub		
	}
}
