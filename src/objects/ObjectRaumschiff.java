package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import solar.SimpleInputInterface;
import texture.TextureLoader;

public class ObjectRaumschiff extends GameObject implements SimpleInputInterface{
	
	private Point center;
	private double Degree = 0;
	private AffineTransformOp op;
	
	BufferedImage img = TextureLoader.LoadTexture("raumschiff.png");
	
	public ObjectRaumschiff(Point center) {
		this.center = center;
	}
	
	private AffineTransformOp Rotate() {
		double rotationRequired = Math.toRadians(Degree);
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		return op;
	}
	
	private double getAngle(Point MousePosition) {
	    double angle = Math.atan2(center.y - MousePosition.y, center.x - MousePosition.x);
	    
	    return Math.toDegrees(angle);
	}
	
	@Override
	public void Draw(Graphics2D G) {

		// Drawing the rotated image at the required drawing locations
		G.drawImage(op.filter(img, null), center.x - img.getWidth()/2, center.y - img.getHeight()/2, null);
		G.setColor(Color.yellow);
		G.fillOval(center.x, center.y, 5, 5);
		
	}

	@Override
	public void Update(long Tick) {

		center.x += 2 * Math.cos(Math.toRadians(Degree));
		center.y += 2 * Math.sin(Math.toRadians(Degree));
	}

	@Override
	public boolean MouseClick(Point P) {
		Point MousePosition = MouseInfo.getPointerInfo().getLocation();
		Degree = getAngle(MousePosition) - 180;
		op = Rotate();
		return false;
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
