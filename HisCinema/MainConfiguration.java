package HisCinema;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;

public class MainConfiguration 
{
	public final static String CONFIG_FILE_LOCATION = "HisCinema/resources/config.txt";
	public static int hisCinemaServerPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int hisCinemaServerPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			hisCinemaServerPort = Integer.parseInt(properties.getProperty("HisCinemaServerPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hisCinemaServerPort;
	}

	public static InetAddress hisCinemaIP() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		String hisCinemaIP = "";
		try
		{
			properties.load(new FileInputStream(configFile));
			hisCinemaIP = properties.getProperty("HisCinemaIP");
			return InetAddress.getByName(hisCinemaIP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
