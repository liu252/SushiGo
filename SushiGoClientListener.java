import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SushiGoClientListener implements Runnable
{
	private Socket connectionSock = null;

	SushiGoClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
		try
		{
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while(true)
			{
				String serverText = serverInput.readLine();
				if(serverInput != null)
				{
					System.out.println(serverText);
				}
				else
				{
					System.out.println("Closing connection for socket" + connectionSock);
					connectionSock.close();
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}