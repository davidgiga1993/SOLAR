package solar;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class GameEngine extends Applet
{
    private static final long serialVersionUID = 1L;
    private Graphics2D mBufferGraphics;
    private Graphics2D mBufferSubframe;

    private BufferedImage mOffscreen;
    private Image mSubframe;

    private Timer mFrameTimer;
    private Timer mSubFrameTimer;
    private Timer mGameTimer;

    // DEBUG
    private Timer mDebugTimer;
    private int mDebugFPS;
    private int mDebugTPS;
    private int mDebugFPSCounter;
    private int mDebugTPSCounter;
    private Font mDebugFont;
    public boolean mShowDebug = true;
    // END DEBUG

    private GameLogic GL;

    private long GameTick = 0;

    public static final int Width = 1200;
    public static final int Height = 810;
    public static final int CenterX = Width / 2;
    public static final int CenterY = Height / 2;

    public static final int DebugLoopTime = 1000;
    public static final int DrawSleepTime = 5;
    public static final int DrawSubframeSleepTime = 50;
    public static final int GameLoopTime = 20;

    public void init()
    {
        setSize(Width, Height);
        mOffscreen = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        mSubframe = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);

        mBufferSubframe = (Graphics2D) mSubframe.getGraphics();
        mBufferGraphics = (Graphics2D) mOffscreen.getGraphics();

        addMouseMotionListener(new MouseMotion());
        addMouseListener(new MouseClick());
        addMouseWheelListener(new MouseWheel());
        addKeyListener(new KeyEvents());
    }

    public void start()
    {
        GL = new GameLogic(this);

        mDebugTimer = new Timer(DebugLoopTime, new DebugTick());
        mFrameTimer = new Timer(DrawSleepTime, new FrameTimerTick());
        mSubFrameTimer = new Timer(DrawSubframeSleepTime, new SubFrameTimerTick());

        mGameTimer = new Timer(GameLoopTime, new GameLogicTick());
        mSubFrameTimer.start();
        mFrameTimer.start();
        mGameTimer.start();
        mDebugTimer.start();
        mDebugFont = new Font("Arial", Font.PLAIN, 10);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        mBufferGraphics.setColor(Color.black);
        mBufferGraphics.fillRect(0, 0, Width, Height);


        mBufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        mBufferGraphics.drawImage(mSubframe, 0, 0, null);
        GL.Draw(mBufferGraphics);

        if (mShowDebug)
        {
            mDebugFPSCounter++;
            mBufferGraphics.setPaint(Color.white);
            mBufferGraphics.setFont(mDebugFont);
            mBufferGraphics.drawString("FPS", 2, 13);
            mBufferGraphics.drawString(String.valueOf(mDebugFPS), 25, 13);
            mBufferGraphics.drawString("TPS", 2, 25);
            mBufferGraphics.drawString(String.valueOf(mDebugTPS), 25, 25);
        }
        g.drawImage(mOffscreen, 0, 0, this);
    }

    private class DebugTick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            mDebugFPS = mDebugFPSCounter;
            mDebugTPS = mDebugTPSCounter;
            mDebugFPSCounter = 0;
            mDebugTPSCounter = 0;
        }
    }

    private class FrameTimerTick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            repaint();
        }
    }

    private class SubFrameTimerTick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            GL.DrawSubframe(mBufferSubframe);
        }
    }

    private class MouseWheel implements MouseWheelListener
    {
        @Override
        public void mouseWheelMoved(MouseWheelEvent arg0)
        {
            GL.MouseWheelMoved(arg0);

        }
    }

    private class GameLogicTick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            GL.Update(GameTick);
            GameTick++;

            if (mShowDebug)
            {
                mDebugTPSCounter++;
            }
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

    private class KeyEvents implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent arg0)
        {
            GL.KeyDown(arg0.getKeyCode());
            if (arg0.getKeyCode() == 112) // F1
            {
                mShowDebug = !mShowDebug;
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0)
        {
            GL.KeyUp(arg0.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent arg0)
        {
            GL.KeyPressed(arg0.getKeyCode(), arg0.getKeyChar());
        }
    }
}
