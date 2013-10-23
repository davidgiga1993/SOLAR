import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class BeispielGameObject extends GameObject // Implement nicht benötigt da bereits un GameObject
{
	private boolean HasClick = false;
	private int XTop = 0;
	
	// Init
	public BeispielGameObject()
	{
		PosY = 100;
		ClickArea = new Rectangle(0, 0, 200, 100); // In diesem Bereich soll ein klick erkannt werden
	}
	
	// Draw wird ~ alle 15 ms ausgeführt und sollte NUR(!) zum zeichnen verwendet werden
	@Override
	public void Draw(Graphics G)
	{
		G.setColor(Color.black);
		G.drawLine(XTop, 0, PosX, PosY);
		
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
		PosX += 2;
		if(PosX > 200)
		{
			PosX = 0;
			XTop += 10;
			if(XTop > 200)
				XTop = 0;
		}
	}

	// Wird ausgeführt wenn auf das Objekt geklickt wurde
	// Dazu muss das Rechteck ClickArea auf ein Bereich gesetzt werden, auf den geklickt werden darf
	@Override
	public void MouseClick(Point P)
	{
		HasClick = !HasClick;
	}

	// Wird ausgeführt wenn eine Taste gedrückt wird
	@Override
	public void KeyDown(int KeyCode)
	{

	}

	// Wird ausgeführt wenn eine Taste losgelassen wird
	@Override
	public void KeyUp(int KeyCode)
	{	
	}

	// Wird ausgeführt wenn eine Taste gedrückt WURDE (runter und hoch)
	@Override
	public void KeyPressed(int KeyCode, char KeyChar)
	{
	}
}
