import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class GameLogic implements GameObjectInterface
{
	public GameEngine GE;
	private List<GameObjectInterface> Objects = new ArrayList<GameObjectInterface>();
	private List<MouseMoveInterface> MouseMove = new ArrayList<MouseMoveInterface>();
	private int ObjectCounter = 0;
	
	public GameLogic(GameEngine GE)
	{
		this.GE = GE;
				
		AddObject(new Hallo());
	}
	
	// Fügt ein neues GameObject der Liste hinzu und gibt dessen Index zurück
	public int AddObject(GameObjectInterface O)
    {
    	Objects.add(O);
    	ObjectCounter++;
    	return (ObjectCounter - 1);
    }
	
	public void RemoveObject(int Index)
	{
		Objects.remove(Index);
		ObjectCounter--;
	}
	
	@Override
	public void Draw(Graphics G)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
    		Objects.get(X).Draw(G);
    	}		
	}

	@Override
	public void Update(long Tick)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
    		Objects.get(X).Update(Tick);
    	}		
	}
	
	public void MouseMove(Point P)
	{
		for(int X = 0; X < MouseMove.size(); X++)
    	{
    		MouseMove.get(X).MouseMove(P);
    	}		
	}

	@Override
	public void MouseClick(Point P)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
    		Objects.get(X).MouseClick(P);
    	}	
	}
	
	public void MouseDown(Point P)
	{
		for(int X = 0; X < MouseMove.size(); X++)
    	{
			MouseMove.get(X).MouseDown(P);
    	}	
	}
	
	public void MouseRelease(Point P)
	{
		for(int X = 0; X < MouseMove.size(); X++)
    	{
			MouseMove.get(X).MouseMove(P);
    	}		
	}
}
