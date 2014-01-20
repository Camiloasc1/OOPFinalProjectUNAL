
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSerializator
{
	public static boolean serializeObject(Object o, String file)
	{
		try
		{
			FileOutputStream fStream = new FileOutputStream(file);
			ObjectOutputStream oStream = new ObjectOutputStream(fStream);
			
			oStream.writeObject(o);
			
			fStream.close();
			oStream.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println(e);
			return false;
		}
		catch (IOException e)
		{
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	public static Object deserializeObject(String file)
	{
		Object obj = null;
		try
		{
			FileInputStream fStream = new FileInputStream(file);
			ObjectInputStream oStream = new ObjectInputStream(fStream);
			
			obj = oStream.readObject();
			
			fStream.close();
			oStream.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println(e);
			return null;
		}
		catch (IOException e)
		{
			System.err.println(e);
			return null;
		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e);
			return null;
		}
		return obj;
	}
}
