import java.awt.* ;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

public class TetrisPanel extends Panel implements KeyListener,MouseListener
{
	private int interval=1;
	private int level[]={1000, 500, 250, 200, 150, 100, 50};		//refers to the speed of each level of the game
	private final Color backGround=Color.LIGHT_GRAY;		//colour of the background

	private int curLevel=0;		//current level of the game
	private long counter=0;

	/*
		The following are constants used for user input
	*/
	private final int SHIFT_R=KeyEvent.VK_RIGHT;
	private final int SHIFT_L=KeyEvent.VK_LEFT;
	private final int H_DROP=KeyEvent.VK_SPACE;
	private final int ROTATE_C=KeyEvent.VK_UP;
	private final int S_DROP=KeyEvent.VK_DOWN;
	private final int HOLD=KeyEvent.VK_SHIFT;

	private Dimension dim=null;
	BufferedImage osi=null;
	Graphics osg=null;		//used for double buffer

	public Tetris t=new Tetris();
	TetrisPanel()		//this is the timer of the game
	{
		t.nextPiece.addLast((Integer)(int)(Math.random()*7));
		t.nextPiece.addLast((Integer)(int)(Math.random()*7));
		t.nextPiece.addLast((Integer)(int)(Math.random()*7));
		t.nextPiece.addLast((Integer)(int)(Math.random()*7));
		addKeyListener(this);
		addMouseListener(this);
		final Timer timer=new Timer();
		TimerTask drop=new TimerTask()		//after a certain interval, a piece is dropped or inserted
		{
			public void run()
			{
				if(counter%level[curLevel]==0 && t.game)
				{
					t.save();
					t.drop();
					t.sync();
					repaint();
				}
				counter++;
			}
		};
		timer.scheduleAtFixedRate(drop, 0, interval);
	}
	public void paint(Graphics g)		//double buffer
	{
		dim=getSize();
		t.changeDimension(dim);
		osi=new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		osg=osi.getGraphics();
		update(g);
	}

	public void update(Graphics g)		//double buffer
	{
		curLevel=t.cleared/20;		//used to assign the game's level
		if(curLevel>6)
			curLevel=6;

		osg.setColor (backGround);
		osg.fillRect( 0, 0, dim.width, dim.height);
		t.display(osg);
		g.drawImage(osi, 0, 0, this);
	}

	public void keyPressed(KeyEvent ke)		//controls of the game
	{
		int a=ke.getKeyCode();
		if(t.game)
		switch(a)
		{
			case SHIFT_R:		//shifts piece right
				t.save();
				t.rightShift();
				t.sync();
				repaint();
				break;

			case SHIFT_L:		//shifts piece left
				t.save();
				t.leftShift();
				t.sync();
				repaint();
				break;

			case ROTATE_C:		//rotates piece clockwise
				t.save();
				t.rotateC();
				t.sync();
				repaint();
				break;

			case S_DROP:		//drops the piece a spot
				t.save();
				t.next();
				t.sync();
				repaint();
				counter=0;
				break;
			case H_DROP:		//hard drops current piece
				t.save();
				t.hDrop();
				t.sync();
				repaint();
				counter=0;
				break;
			case HOLD:			//holds a piece
				t.save();
				t.hold();
				t.sync();
				repaint();
			default:
				break;
		}
	}
	public void keyReleased(KeyEvent ke)
	{

	}
	public void keyTyped(KeyEvent ke)
	{
	}
	public void mousePressed(MouseEvent me)		//used to reset the game
	{
		if(!t.game)
		{
			t.reset();
			repaint();
		}
	}
	public void mouseReleased(MouseEvent me)
	{

	}
	public void mouseClicked(MouseEvent me)
	{

	}
	public void mouseExited(MouseEvent me)
	{

	}
	public void mouseEntered(MouseEvent me)
	{

	}
}