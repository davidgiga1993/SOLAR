import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Main  extends Applet 
{
	
	private Graphics mBufferGraphics;
	private Image mOffscreen;
	private Timer mFrameTimer;
	private int X = 0;
	
	public void init()
	{
		mOffscreen = createImage(WIDTH,HEIGHT);
		mBufferGraphics = mOffscreen.getGraphics();
	}
	
	public void start()
	{
		
		mFrameTimer = new Timer(15, new FrameTimerTick());
		mFrameTimer.start();
	}
	
    public void update(Graphics g)
    { 
         paint(g); 
    }
    
    public void paint(Graphics g)
    {
    	mBufferGraphics.clearRect(0,0,WIDTH,HEIGHT);
    	mBufferGraphics.drawLine(0, 0, WIDTH, HEIGHT);    	
    	g.drawImage(mOffscreen,0,0,this);
    }
    
    private class FrameTimerTick implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			repaint();
		}
    }
}
