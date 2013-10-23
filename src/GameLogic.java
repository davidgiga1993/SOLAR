import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class GameLogic implements GameObjectInterface
{
	public GameEngine GE;
	private List<GameObject> Objects = new ArrayList<GameObject>();
	private List<MouseMoveInterface> MouseMove = new ArrayList<MouseMoveInterface>();
	private int ObjectCounter = 0;
	
	public GameLogic(GameEngine GE)
	{
		this.GE = GE;
		
		AddObject(new BeispielGameObject()); // Beispielobject hinzufügen
	}
	
	// Fügt ein neues GameObject der Liste hinzu und gibt dessen Index zurück
	public int AddObject(GameObject O)
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
    		if(Objects.get(X).ClickArea.contains(P.x, P.y))
    		{
    			Objects.get(X).MouseClick(P);
    		}
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

	@Override
	public void KeyDown(int KeyCode)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
			Objects.get(X).KeyDown(KeyCode);
    	}
	}

	@Override
	public void KeyUp(int KeyCode)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
			Objects.get(X).KeyUp(KeyCode);
    	}
	}

	@Override
	public void KeyPressed(int KeyCode, char KeyChar)
	{
		for(int X = 0; X < Objects.size(); X++)
    	{
			Objects.get(X).KeyPressed(KeyCode, KeyChar);
    	}
	}
}
