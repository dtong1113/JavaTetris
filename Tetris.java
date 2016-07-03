import java.awt.*;
import java.util.*;

public class Tetris
{
	private static Dimension dim;
	private static int piece=(int)(Math.random()*7);		//the current piece
	private static int topX=0;			//x value of the top left corner of the game array
	private static int topY=0;			//y value of the top left corner of the game array
	private static int orientation=0;	//orientation of the piece
	private static int pieceL=6;		//y value of the top left corner of the piece array
	private static int pieceH=0;		//x value of the top left corner of the piece array
	public static int cleared=0;		//number of lines cleared
	private static int ghostPiece=0;	//amount of grids below the ghost piece should be displayed
	public static LinkedList<Integer> nextPiece=new LinkedList<Integer>();		//holds the piece queue
	public static int hold=0;		//checks whether or not a piece has been held this turn
	private static boolean inHand=false;		//checks if there is a piece in hand
	public static int pieceHeld=0;			//the piece being held

	public static boolean game=true;		//checks whether or not the game is over

	private static int [][]array=new int[][]		//game array
	{
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	};





	private static Color []col=new Color[] {Color.WHITE, Color.CYAN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.RED};	//colour of each piece

	private static final int [][][][] PIECES=new int[][][][]//piece 0-6, orientation, vertical, horizontal
	{
		{//line
			{
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 0}
			},
			{
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 0, 0}
			},
			{
				{0, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 1, 0, 0}
			}

		},
		{//reverse L
			{
				{2, 0, 0, 0},
				{2, 2, 2, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 2, 2, 0},
				{0, 2, 0, 0},
				{0, 2, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 0, 0},
				{2, 2, 2, 0},
				{0, 0, 2, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 2, 0, 0},
				{0, 2, 0, 0},
				{2, 2, 0, 0},
				{0, 0, 0, 0}
			}

		},
		{//L piece
			{
				{0, 0, 3, 0},
				{3, 3, 3, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 3, 0, 0},
				{0, 3, 0, 0},
				{0, 3, 3, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 0, 0},
				{3, 3, 3, 0},
				{3, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{3, 3, 0, 0},
				{0, 3, 0, 0},
				{0, 3, 0, 0},
				{0, 0, 0, 0}
			}

		},
		{//Square piece
			{
				{0, 4, 4, 0},
				{0, 4, 4, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 4, 4, 0},
				{0, 4, 4, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 4, 4, 0},
				{0, 4, 4, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 4, 4, 0},
				{0, 4, 4, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			}

		},
		{//s right
			{
				{0, 5, 5, 0},
				{5, 5, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 5, 0, 0},
				{0, 5, 5, 0},
				{0, 0, 5, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 0, 0},
				{0, 5, 5, 0},
				{5, 5, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{5, 0, 0, 0},
				{5, 5, 0, 0},
				{0, 5, 0, 0},
				{0, 0, 0, 0}
			}

		},
		{//T piece
			{
				{0, 6, 0, 0},
				{6, 6, 6, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 6, 0, 0},
				{0, 6, 6, 0},
				{0, 6, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 0, 0},
				{6, 6, 6, 0},
				{0, 6, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 6, 0, 0},
				{6, 6, 0, 0},
				{0, 6, 0, 0},
				{0, 0, 0, 0}
			}

		},
		{//S left
			{
				{7, 7, 0, 0},
				{0, 7, 7, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 7, 0},
				{0, 7, 7, 0},
				{0, 7, 0, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 0, 0, 0},
				{7, 7, 0, 0},
				{0, 7, 7, 0},
				{0, 0, 0, 0}
			},
			{
				{0, 7, 0, 0},
				{7, 7, 0, 0},
				{7, 0, 0, 0},
				{0, 0, 0, 0}
			}

		}
	};
	private static int curPos[][]=new int[4][4];		//the state of the 4 by 4 area before the piece is added

	public static boolean check()	//checks whether or not the move is possible
	{
		for(int x=0; x<4; x++)
		{
			for(int y=0; y<4; y++)
			{
				if(array[pieceH+x][pieceL+y]!=0 && PIECES[piece][orientation][x][y]!=0)
					return true;
			}
		}
		return false;
	}
	public static void leftShift()	//shifts piece to the left
	{
		pieceL--;
		if(check())
			pieceL++;
	}
	public static void rightShift()	//shifts piece to the right
	{
		pieceL++;
		if(check())
			pieceL--;
	}
	public static void next()	//soft drops piece
	{

		pieceH++;
		if(check())
			pieceH--;
	}
	public static void drop()	//piece naturally falls with time
	{
		pieceH++;
		if(check())
		{
			pieceH--;
			for(int x=0; x<4; x++)
			{
				for(int y=0; y<4; y++)
				{
					if(PIECES[piece][orientation][x][y]!=0)
						array[pieceH+x][pieceL+y]=PIECES[piece][orientation][x][y];
				}
			}
			piece=(int)nextPiece.getFirst();
			nextPiece.removeFirst();
			nextPiece.addLast((Integer)(int)(Math.random()*7));
			orientation=0;
			pieceL=6;
			pieceH=0;
			hold=0;
		}

		/*
			The following checks for filled lines, clears them and shifts the pieces down
		*/
		for(int x=0; x<24; x++)
		{
			boolean check=true;

			for(int y=3; y<13; y++)
			{
				if(array[x][y]==0)
					check=false;
			}
			if(check)
			{
				cleared++;
				for(int y=3; y<13; y++)
				{
					array[x][y]=0;
				}
				for(int y=x; y>0; y--)
				{
					for(int z=3; z<13; z++)
					{
						array[y][z]=array[y-1][z];
					}
				}
			}
		}
		/*
			This checks whether or not the player has lost
		*/
		for(int y=3; y<13; y++)
		{
			if(array[3][y]!=0)
			{
				game=false;
			}
		}

	}
	public static void hDrop()	//this hard drops the tetris piece
	{
		while(!check())
		{
			pieceH++;
		}
		pieceH--;
		drop();
	}
	public static void rotateC()	//rotates the piece clockwise
	{
		orientation++;
		orientation%=4;
		if(check())
		{
			orientation--;

		}
	}
	public static void save()	//this restores the original state of the array without the piece
	{
		for(int x=0; x<4; x++)
		{
			for(int y=0; y<4; y++)
			{
				array[pieceH+x][pieceL+y]=curPos[x][y];
			}
		}
	}
	public static void sync()	//this syncs our piece with the game array
	{
		/*
			The following is used to generate a "ghost" piece at the bottom of the array
		*/
		ghostPiece=0;
		boolean flag=true;
		while(flag)
		{
			ghostPiece++;
			for(int x=0; x<4; x++)
			{
				for(int y=0; y<4; y++)
				{
					if(array[pieceH+x+ghostPiece][pieceL+y]!=0 && PIECES[piece][orientation][x][y]!=0)
					{
						flag=false;
					}
				}
			}
		}
		ghostPiece--;

		/*
			This saves a state of the array without a piece
		*/
		for(int x=0; x<4; x++)
		{
			for(int y=0; y<4; y++)
			{
				curPos[x][y]=array[pieceH+x][pieceL+y];
			}
		}
		/*
			This inserts the piece into the game array
		*/
		for(int x=0; x<4; x++)
		{
			for(int y=0; y<4; y++)
			{
				if(PIECES[piece][orientation][x][y]!=0)
					array[pieceH+x][pieceL+y]=PIECES[piece][orientation][x][y];
			}
		}

	}
	public static void hold()		//this "holds" a piece in tetris
	{

		if(!inHand)
		{
			inHand=true;
			pieceHeld=piece;
			piece=(int)nextPiece.getFirst();
			nextPiece.removeFirst();
			nextPiece.addLast((int)(Math.random()*7));
			orientation=0;
			pieceH=0;
			pieceL=6;
		}
		else if(hold==0)		//makes sure hold can only be used one a turn
		{
			int temp=piece;
			piece=pieceHeld;
			pieceHeld=temp;
			orientation=0;
			pieceH=0;
			pieceL=6;
		}
		hold++;
	}
	public void changeDimension(Dimension newDim)		//gets the dimensions from TetrisPanel
	{
		dim = newDim;
	}
	public static void reset()		//restarts the game by resetting all game variables
	{
		topX=0;
		topY=0;
		orientation=0;
		pieceL=6;
		pieceH=0;
		cleared=0;
		game=true;
		array=null;
		array=new int[][]
		{
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};
		curPos=null;
		curPos=new int[][]
		{
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0}
		};
		inHand=false;
		hold=0;
		nextPiece.clear();
		piece=(int)(Math.random()*7);
		nextPiece.addLast((Integer)(int)(Math.random()*7));
		nextPiece.addLast((Integer)(int)(Math.random()*7));
		nextPiece.addLast((Integer)(int)(Math.random()*7));
		nextPiece.addLast((Integer)(int)(Math.random()*7));
		ghostPiece=0;
	}
	public static void display(Graphics g)	//using double buffer, we draw the tetris game
	{
		int SIZE=Math.min(dim.width/10, dim.height/24);		//the size of each grid
		topX=(dim.width-SIZE*10)/2;
		topY=(dim.height-SIZE*24)/2;


		for(int x=0; x<24; x++)		//displays the game array
		{
			for(int y=3; y<13; y++)
			{
				g.setColor(Color.BLACK);

				g.fillRect(topX+y*SIZE, topY+x*SIZE, SIZE, SIZE);
				g.setColor(col[array[x][y]]);
				g.fillRect(topX+y*SIZE+1, topY+x*SIZE+1, SIZE-2, SIZE-2);
			}
		}


		for(int x=0; x<4; x++)		//displays the ghost piece
		{
			for(int y=0; y<4; y++)
			{
				if(array[pieceH+x+ghostPiece][pieceL+y]==0 && PIECES[piece][orientation][x][y]!=0)
				{
					g.setColor(Color.GRAY);
					g.fillRect(topX+(pieceL+y)*SIZE+1, topY+(pieceH+x+ghostPiece)*SIZE+1, SIZE-2, SIZE-2);
				}
			}
		}

		for(int x=0; x<4; x++)		//displays the held piece
		{
			for(int y=0; y<4; y++)
			{
				g.setColor(Color.BLACK);
				g.fillRect(topX-50+y*SIZE, topY+x*SIZE+75, SIZE, SIZE);
				if(inHand)
					g.setColor(col[PIECES[pieceHeld][0][x][y]]);
				else
					g.setColor(col[0]);
				g.fillRect(topX-50+y*SIZE+1, topY+x*SIZE+1+75, SIZE-2, SIZE-2);
			}
		}
		for(int x=0; x<4; x++)		//displays the 1st piece in the queue
		{
			for(int y=0; y<4; y++)
			{
				g.setColor(Color.BLACK);
				g.fillRect(topX+13*SIZE+25+y*SIZE, topY+x*SIZE+75, SIZE, SIZE);
				g.setColor(col[PIECES[nextPiece.get(0)][0][x][y]]);
				g.fillRect(topX+13*SIZE+25+y*SIZE+1, topY+x*SIZE+1+75, SIZE-2, SIZE-2);
			}
		}
		for(int x=0; x<4; x++)		//displays the 2nd piece in the queue
		{
			for(int y=0; y<4; y++)
			{
				g.setColor(Color.BLACK);
				g.fillRect(topX+13*SIZE+25+y*SIZE, topY+x*SIZE+190, SIZE, SIZE);
				g.setColor(col[PIECES[nextPiece.get(1)][0][x][y]]);
				g.fillRect(topX+13*SIZE+25+y*SIZE+1, topY+x*SIZE+1+190, SIZE-2, SIZE-2);
			}
		}
		for(int x=0; x<4; x++)		//displays the 3rd piece in the queue
		{
			for(int y=0; y<4; y++)
			{
				g.setColor(Color.BLACK);
				g.fillRect(topX+13*SIZE+25+y*SIZE, topY+x*SIZE+305, SIZE, SIZE);
				g.setColor(col[PIECES[nextPiece.get(2)][0][x][y]]);
				g.fillRect(topX+13*SIZE+25+y*SIZE+1, topY+x*SIZE+1+305, SIZE-2, SIZE-2);
			}
		}
		for(int x=0; x<4; x++)		//displays the 4th piece in the queue
		{
			for(int y=0; y<4; y++)
			{
				g.setColor(Color.BLACK);
				g.fillRect(topX+13*SIZE+25+y*SIZE, topY+x*SIZE+420, SIZE, SIZE);
				g.setColor(col[PIECES[nextPiece.get(3)][0][x][y]]);
				g.fillRect(topX+13*SIZE+25+y*SIZE+1, topY+x*SIZE+1+420, SIZE-2, SIZE-2);
			}
		}

		String score="Score: "+cleared;		//prints the score
		Font f= new Font("Dialog", Font.PLAIN, 20);
		g.setColor(Color.BLACK);
		g.setFont(f);
		g.drawString(score, 100, 100);
		g.drawString("Hold:", 255, 75);		//prints Hold over the hold box
		g.drawString("Next:", 330+SIZE*13, 75);		//prints Next over the piece queue

		String level=""+(cleared/20+1);
		if(cleared/20 >=6)
			level="MAX";
		g.drawString("Level: "+level, 100, 150);	//prints the level of the game which increases every 20 lines

		Font h=new Font("Dialog", Font.PLAIN, 100);
		Font i=new Font("Dialog", Font.PLAIN, 50);

		if(!game)		//prints the GAME OVER screen
		{
			int brightness=128;
			g.setColor(new Color(0,0,0, brightness));
			g.fillRect(0, 0, dim.width, dim.height);
			g.setColor(Color.RED);
			g.setFont(h);
			g.drawString("GAMEOVER", 100, 300);
			g.setFont(i);
			g.drawString("Click to play again", 175, 375);
		}


	}
}