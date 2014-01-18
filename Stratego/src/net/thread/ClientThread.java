/**
 * File: ClientThread.java
 * Package: Stratego.net.ClientThread
 * Creation: 17/01/2014 at 20:22:05
 */

package net.thread;

import net.Action;
import net.socket.SocketClient;
import entities.Board;

/**
 * @author camiloasc1
 * 
 */
public final class ClientThread extends Thread implements AutoCloseable
{
	private volatile boolean run;
	private SocketClient socketClient;
	private Board board;
	private boolean player;
	private boolean hasTurn;
	
	/**
	 * 
	 */
	public ClientThread()
	{
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		while (run && (socketClient != null) && !socketClient.getSocket().isClosed())
		{
			Object object = socketClient.readObject();
			if (object != null)
			{
				if (object instanceof Board)
				{
					board = (Board) object;
				}
				else if (object instanceof Action)
				{
					Action action = (Action) object;
					switch (action.getAction())
					{
						case EXIT:
						{
							close();
							break;
						}
						case INIT:
						{
							player = action.getPlayer();
							break;
						}
						case TURN:
						{
							hasTurn = action.getTurn();
							break;
						}
						case DRAW:
							break;
						case LOSE:
							break;
						case WIN:
							break;
						default:
							break;
					}
				}
			}
		}
		close();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close()// throws Exception
	{
		run = false;
		socketClient.close();
	}
	
	/**
	 * @param t
	 *            the length of time to sleep in milliseconds
	 */
	private static void wait(int t)
	{
		try
		{
			Thread.sleep(t);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return;
	}
}
