/**
 * File: Client.java
 * Package: Sockets.tictactoe.Client
 * Creation: 11/01/2014 at 16:24:06
 */

package net;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author camiloasc1
 * 
 */
public final class SocketClient implements AutoCloseable
{
	/**
	 * 
	 */
	public static final String HOST = "localhost";
	/**
	 * 
	 */
	public static final int PORT = SocketServer.PORT;
	
	private Socket socket;
	
	/**
	 * 
	 */
	public SocketClient()
	{
		this(HOST, PORT);
	}
	
	/**
	 * @param host
	 * @param port
	 */
	public SocketClient(String host, int port)
	{
		super();
		try
		{
			socket = new Socket(host, port);
		}
		catch (UnknownHostException e)
		{
			System.err.println(e);
			close();
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println(e);
			close();
			System.exit(1);
		}
	}
	
	/**
	 * @param socket
	 */
	public SocketClient(Socket socket)
	{
		super();
		this.socket = socket;
	}
	
	/**
	 * @return the socket
	 */
	public synchronized final Socket getSocket()
	{
		return socket;
	}
	
	/**
	 * @return Object the object read from the socket stream
	 */
	public final Object readObject()
	{
		Object object = null;
		
		if (isClosed())
			return null;
		
		try
		{
			// ObjectInputStream always wait for data
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			object = objectInputStream.readObject();
		}
		catch (EOFException e)
		{
			// EOF EndOfFile
			System.err.println(e);
		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e);
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
		
		return object;
	}
	
	/**
	 * @throws IOException
	 */
	public void ignoreInput()
	{
		try
		{
			InputStream inputStream = socket.getInputStream();
			inputStream.skip(inputStream.available());
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}
	
	/**
	 * @param object
	 *            the object to be written
	 */
	public final void writeObject(Object object)
	{
		if (isClosed())
			return;
		
		try
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(object);
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public final void close()// throws Exception
	{
		if (isClosed())
			return;
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
		socket = null;
	}
	
	/**
	 * @return true if the socket has been closed
	 */
	public boolean isClosed()
	{
		return (socket == null || socket.isClosed());
	}
}
