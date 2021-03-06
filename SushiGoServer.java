import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SushiGoServer
{
	private ArrayList<Socket> socketList;	//ArrayList of all client sockets connected to server.
	public static SushiGoGame game;//creates a game object to use to reference all the methods of the game class
	//System.out.println("game created");
	public SushiGoServer()
	{
		socketList = new ArrayList<Socket>();
		game = new SushiGoGame(2); 
	}

	private void getConnection()
	{
		try
		{
			System.out.println("Waiting for client connections to Sushi Go Server (port 7890)");
			ServerSocket serverSock = new ServerSocket(7890);

			while(true)	//Infinite loop.
			{
					//Accepts a client socket and adds it to the array list.
					Socket connectionSock = serverSock.accept();
					socketList.add(connectionSock);		

					//Creates a new thread based on the updated ClientHandler and starts the thread
					SushiGoClientHandler handler = new SushiGoClientHandler(connectionSock, this.socketList, game);
					Thread theThread = new Thread(handler);
					theThread.start();
					game.addHandler(handler);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		SushiGoServer server = new SushiGoServer();
		server.getConnection();
	}
}
