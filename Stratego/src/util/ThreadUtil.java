/**
 * File: ThreadUtil.java
 * Package: Stratego.util.ThreadUtil
 * Creation: 18/01/2014 at 11:50:27
 */

package util;

/**
 * @author camiloasc1
 * 
 */
public final class ThreadUtil
{
	/**
	 * @param t
	 *            the length of time to sleep in milliseconds
	 */
	public static void wait(int t)
	{
		try
		{
			Thread.sleep(t);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * 
	 */
	private ThreadUtil()
	{
	}
	
}
