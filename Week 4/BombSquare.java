import java.util.*;

public class BombSquare extends GameSquare
{
	private BombSquare[] neighbours = new BombSquare[9];
	private int neighbourBombs = 0;
	private boolean thisSquareHasBomb = false;
	private boolean notClicked = true;
	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	public void clicked()
	{
		if(notClicked)
		{
			notClicked = false;
			if(thisSquareHasBomb)
			{
				this.setImage("images/bomb.png");
			}
			else
			{
				findNeighbours();
				calculateNeighbourBombs();
				//System.out.println("This one had "+neighbourBombs+" many neighbours with bombs.");
				//System.out.println("This ones [X,Y] is: ["+xLocation+","+yLocation+"]");
				this.setImage("images/"+neighbourBombs+".png");
				if(neighbourBombs==0)
				{
					for(int i=0;i<9;i++)
					{
						if(neighbours[i] != null) neighbours[i].clicked();
					}
				}
			}
		}
	}

	public boolean hasABomb()
	{
		return thisSquareHasBomb;
	}

	public void findNeighbours()
	{
		for(int i=0;i<3;i++)		//X
		{
			for(int j=0;j<3;j++)	//Y
			{
				neighbours[(i*3)+j] = (BombSquare) board.getSquareAt(xLocation+i-1,yLocation+j-1);
			}
		}
	}

	public void calculateNeighbourBombs()
	{
		for(int i=0;i<9;i++)
		{
			if(neighbours[i] == null)
			{}
			else if(neighbours[i].hasABomb())
			{
				neighbourBombs++;
			}
			else
			{}
		}
	}
}
