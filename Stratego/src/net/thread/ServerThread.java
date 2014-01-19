/**
 * File: ServerThread.java
 * Package: Sockets.tictactoe.ServerThread
 * Creation: 13/01/2014 at 17:41:59
 */

package net.thread;

import net.Action;
import net.Actions;
import net.socket.SocketClient;
import util.ThreadUtil;
import entities.Board;
import game.main.MainServer;

/**
 * @author camiloasc1
 * 
 */
public final class ServerThread extends Thread implements AutoCloseable
{
	public static final int DELAY = 50;
	
	private volatile boolean run;
	private boolean player;
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
	}
	
	/**
	 * @param socketClient
	 * @param hasTurn
	 */
	public ServerThread(SocketClient socketClient, boolean hasTurn)
	{
		super();
		this.socketClient = socketClient;
		this.hasTurn = hasTurn;
		run = true;
	}
	
	/**
	 * @return the socketClient
	 */
	public SocketClient getSocketClient()
	{
		return socketClient;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		Run:
		while (run && (socketClient.getSocket() != null) && !socketClient.getSocket().isClosed())
		{
			ThreadUtil.wait(DELAY);
			
			socketClient.writeObject(new Action(Actions.TURN, hasTurn));
			socketClient.writeObject(Board.swapBoard(!player));
			
			Object object = socketClient.readObject(false);
			if (object == null)
			{
				continue Run;
			}
			
			if (object instanceof Action)
			{
				Action action = (Action) object;
				handleAction(action);
			}
			// socketClient.ignoreInput();
		}
		close();
	}
	
	/**
	 * @param action
	 */
	private void handleAction(Action action)
	{
		switch (action.getAction())
		{
			case MOVE:
			{
				if (!hasTurn)
					return;
				
				Board board = Board.getInstance();
				
				if (player)
				{
					if (board.movePiece(Board.SIZE - 1 - action.getX(), Board.SIZE - 1 - action.getY(),
							Board.SIZE - 1 - action.getX2(), Board.SIZE - 1 - action.getY2()))
					{
						changeTurn();
					}
				}
				else
				{
					
					if (board.movePiece(action.getX(), action.getY(), action.getX2(), action.getY2()))
					{
						changeTurn();
					}
				}
				
				int status = -1;
				if ((status = board.check()) != -1)
				{
					switch (status)
					{
						case 0:
						{
							for (ServerThread thread : MainServer.getThreads())
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
						}
						case 1:
						{
							for (ServerThread thread : MainServer.getThreads())
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
						}
						case 2:
						{
							for (ServerThread thread : MainServer.getThreads())
							{
								thread.socketClient.writeObject(new Action(Actions.DRAW));
							}
							break;
						}
						default:
							break;
					}
					// Restart
					Board.reset();
					socketClient.ignoreInput();
					// socketClient.writeObject(new Action(Actions.EXIT));
					// close();
				}
				break;
			}
			case INIT:
			{
				player = action.getPlayer();
				Board.addPlayerPieces(!action.getPlayer(), action.getBoard());
				break;
			}
			case EXIT:
			{
				close();
				break;
			}
			default:
				break;
		}
	}
	
	/**
	 * 
	 */
	private static void changeTurn()
	{
		for (ServerThread thread : MainServer.getThreads())
		{
			if (thread == null)
			{
				continue;
			}
			thread.hasTurn = !thread.hasTurn;
			thread.socketClient.writeObject(new Action(Actions.TURN, thread.hasTurn));
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
}
