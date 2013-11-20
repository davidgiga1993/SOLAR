package objects;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import tools.Calculator;

public class ObjectStern extends GameObject {
	private int Masse;
	private int Durchmesser;
	private Paint Farbe;
	private Point Position;
	private Sterntyp Typ;
	
	public enum Sterntyp
	{
		HauptreiheBlau("blau.png"),
		HauptreiheBlauWeiﬂ("blauweiss.png"),
		HauptreiheWeiﬂ("weiss.png"),
		HauptreiheWeiﬂGelb("weissgelb.png"),
		HauptreiheGelb("gelb.png"),
		HauptreiheOrange("orange.png"),
		HauptreiheRotOrange("rotorange.png"),
		RiesensternRot("riesenrot.png"),
		RiesensternOrange("riesenorange.png"),
		RiesensternGelb("riesengelb.png"),
		weiﬂerZwerg("weisserzwerg.png"),
		braunerZwerg("braun.png"),
		SchwarzesLoch("schwarz.png"),
		Neutronenstern("neutron.png");
		
		public String TexturePath;
		private Sterntyp(String TexturePath)
		{
			this.TexturePath = TexturePath;
		}
		
		public static Sterntyp getRandomType() {
	        return values()[(int) (Math.random() * values().length)];
	    }
	}

	public ObjectStern(int Masse, int Durchmesser, Paint Farbe, Point Position, Sterntyp Typ) {
		this.Masse = Masse;
		this.Durchmesser = Durchmesser;
		this.Farbe = Farbe;
		this.Position = Position;
		this.Typ = Typ;
	}

	public ObjectStern() {
		this.Typ = Sterntyp.getRandomType();
		this.Masse = (int) (Math.random()*100000);//1 = 0,001 Sonnenmassen
		this.Durchmesser = 50;
		
	}
	
	
	private int MassenBestimmung(Sterntyp Typ) {
		
		int Untergrenze = 0;
		int Obergrenze = 0;
		switch (Typ){
		
		case HauptreiheBlau:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheBlauWeiﬂ:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheWeiﬂ:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheWeiﬂGelb:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheGelb:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheOrange:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case HauptreiheRotOrange:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case RiesensternGelb:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case RiesensternOrange:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case RiesensternRot:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case weiﬂerZwerg:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case braunerZwerg:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case SchwarzesLoch:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
		case Neutronenstern:
			Untergrenze = 25;
			Obergrenze = 100;
			break;
			
		default:
			break;
		}
		return Calculator.Random(Untergrenze*Calculator.Sonnenmasse, Obergrenze*Calculator.Sonnenmasse);
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
