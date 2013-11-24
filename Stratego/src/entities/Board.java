
package entities;

import java.io.Serializable;

/*
 * RecomendacionesSingleton
 * 1. Ocultar el constructor (solo debe existir uno). Ponerlo "private"
 * 2. Tener una referencia oculta y con un punto de acceso global.
 * Una referencia "private static" y un método "public" para acceder a ella
 * 3. El método de acceso:
 * Debe revisar si es null la referencia al singleton y si es así, instanciarla
 * Debe ser synchronized
 * 4. Proteger el singleton contra clonación, sobre escribiendo "clone"
 * Se puede retornar lo que devuelve el método de acceso global.
 * ó lanzar una excepción.
 */

/**
 * Game board for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Board implements Serializable
{
	private static final long serialVersionUID = -333970939198499361L;
	private static final int SIZE = 10;
	private static Board gameBoard;
	
	Piece[][] map;
	
	/**
	 * Constructor of the unique instance of Board
	 */
	private Board()
	{
		super();
		map = new Piece[SIZE][SIZE];
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j] = null;
			}
		}
	}
	
	/**
	 * @return The unique instance of Board
	 */
	public static synchronized Board getBoard()
	{
		if (gameBoard == null)
		{
			gameBoard = new Board();
		}
		return gameBoard;
	}
	
	@Override
	public Object clone()
	{
		return getBoard();
	}
	
	/**
	 * @param x
	 * @param y
	 * @return isEmptyPos(x,y)
	 */
	public boolean isEmptyPos(byte x, byte y)
	{
		return (map[x][y] == null);
	}
	
	/**
	 * @param piece
	 * @param x
	 * @param y
	 * @return isPieceAt(x,y)
	 */
	public boolean isPieceAt(Piece piece, byte x, byte y)
	{
		if (piece == null)
			return false;
		
		return (map[x][y] == piece);
	}
	
	/**
	 * @param piece
	 * @return (x) position of piece
	 */
	public byte getPieceX(Piece piece)
	{
		if (piece == null)
			return -1;
		
		for (byte i = 0; i < map.length; i++)
		{
			for (byte j = 0; j < map[i].length; j++)
			{
				if (isPieceAt(piece, i, j))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * @param piece
	 * @return (y) position of piece
	 */
	public byte getPieceY(Piece piece)
	{
		if (piece == null)
			return -1;
		
		for (byte i = 0; i < map.length; i++)
		{
			for (byte j = 0; j < map[i].length; j++)
			{
				if (isPieceAt(piece, i, j))
				{
					return j;
				}
			}
		}
		return -1;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return Piece at (x,y)
	 */
	public Piece getPieceAt(byte x, byte y)
	{
		return map[x][y];
	}
	
	/**
	 * @param xi
	 * @param yi
	 * @param xf
	 * @param yf
	 * @return Successful
	 * @deprecated
	 */
	public boolean move(byte xi, byte yi, byte xf, byte yf)
	{
		if (isEmptyPos(xi, yi))
			return false;
		
		return move(getPieceAt(xi, yi), xf, yf);
	}
	
	/**
	 * @param piece
	 * @param x
	 * @param y
	 * @return Successful
	 */
	public boolean move(Piece piece, byte x, byte y)
	{
		if (piece == null || !piece.isValidMove(x, y))
			return false;
		if (!isEmptyPos(x, y))
			return false;
		
		map[getPieceX(piece)][getPieceY(piece)] = null;
		map[x][y] = piece;
		return true;
	}
}
