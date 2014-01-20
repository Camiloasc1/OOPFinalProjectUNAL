/**
 * File: Client.java
 * Package: Sockets.tictactoe.Client
 * Creation: 11/01/2014 at 16:24:06
 */

package net.socket;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
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
		setSoLinger(socket);
	}
	
	/**
	 * @param socket
	 */
	public SocketClient(Socket socket)
	{
		super();
		this.socket = socket;
		setSoLinger(this.socket);
	}
	
	/**
	 * @param socket
	 */
	private void setSoLinger(Socket socket)
	{
		try
		{
			socket.setSoLinger(true, 10);
		}
		catch (SocketException e)
		{
			System.err.println(e);
		}
	}
	
	/**
	 * @return the socket
	 */
	public synchronized final Socket getSocket()
	{
		return socket;
	}
	
	/**
	 * @see java.io.ObjectInputStream#readObject()
	 */
	public Object readObject()
	{
		return readObject(true);
	}
	
	/**
	 * @see java.io.ObjectInputStream#readObject()
	 */
	public Object readObject(boolean wait)
	{
		if (isClosed())
			return null;
		
		if (!wait)
		{
			try
			{
				if (socket.getInputStream().available() == 0)
					return null;
			}
			catch (IOException e)
			{
				System.err.println(e);
			}
		}
		
		Object object = null;
		
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
			close();
		}
		
		return object;
	}
	
	/**
	 * skip all buffered data
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
	 * @see java.io.ObjectOutputStream#writeObject(Object)
	 */
	public void writeObject(Object object)
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
	public void close()// throws Exception
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
	 * @see java.net.Socket#isClosed()
	 */
	public boolean isClosed()
	{
		return ((socket == null) || socket.isClosed());
	}
}
