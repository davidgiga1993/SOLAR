package com.me.ressources;

public class Credits{
	
	double RaiseRate;
	double Value;
	
	public Credits ()
	{
		this.setRaiseRate(0);
		this.setValue(0);
	}
	
	public void raise(){
		this.setValue(this.Value * this.RaiseRate);
	}
	
	public void setRaiseRate(double Value)
	{
		this.RaiseRate = Value;
  	     System.out.println("Raise Rate für Credits wurden gesetzt auf " + this.getRaiseRate());
	}
	
	public double getRaiseRate()
	{
		return this.RaiseRate;
	}
	
	public void setValue(double Value)
	{
		this.Value = Value;
  	     System.out.println("Value für Credits wurden gesetzt auf " + this.getValue());
	}
	
	public double getValue()
	{
		return this.Value;
	}

}
