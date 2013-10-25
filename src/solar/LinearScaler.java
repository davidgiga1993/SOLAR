package solar;

public class LinearScaler extends ScaleEffect
{
	@Override
	public int Update(long Tick)
	{
		if((!IsDown && StartValue >= FinalValue) || (IsDown && StartValue <= FinalValue))
		{
			IsFinal = true;
			return FinalValue;
		}
		else
		{
			if(IsDown)
				StartValue -= Speed;
			else
				StartValue += Speed;
			
			return StartValue;
		}	
	}
}
