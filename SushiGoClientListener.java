import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;

public class SushiGoClientListener implements Runnable
{
	private Socket connectionSock = null;
	public String outputString = "";
	public boolean readOutput = true;
	private int serverTextInt = 0;
	private boolean start = false;
	private String cardName = "Nothing";
	private String data;
	public int playerTurn = 1;
	int cardNum;
	SushiGoClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
		try
		{
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			SushiGoGame game = new SushiGoGame(2); //For test purposes, we are going to stick to 2 players until we can get the game working
			while(true)
			{
				String serverText = serverInput.readLine();
				//Converts interger input to the cardname output
				//int serverText = Integer.parseInt(serverInput.readLine());
				System.out.println("The other terminal typed: " + serverText);
				if(serverInput != null)
				{
					//System.out.println(serverText);
					if(serverText.equals("your turn"))
					{
						System.out.println("Opposing player played: " + cardName);
						System.out.println("Cards in hand:");
						//still testing
						//Each Client has their own player 1 and player 2 hands. Still a working progress
						if (game.isPlayer1)
						{
							game.showCards(1);
							game.isPlayer1 = false;
						}
						else 
						{
							game.showCards(2);
							game.isPlayer1 = true;
						}
						
						Scanner keyboard = new Scanner(System.in);
						cardNum = keyboard.nextInt();
						game.Move(playerTurn, cardNum);
						
						//Turn the string into the proper format
						String outputString = Integer.toString(cardNum);
						//Create an output stream and send the string off.
						DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
						serverOutput.writeBytes(outputString+"\n");
						//System.out.println("yolo6");
						
					}
				}
				else
				{
					System.out.println("Closing connection for socket" + connectionSock);
					connectionSock.close();
					break;
				}
			}
			//If the program gets here, it exits.
			System.out.println("Thanks for playing SushiGo!");
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}
