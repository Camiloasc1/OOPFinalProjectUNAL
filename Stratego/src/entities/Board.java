
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
	
	private Piece[][] map;
	
	/**
	 * Constructor of the unique instance of Board
	 */
	private Board()
	{
		super();
		map = new Piece[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
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
	 * @return the size
	 */
	public static int getSize()
	{
		return SIZE;
	}

	/**
	 * @param x
	 * @param y
	 * @return true if position (x,y) is empty
	 */
	public boolean isEmptyPos(byte x, byte y)
	{
		if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7))
			return false;
		
		return (map[x][y] == null);
	}
	
	/**
	 * @param piece
	 * @param x
	 * @param y
	 * @return true if piece is at (x,y)
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
		
		for (byte i = 0; i < SIZE; i++)
		{
			for (byte j = 0; j < SIZE; j++)
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
		
		for (byte i = 0; i < SIZE; i++)
		{
			for (byte j = 0; j < SIZE; j++)
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
	 * @param x
	 * @param y
	 * @return true if piece has moved to (x,y)
	 */
	public boolean movePiece(Piece piece, byte x, byte y)
	{
		map[Board.getBoard().getPieceX(piece)][Board.getBoard().getPieceY(piece)] = null;
		map[x][y] = piece;
		return true;
	}
	
	/**
	 * @param piece
	 * @return true if piece added
	 */
	public boolean addPiece(Piece piece)
	{
		for (byte i = 0; i < SIZE; i++)
		{
			if (i == 4 || i == 5)
				continue;
			for (byte j = 0; j < SIZE; j++)
			{
				if (isEmptyPos(i, j))
				{
					map[i][j] = piece;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param piece
	 * @return true if piece has removed
	 */
	public boolean removePiece(Piece piece)
	{
		if (piece == null)
			return false;
		
		map[Board.getBoard().getPieceX(piece)][Board.getBoard().getPieceY(piece)] = null;
		return true;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return true if piece at (x,y) has removed
	 */
	public boolean removePiece(byte x, byte y)
	{
		return removePiece(getPieceAt(x, y));
	}
}
