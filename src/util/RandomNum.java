
package util;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Code from POO project
 * @param min
 *            Lowest number
 * @param max
 *            Highest number
 * @return
 *         (Math.random() * ((max - min) + 1) + min)
 */
public class RandomNum
{
	public static byte getByte()
	{
		return getByte(Byte.MAX_VALUE - 1);
	}
	
	public static byte getByte(int max)
	{
		return getByte(0, max);
	}
	
	public static byte getByte(int min, int max)
	{
		return (byte) (Math.random() * ((max - min) + 1) + min);
	}
	
	public static short getShort()
	{
		return getShort(Short.MAX_VALUE - 1);
	}
	
	public static short getShort(int max)
	{
		return getShort(0, max);
	}
	
	public static short getShort(int min, int max)
	{
		return (short) (Math.random() * ((max - min) + 1) + min);
	}
	
	public static int getInt()
	{
		return getInt(Integer.MAX_VALUE - 1);
	}
	
	public static int getInt(int max)
	{
		return getInt(0, max);
	}
	
	public static int getInt(int min, int max)
	{
		return (int) (Math.random() * ((max - min) + 1) + min);
	}
	
	public static long getLong()
	{
		return getLong(Long.MAX_VALUE - 1);
	}
	
	public static long getLong(long max)
	{
		return getLong(0, max);
	}
	
	public static long getLong(long min, long max)
	{
		return (long) (Math.random() * ((max - min) + 1) + min);
	}
	
	public static boolean getBool()
	{
		return (getByte(0, Byte.MAX_VALUE) % 2 == 0);
	}
	
	public static Object fromTreeSet(TreeSet<Object> tree)
	{
		ArrayList<Object> array = new ArrayList<Object>(tree);
		return array.get(getInt(array.size() - 1));
	}
}
