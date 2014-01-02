
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

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
public final class Board implements Serializable, Iterable<Piece>
{
	private static final long serialVersionUID = -333970939198499361L;
	public static final byte SIZE = 10;
	private static volatile Board INSTANCE = new Board();
	
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
	public static Board getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// return getInstance();
	}
	
	/**
	 * @return the size
	 */
	public static byte getSize()
	{
		return SIZE;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return matrix file for x coordinate
	 */
	protected byte convertX(byte x, byte y)
	{
		return (byte) (SIZE - y - 1);
		
	}
	
	/**
	 * @param x
	 * @param y
	 * @return matrix column for y coordinate
	 */
	protected byte convertY(byte x, byte y)
	{
		return x;
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
		
		if ((y == 4 || y == 5) && (x == 2 || x == 3 || x == 6 || x == 7))
			return false;
		return (map[convertX(x, y)][convertY(x, y)] == null);
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
		
		return (map[convertX(x, y)][convertY(x, y)] == piece);
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
		return map[convertX(x, y)][convertY(x, y)];
	}
	
	/**
	 * @param x
	 * @param y
	 * @return true if piece has moved to (x,y)
	 */
	public boolean movePiece(Piece piece, byte x, byte y)
	{
		map[Board.getInstance().getPieceX(piece)][Board.getInstance().getPieceY(piece)] = null;
		map[convertX(x, y)][convertY(x, y)] = piece;
		return true;
	}
	
	/**
	 * @param piece
	 * @return true if piece added
	 */
	public boolean addPiece(Piece piece)
	{
		for (byte j = 0; j < SIZE; j++)
		{
			if (j == 4 || j == 5)
				continue;
			for (byte i = 0; i < SIZE; i++)
			{
				if (isEmptyPos(i, j))
				{
					map[convertX(i, j)][convertY(i, j)] = piece;
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
		
		map[Board.getInstance().getPieceX(piece)][Board.getInstance().getPieceY(piece)] = null;
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
	
	@Override
	public Iterator<Piece> iterator()
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Piece piece;
		for (byte j = 0; j < SIZE; j++)
		{
			for (byte i = 0; i < SIZE; i++)
			{
				piece = getPieceAt(i, j);
				if (piece != null)
				{
					pieces.add(piece);
				}
			}
		}
		return pieces.iterator();
	}
}
