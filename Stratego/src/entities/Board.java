
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

public class Board implements Serializable/* , Cloneable */
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -333970939198499361L;
	private static Board gameBoard;
	
	int[][] map;
	
	private Board()
	{
		super();
		map = new int[10][10];
	}
	
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
}
