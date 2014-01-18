/**
 * File: ServerThread.java
 * Package: Sockets.tictactoe.ServerThread
 * Creation: 13/01/2014 at 17:41:59
 */

package net;

import java.net.SocketException;

import net.socket.SocketClient;
import entities.Board;

/**
 * @author camiloasc1
 * 
 */
public final class ServerThread extends Thread implements AutoCloseable
{
	public static final int WAITTIME = 10;
	public static final int DELAY = 50;
	
	private volatile boolean run;
	private boolean hasTurn;
	private SocketClient socketClient;
	
	/**
	 * 
	 */
	public ServerThread()
	{
		super();
		socketClient = null;
		run = true;
		try
		{
			socketClient.getSocket().setSoLinger(true, WAITTIME);
		}
		catch (SocketException e)
		{
			System.err.println(e);
		}
	}
	
	/**
	 * @param socketClient
	 */
	public ServerThread(SocketClient socketClient)
	{
		super();
		this.socketClient = socketClient;
		run = true;
		try
		{
			socketClient.getSocket().setSoLinger(true, WAITTIME);
		}
		catch (SocketException e)
		{
			System.err.println(e);
		}
	}
	
	/**
	 * @return the hasTurn
	 */
	public boolean hasTurn()
	{
		return hasTurn;
	}
	
	/**
	 * @param hasTurn
	 *            the hasTurn to set
	 */
	public void setHasTurn(boolean hasTurn)
	{
		this.hasTurn = hasTurn;
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
			// socketClient.writeObject(new Action(hasTurn));
			socketClient.writeObject(Board.getInstance());
			
			if (hasTurn)
			{
				Object object = socketClient.readObject();
				if (object != null)
				{
					if (object instanceof Action)
					{
						Action action = (Action) object;
						switch (action.getAction())
						{
							case MOVE:
							{
								Board board = Board.getInstance();
								
								if (board.movePiece(action.getPiece(), action.getX(), action.getY()))
								{
									changeTurn();
								}
								
								int status = -1;
								if ((status = board.check()) != -1)
								{
									switch (status)
									{
										case 0:
											for (ServerThread thread : Server.getThreads())
											{
												thread.socketClient.writeObject(Board.getInstance());
												if (this == thread)
												{
													thread.socketClient.writeObject(new Action(Actions.WIN));
												}
												else
												{
													thread.socketClient.writeObject(new Action(Actions.LOSE));
												}
											}
											break;
										case 1:
											for (ServerThread thread : Server.getThreads())
											{
												thread.socketClient.writeObject(Board.getInstance());
												if (this == thread)
												{
													thread.socketClient.writeObject(new Action(Actions.WIN));
												}
												else
												{
													thread.socketClient.writeObject(new Action(Actions.LOSE));
												}
											}
											break;
										case 2:
											for (ServerThread thread : Server.getThreads())
											{
												thread.socketClient.writeObject(new Action(Actions.DRAW));
											}
											break;
										
										default:
											break;
									}
									// Restart
									Board.reset();
									socketClient.ignoreInput();
									// socketClient.writeObject(new Action(Actions.EXIT));
									// close();
								}
								// changeTurn();
								break;
							}
							case EXIT:
							{
								for (ServerThread thread : Server.getThreads())
								{
									if (thread != this) // do not close my self
									{
										thread.socketClient.writeObject(new Action(Actions.EXIT));
										thread.close();
									}
								}
								socketClient.writeObject(new Action(Actions.EXIT));
								close();
								break;
							}
							default:
								break;
						}
					}
				}
			}
			else
			{
				socketClient.ignoreInput();
			}
			
			wait(DELAY);
		}
		close();
	}
	
	/**
	 * 
	 */
	private static void changeTurn()
	{
		for (ServerThread thread : Server.getThreads())
		{
			thread.hasTurn = !thread.hasTurn;
			thread.socketClient.writeObject(new Action(Actions.TURN, thread.hasTurn));
			// thread.socketClient.writeObject(Board.getInstance());
		}
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
