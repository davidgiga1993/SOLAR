package objects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;

import solar.SimpleInputInterface;


public class ObjectRaumschiff1 extends GameObject implements SimpleInputInterface{

	private GeneralPath trapez;
	private Point[] position = new Point[4];
	private Point center;
	private int Degree = 0;
	
	public ObjectRaumschiff1 (){
		position[0] = new Point(300,300);
		position[1] = new Point(350,300);
		position[2] = new Point(350,350);
		position[3] = new Point(300,350);
		center = new Point(325,325);
		trapez = new GeneralPath();
		trapez.moveTo(position[0].x, position[0].y);
		trapez.lineTo(position[1].x, position[1].y);
	    trapez.lineTo(position[2].x, position[2].y);
	    trapez.lineTo(position[3].x, position[3].y);
	    trapez.closePath();
	    
	}
	private void Rotate() {
		
		double sin = Math.sin(Math.toRadians(Degree));
        double cos = Math.cos(Math.toRadians(Degree));
        
        if(cos < 0.5 && cos > (-0.5)) cos = 0;
        else if (cos >= 0.5 && cos < 1.5) cos = 1;
        else if (cos >= 1.5) cos = 2;
        else if (cos <= (-0.5) && cos > (-1.5)) cos = 1;
		else cos = 2;
        
        if(sin < 0.5 && sin > (-0.5)) sin = 0;
        else if (sin >= 0.5 && sin < 1.5) sin = 1;
        else if (sin >= 1.5) sin = 2;
        else if (sin <= (-0.5) && sin > (-1.5)) sin = 1;
		else sin = 2;
		
        int height = 50;
        int width = 50;
        if(Degree%90 != 0) {
        	width = (int) Math.round(width/Math.sqrt(2));
        	height = (int) Math.round(height/Math.sqrt(2));
        }
 
        position[0].x = (int) (center.x + cos * width / 2.0 - sin * height / 2.0);
        position[0].y = (int) (center.y + sin * width / 2.0 + cos * height / 2.0);
 
        position[1].x = (int) (center.x + cos * width / 2.0 + sin * height / 2.0);
        position[1].y = (int) (center.y + sin * width / 2.0 - cos * height / 2.0);
 
        position[2].x = (int) (center.x - cos * width / 2.0 + sin * height / 2.0);
        position[2].y = (int) (center.y - sin * width / 2.0 - cos * height / 2.0);
 
        position[3].x = (int) (center.x - cos * width / 2.0 - sin * height / 2.0);
        position[3].y = (int) (center.y - sin * width / 2.0 + cos * height / 2.0);
	}
	@Override
	public void Draw(Graphics2D G) {
		
		G.setColor(Color.yellow);
		G.fill(trapez);
		
	}

	@Override
	public void Update(long Tick) {
		double cos = 2*Math.cos(Math.toRadians(Degree));
		double sin = 2*Math.sin(Math.toRadians(Degree));


        if(cos < 0.5 && cos > (-0.5)) cos = 0;
        else if (cos >= 0.5 && cos < 1.5) cos = 1;
        else if (cos >= 1.5) cos = 2;
        else if (cos <= (-0.5) && cos > (-1.5)) cos = -1;
		else cos = -2;
        
        if(sin < 0.5 && sin > (-0.5)) sin = 0;
        else if (sin >= 0.5 && sin < 1.5) sin = 1;
        else if (sin >= 1.5) sin = 2;
        else if (sin <= (-0.5) && sin > (-1.5)) sin = -1;
		else sin = -2;
		
        if(Degree%90 == 0){
        	if ( sin == 2) sin = 1;
        	if ( sin == (-2)) sin = -1;
        	if ( cos == 2) cos = 1;
        	if ( cos == (-2)) cos = -1;
        }
		position[0].x += cos;
		position[0].y += sin;
		position[1].x += cos;
		position[1].y += sin;
		position[2].x += cos;
		position[2].y += sin;
		position[3].x += cos;
		position[3].y += sin;
		center.x += cos;
		center.y += sin;
		
		trapez.reset();
		trapez.moveTo(position[0].x, position[0].y);
		trapez.lineTo(position[1].x, position[1].y);
	    trapez.lineTo(position[2].x, position[2].y);
	    trapez.lineTo(position[3].x, position[3].y);
	    trapez.closePath();
			
	}

	@Override
	public boolean MouseClick(Point P) {
		Degree += 45;
		Rotate();
		return true;
	}

	@Override
	public boolean KeyDown(int KeyCode) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean KeyUp(int KeyCode) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean KeyPressed(int KeyCode, char KeyChar) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void ZoomChanged() {
		// TODO Auto-generated method stub
		
	}

}
