package objects;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;


public class ObjectStern extends GameObject {
	private int Masse;
	private int Durchmesser;
	private Paint Farbe;
	private Point Position;
	
	public ObjectStern(int Masse, int Durchmesser, Paint Farbe, Point Position) {
		this.Masse = Masse;
		this.Durchmesser = Durchmesser;
		this.Farbe = Farbe;
		this.Position = Position;
	}
	

	@Override
	public void Draw(Graphics2D G) {
		G.setPaint(Farbe);
		G.fillOval(Position.x, Position.y, Durchmesser, Durchmesser);
		
	}

	@Override
	public void Update(long Tick) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void ZoomChanged() {
		// TODO Auto-generated method stub
		
	}

}
