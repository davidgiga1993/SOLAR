package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import tools.Calculator;

public class ObjectStern extends GameObject {
	private int Masse;
	private int Durchmesser;
	private Point Position = new Point(0,0);
	private Sterntyp Typ;
	private RadialGradientPaint Farbe;
	
	public enum Sterntyp
	{
		HauptreiheBlau(Color.blue),
		HauptreiheBlauWeiﬂ(Color.cyan),
		HauptreiheWeiﬂ(Color.white),
		HauptreiheWeiﬂGelb(Color.cyan),
		HauptreiheGelb(Color.yellow),
		HauptreiheOrange(Color.orange),
		HauptreiheRotOrange(Color.cyan),
		RiesensternRot(Color.red),
		RiesensternOrange(Color.orange),
		RiesensternGelb(Color.yellow),
		weiﬂerZwerg(Color.white),
		braunerZwerg(Color.cyan),
		SchwarzesLoch(Color.cyan),
		Neutronenstern(Color.cyan);
		
		public Color Farbe;
		private Sterntyp(Color Farbe)
		{
			this.Farbe = Farbe;
		}
		
		public static Sterntyp getRandomType() {
	        return values()[(int) (Math.random() * values().length)];
	    }
	}

	public ObjectStern(int Masse, int Durchmesser, Point Position, Sterntyp Typ) {
		this.Masse = Masse;
		this.Durchmesser = Durchmesser;
		this.Position = Position;
		this.Typ = Typ;
		Farbe = SetColor();
		
		
	}

	public ObjectStern() {
		Typ = Sterntyp.getRandomType();
		Masse = MassenBestimmung(Typ);
		Durchmesser = DurchmesserBestimmung(Typ, Masse);
		Position.x = Calculator.Random(0, 1000);
		Position.y = Calculator.Random(0, 700);
		Farbe = SetColor();
		
	}
	
	private int DurchmesserBestimmung(Sterntyp Typ, int Masse) {
		
		int Durchmesser = 0;
		
		switch (Typ){
		
		case HauptreiheBlau:
		case HauptreiheBlauWeiﬂ:
		case HauptreiheWeiﬂ:
		case HauptreiheWeiﬂGelb:
		case HauptreiheGelb:
		case HauptreiheOrange:
		case HauptreiheRotOrange:
			Durchmesser = (int) (Math.pow((double) Masse, (9.0/19.0))/26.36*100);//Teilung durch 26,36 um 
			if(Durchmesser < 10) Durchmesser = 10;
			break;																 //die Einheit Sonnenmassen zu erhalten
			
		default:
			Durchmesser = 100;
			break;
		
		}
		return Durchmesser;
	}
	
	private int MassenBestimmung(Sterntyp Typ) {
		
		float Untergrenze = 0;
		float Obergrenze = 0;
		switch (Typ){
		
		case HauptreiheBlau:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheBlauWeiﬂ:
			Untergrenze = 6;
			Obergrenze = 25;
			break;
		case HauptreiheWeiﬂ:
			Untergrenze = 1.9f;
			Obergrenze = 6;
			break;
		case HauptreiheWeiﬂGelb:
			Untergrenze = 1.3f;
			Obergrenze = 1.9f;
			break;
		case HauptreiheGelb:
			Untergrenze = 0.9f;
			Obergrenze = 1.3f;
			break;
		case HauptreiheOrange:
			Untergrenze = 0.5f;
			Obergrenze = 0.9f;
			break;
		case HauptreiheRotOrange:
			Untergrenze = 0.1f;
			Obergrenze = 0.5f;
			break;
		case RiesensternGelb:
			Untergrenze = 6;
			Obergrenze = 100;
			break;
		case RiesensternOrange:
			Untergrenze = 2.3f;
			Obergrenze = 25;
			break;
		case RiesensternRot:
			Untergrenze = 0.9f;
			Obergrenze = 6;
			break;
		case weiﬂerZwerg:
			Untergrenze = 0.1f;
			Obergrenze = 1.44f;
			break;
		case braunerZwerg:
			Untergrenze = 0.0013f;
			Obergrenze = 0.5f;
			break;
		case SchwarzesLoch:
			Untergrenze = 3;
			Obergrenze = 100;
			break;
		case Neutronenstern:
			Untergrenze = 1.44f;
			Obergrenze = 3;
			break;
			
		default:
			break;
		}
		return Calculator.Random((int) Untergrenze*Calculator.Sonnenmasse, (int) Obergrenze*Calculator.Sonnenmasse);
	}
	
	private RadialGradientPaint SetColor() {
		
		Point2D center = new Point2D.Float(Position.x, Position.y);
		float radius = Durchmesser/2;
		float[] dist = {0.0f, 0.5f, 1.0f};
		Color[] colors = {Typ.Farbe, Typ.Farbe, new Color(0, 0, 0, 0 )};
		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		
		return p;
	}
	@Override
	public void Draw(Graphics2D G) {
		
		G.setPaint(Farbe); 
		G.fillRect(Position.x - Durchmesser/2, Position.y - Durchmesser/2, Durchmesser*2, Durchmesser*2); 
	}
	

	@Override
	public void Update(long Tick) {

	}

	@Override
	protected void ZoomChanged() {

	}

}
