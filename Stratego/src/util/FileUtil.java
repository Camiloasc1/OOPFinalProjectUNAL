
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Camilo Sanchez
 *
 */
public class FileUtil
{
	
	/**
	 * Serializes a Object into a file
	 * 
	 * @param o Object to be serialized
	 * @param file File output
	 * @return true if successful
	 */
	public static boolean serializeObject(Object o, String file)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(o);
			
			fos.close();
			oos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Deserializes a Object from a file
	 * 
	 * @param file File input
	 * @return Deserialized object
	 */
	public static Object deserializeObject(String file)
	{
		Object o = null;
		try
		{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			o = ois.readObject();
			
			fis.close();
			ois.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		return o;
	}
}
