/**
 * File: ServerSocket.java
 * Package: Sockets.tictactoe.ServerSocket
 * Creation: 12/01/2014 at 20:03:13
 */

package net.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author camiloasc1
 * 
 */
public final class SocketServer implements AutoCloseable
{
	/**
	 * 
	 */
	public static final int PORT = 35557;
	
	private ServerSocket serverSocket;
	
	/**
	 * 
	 */
	public SocketServer()
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException e)
		{
			System.err.println(e);
			close();
			System.exit(1);
		}
	}
	
	/**
	 * @param port
	 */
	public SocketServer(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.err.println(e);
			close();
			System.exit(1);
		}
	}
	
	/**
	 * @param serverSocket
	 */
	public SocketServer(ServerSocket serverSocket)
	{
		super();
		this.serverSocket = serverSocket;
	}
	
	/**
	 * @return
	 * @throws IOException
	 * @see java.net.ServerSocket#accept()
	 */
	public SocketClient accept()// throws IOException
	{
		System.out.println("Waiting for a Client ...");
		
		Socket socket = null;
		try
		{
			socket = serverSocket.accept();
			System.out.println("Incoming connection from: " + socket.getInetAddress());
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
		return new SocketClient(socket);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close()// throws IOException
	{
		if (isClosed())
			return;
		try
		{
			serverSocket.close();
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
		serverSocket = null;
	}
	
	/**
	 * @return true if the socket has been closed
	 */
	private boolean isClosed()
	{
		return serverSocket == null || serverSocket.isClosed();
	}
	
}
