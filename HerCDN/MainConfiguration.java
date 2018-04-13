package HerCDN;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;

public class MainConfiguration 
{
	public final static String CONFIG_FILE_LOCATION = "HerCDN/resources/config.txt";
	
	public static int herCDNServerPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int herCinemaServerPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			herCinemaServerPort = Integer.parseInt(properties.getProperty("HerCDNServerPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return herCinemaServerPort;
	}
	
	public static InetAddress herCDNIP() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		String hisCinemaIP = "";
		try
		{
			properties.load(new FileInputStream(configFile));
			hisCinemaIP = properties.getProperty("HerCDNIP");
			return InetAddress.getByName(hisCinemaIP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
