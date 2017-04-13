import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class SushiGoClient
{
	public static void main(String[] args)
	{
		try
		{
			String hostname = "LocalHost";
			int port = 7890;

			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(hostname, port);

			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());

			System.out.println("Connection made.");

			//Creates new instance of the listener class and starts a thread using it.
			SushiGoClientListener listener = new SushiGoClientListener(connectionSock);
			Thread theThread = new Thread(listener);
			theThread.start();

			Scanner keyboard = new Scanner(System.in);
			while(true)
			{
				String data = keyboard.nextLine();
				serverOutput.writeBytes(data + "\n");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}