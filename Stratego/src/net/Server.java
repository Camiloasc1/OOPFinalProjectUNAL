/**
 * File: Server.java
 * Package: Sockets.tictactoe.Server
 * Creation: 11/01/2014 at 16:24:15
 */

package net;

import net.socket.SocketClient;
import net.socket.SocketServer;

/**
 * @author camiloasc1
 * 
 */
public final class Server
{
	private static ServerThread[] threads;
	private static SocketClient[] clients;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SocketServer socketServer = new SocketServer();
		
		clients = new SocketClient[2];
		threads = new ServerThread[2];
		
		clients[0] = socketServer.accept();
		clients[0].writeObject(new Action(Actions.TURN, false));
		threads[0] = new ServerThread(clients[0]);
		
		clients[1] = socketServer.accept();
		clients[1].writeObject(new Action(Actions.TURN, true));
		threads[1] = new ServerThread(clients[1]);
		
		System.out.println("Playing");
		threads[0].setHasTurn(true);
		threads[1].setHasTurn(false);
		threads[0].start();
		threads[1].start();
		
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
	private Server()
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
		Server.threads = threads;
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
		Server.clients = clients;
	}
	
}
