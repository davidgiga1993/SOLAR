import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;


public class GameEngine  extends Applet 
{
	private static final long serialVersionUID = 1L;
	private Graphics mBufferGraphics;
	private Image mOffscreen;
	private Timer mFrameTimer;
	private Timer mGameTimer;
	
	private GameLogic GL;
	
	private long GameTick = 0;
	private int Width = 800;
	private int Height = 600;
	
	public void init()
	{
		setSize(Width, Height);
		mOffscreen = createImage(Width,Height);
		mBufferGraphics = mOffscreen.getGraphics();
		
		addMouseMotionListener(new MouseMotion());
		addMouseListener(new MouseClick());
	}
	
	public void start()
	{		
		mFrameTimer = new Timer(15, new FrameTimerTick());
		mGameTimer = new Timer(50, new GameLogicTick());
		mFrameTimer.start();
		mGameTimer.start();
		
		GL = new GameLogic(this);
	}
	
    public void update(Graphics g)
    { 
         paint(g); 
    }
    
    public void paint(Graphics g)
    {
    	mBufferGraphics.clearRect(0, 0, Width, Height);
    	
    	GL.Draw(mBufferGraphics);
    	g.drawImage(mOffscreen, 0, 0, this);
    }
    
    private class FrameTimerTick implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			repaint();
		}
    }
    private class GameLogicTick implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			GL.Update(GameTick);
			GameTick++;
		}
    }
    
    private class MouseMotion implements MouseMotionListener
    {
		@Override
		public void mouseDragged(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0)
		{
			GL.MouseMove(arg0.getPoint());
		}    	
    }
    private class MouseClick implements MouseListener
    {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			GL.MouseClick(e.getPoint());
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			
			GL.MouseDown(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			GL.MouseRelease(e.getPoint());
		}    	
    }
}
