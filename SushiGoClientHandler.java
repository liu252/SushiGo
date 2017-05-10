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
	SushiGoGame game = null;
	//Constructor implemented in Sushi Go Server class.
	SushiGoClientHandler(Socket sock, ArrayList<Socket> socketList, SushiGoGame g)
	{
		this.connectionSock = sock;
		this.socketList = socketList;
		this.game = g;
	}
		
	public void sendToClient(String clientText)//sends messages to clients
	{
		try
		{
			DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());//creates a packet to send the message in
			clientOutput.writeBytes(clientText + "\n");//puts the message in the packet and sends it
			System.out.println("Sending to client: "+clientText);//displays what the message was to the console
		} catch (Exception e)//if there is an error submitting the message it prints out that there was an error
		{
			System.out.println("Error: " + e.toString());
		}
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
					System.out.println(clientText);
					game.updateState(this,clientText);
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
