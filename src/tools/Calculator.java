package tools;

public final class Calculator {
	
	public static final int Sonnenmasse = 1000; 
	
	public static final int Random(int Untergrenze, int Obergrenze){
		return (int) (Math.random()*(Obergrenze-Untergrenze))+Untergrenze;
	}

}
