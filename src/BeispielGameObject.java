import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class BeispielGameObject extends GameObject 
{
	private boolean HasClick = false;
	
	// Init
	public BeispielGameObject()
	{
		Position.y = 100;
		ClickArea = new Rectangle(0, 0, 200, 100); // In diesem Bereich soll ein klick erkannt werden
	}
	
	// Draw wird ~ alle 15 ms ausgeführt und sollte NUR(!) zum zeichnen verwendet werden
	@Override
	public void Draw(Graphics G)
	{
		G.setColor(Color.black);
		G.drawLine(0, 0, Position.x, Position.y);
		
		G.setColor(new Color(255, 0, 0, 100));
		G.drawRect(0, 0, 200, 100);
		
		if(HasClick)
		{
			G.drawString("CLICK", 100, 70);
		}
	}

	// Update wird ~ alle 50ms ausgeführt und solle für die Logik benutzt werden
	// Tick gibt die Anzahl abgelaufener Ticks an (1 Tick = ~ 50ms)
	@Override
	public void Update(long Tick)
	{
		Position.x++;
		if(Position.x > 200)
			Position.x = 0;
	}

	// Wird ausgeführt wenn auf das Objekt geklickt wurde
	// Dazu muss das Rechteck ClickArea auf ein Bereich gesetzt werden, auf den geklickt werden darf
	@Override
	public void MouseClick(Point P)
	{
		HasClick = !HasClick;
	}
}
