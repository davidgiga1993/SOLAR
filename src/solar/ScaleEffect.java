package solar;

public abstract class ScaleEffect
{
	public int Speed;
	protected int StartValue;
	protected int FinalValue;
	public boolean IsFinal = true;
	public boolean IsDown = false;
	
	public void SetValues(int Start, int End, int Speed) // Speed = Px per Tick
	{
		StartValue = Start;
		FinalValue = End;
		if(Start > End)
			IsDown = true;
		else
			IsDown = false;
		
		this.Speed = Speed;
		IsFinal = false;
	}
	
	public abstract int Update(long Tick);
}
