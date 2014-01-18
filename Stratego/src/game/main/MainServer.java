/**
 * File: Server.java
 * Package: Sockets.tictactoe.Server
 * Creation: 11/01/2014 at 16:24:15
 */

package game.main;

import entities.Board;
import entities.pieces.Bomb;
import gui.GUI;

import java.io.File;

import net.Action;
import net.Actions;
import net.socket.SocketClient;
import net.socket.SocketServer;
import net.thread.ServerThread;

/**
 * @author camiloasc1
 * 
 */
public final class MainServer
{
	private static SocketClient[] clients = new SocketClient[2];
	private static ServerThread[] threads = new ServerThread[2];
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty("java.library.path", "lib");
		// Init libraries
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl-2.9.0/native/linux/").getAbsolutePath());
		
		GUI.init();
		
		Board board = Board.getInstance();
		board.addPiece(new Bomb(true));
		board.addPiece(new Bomb(false));
		System.out.println(board);
		
		SocketServer socketServer = new SocketServer();
		
		clients[0] = socketServer.accept();
		clients[0].writeObject(new Action(Actions.TURN, true));
		threads[0] = new ServerThread(clients[0]);
		threads[0].start();
		
		clients[1] = socketServer.accept();
		clients[1].writeObject(new Action(Actions.TURN, false));
		threads[1] = new ServerThread(clients[1]);
		threads[1].start();
		
		System.out.println("Playing");
		
		boolean status = true;
		while (status)
		{
			status = false;
			for (ServerThread thread : threads)
			{
				if (thread.isAlive())
				{
					status = true;
				}
				else
				{
					// thread.close();
				}
			}
		}
		
		for (SocketClient client : clients)
		{
			client.close();
		}
		for (ServerThread thread : threads)
		{
			thread.close();
		}
		
		socketServer.close();
	}
	
	/**
	 * 
	 */
	private MainServer()
	{
	}
	
	/**
	 * @return the threads
	 */
	public static ServerThread[] getThreads()
	{
		return threads;
	}
	
	/**
	 * @param threads
	 *            the threads to set
	 */
	public static void setThreads(ServerThread[] threads)
	{
		MainServer.threads = threads;
	}
	
	/**
	 * @return the clients
	 */
	public static SocketClient[] getClients()
	{
		return clients;
	}
	
	/**
	 * @param clients
	 *            the clients to set
	 */
	public static void setClients(SocketClient[] clients)
	{
		MainServer.clients = clients;
	}
	
}
