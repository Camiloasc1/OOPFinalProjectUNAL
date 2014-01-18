/**
 * File: ClientThread.java
 * Package: Stratego.net.ClientThread
 * Creation: 17/01/2014 at 20:22:05
 */

package net.thread;

import net.Action;
import net.Actions;
import net.socket.SocketClient;
import util.ThreadUtil;
import entities.Board;
import gui.states.GameStates;

/**
 * @author camiloasc1
 * 
 */
public final class ClientThread extends Thread implements AutoCloseable
{
	private volatile boolean run;
	private SocketClient socketClient;
	private boolean player;
	private boolean hasTurn;
	
	/**
	 * 
	 */
	public ClientThread()
	{
		super();
		run = true;
		player = false;
		hasTurn = false;
		socketClient = new SocketClient();
	}
	
	/**
	 * @param socketClient
	 */
	public ClientThread(SocketClient socketClient)
	{
		super();
		run = true;
		player = false;
		hasTurn = false;
		this.socketClient = socketClient;
	}
	
	/**
	 * @return the socketClient
	 */
	public SocketClient getSocketClient()
	{
		return socketClient;
	}
	
	/**
	 * @return the player
	 */
	public boolean getPlayer()
	{
		return player;
	}
	
	/**
	 * @return the hasTurn
	 */
	public boolean getHasTurn()
	{
		return hasTurn;
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
			Object object = socketClient.readObject(false);
			if (object == null)
			{
				continue Run;
			}
			
			if (object instanceof Board)
			{
				if (GameStates.getState() == GameStates.INGAME)
				{
					Board board = (Board) object;
					Board.setInstance(board.clone());
				}
			}
			else if (object instanceof Action)
			{
				Action action = (Action) object;
				handleAction(action);
			}
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close()// throws Exception
	{
		run = false;
		socketClient.writeObject(new Action(Actions.EXIT));
		ThreadUtil.wait(500);
		socketClient.close();
	}
	
	/**
	 * @param board
	 */
	public void writeBoard(Board board)
	{
		socketClient.writeObject(new Action(Actions.INIT, board, player));
	}
}
