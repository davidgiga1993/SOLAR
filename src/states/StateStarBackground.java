package states;

import java.awt.Paint;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import objects.GameObjectRectangle;
import solar.GameEngine;
import solar.GameLogic;
import solar.GameState;
import texture.TextureLoader;

public class StateStarBackground extends GameState
{
    private Paint Background;

    public StateStarBackground(GameLogic GL)
    {
        super(GL);

        BufferedImage Texture = TextureLoader.LoadTexture("bg.png");

        GameObjectRectangle BG = new GameObjectRectangle(0, 0, GameEngine.MaxPositions, GameEngine.MaxPositions);
        
        if (Texture != null)
            Background = new TexturePaint(Texture, BG.toRectangle());

        BG.paint = Background;
        BG.Fill = true;

        SubObjects.add(BG);

    }

    @Override
    public boolean MouseClick(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyDown(int KeyCode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyUp(int KeyCode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyPressed(int KeyCode, char KeyChar)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseMove(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseDown(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseRelease(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseWheelMoved(MouseWheelEvent event)
    {
        // TODO Auto-generated method stub
        return false;
    }

    

}
