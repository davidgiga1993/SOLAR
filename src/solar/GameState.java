package solar;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import objects.GameObject;

public abstract class GameState implements GameObjectInterface, SubframeInterface,
        SimpleInputInterface, MouseMoveInterface
{
    protected GameLogic GL;
    protected int ID; // Eigene ID -> Entspricht dem Index in der GameLogic
                      // Liste

    protected List<GameObject> Objects = new ArrayList<GameObject>();
    protected List<GameObject> SubObjects = new ArrayList<GameObject>();
    
    protected double Zoom = 1.0;
    protected double ViewportX = 0;
    protected double ViewportY = 0;

    public GameState(GameLogic GL)
    {
        this.GL = GL;
    }
    
    public void AddZoom(double ToAdd)
    {
        SetZoom(Zoom + ToAdd);
    }
    public void SetZoom(double Zoom)
    {
        this.Zoom = Zoom;
        ViewportX =  GameEngine.Width / 2 - Zoom * GameEngine.Width / 2;
        ViewportY =  GameEngine.Height / 2 - Zoom * GameEngine.Height / 2;
    }
    

    @Override
    public void Draw(Graphics2D G)
    {
        if(Zoom != 1.0f)
        {
            G.translate(ViewportX, ViewportY);
            G.scale(Zoom, Zoom);
            Zoom = 1.0f;
        }
        for (int X = 0; X < Objects.size(); X++)
        {
            Objects.get(X).Draw(G);
        }
    }
    
    @Override
    public void DrawSubframe(Graphics2D G)
    {
        for(int X = 0; X < SubObjects.size(); X++)
        {
            SubObjects.get(X).Draw(G);
        }
        
    }

    @Override
    public void Update(long Tick)
    {
        for (int X = 0; X < Objects.size(); X++)
        {
            Objects.get(X).Update(Tick);
        }
    }
    
    @Override
    public boolean equals(Object O)
    {
        if(O instanceof GameState)
        {
            GameState G = (GameState)O;
            if(G.ID == ID)
                return true;
        }
        return false;
    }
}
