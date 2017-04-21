import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SushiGoClientHandler implements Runnable
{
	private Socket connectionSock = null;
	private ArrayList<Socket> socketList;

	//Constructor implemented in Sushi Go Server class.
	SushiGoClientHandler(Socket sock, ArrayList<Socket> socketList)
	{
		this.connectionSock = sock;
		this.socketList = socketList;
	}

	public void run()
	{
		try
		{
			System.out.println("Connection made with socket " + connectionSock);
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));

			while(true)
			{
				String clientText = clientInput.readLine();
				if(clientText != null)
				{
					System.out.println("Received: " + clientText);

					//For every socket in the socket list besides the sender, output the sent data.
					for(Socket s : socketList)
					{
						if(s != connectionSock)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes(clientText + "\n");
						}
					}
				}
				else
				{
					System.out.println("Closing connection for socket " + connectionSock);

					socketList.remove(connectionSock);
					connectionSock.close();
					break;
				}
			}
		}
		catch(Exception e)
		{
			//Removes the socket from array list in the event of an error.
			System.out.println("Error: " + e.toString());
			socketList.remove(connectionSock);
		}
	}
}
