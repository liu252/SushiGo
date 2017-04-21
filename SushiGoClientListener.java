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
			SushiGoGame game = new SushiGoGame(2); //For test purposes, we are going to stick to 2 players until we can get the game working
			int playerTurn = 2; //Again, for test purposes. Will figure out another way to keep track of player's turn
			while(true)
			{
				//String serverText = serverInput.readLine();
				//Converts interger input to the cardname output
				String serverText = serverInput.readLine();
				System.out.println("The other terminal typed: " + serverText);
				String cardName = "";
				if(serverText.equals("1"))
				{
					cardName = "Pudding";				
				}
				else if(serverText.equals("2"))
				{
					cardName = "Egg Nigiri";				
				}
				else if(serverText.equals("3"))
				{
					cardName = "Salmon Nigiri";				
				}
				else if(serverText.equals("4"))
				{
					cardName = "Squid Nigiri";				
				}
				else if(serverText.equals("5"))
				{
					cardName = "Maki Roll(1)";				
				}
				else if(serverText.equals("6"))
				{
					cardName = "Maki Roll(2)";				
				}
				else if(serverText.equals("7"))
				{
					cardName = "Maki Roll(3)";				
				}
				else if(serverText.equals("8"))
				{
					cardName = "Dumpling";				
				}
				else if(serverText.equals("9"))
				{
					cardName = "Tempura";				
				}
				else if(serverText.equals("10"))
				{
					cardName = "Wasabi";				
				}
				else if(serverText.equals("11"))
				{
					cardName = "Sashimi";				
				}
				else if(serverText.equals("12"))
				{
					cardName = "Chopsticks)";				
				}
				else
				{
					cardName = "Invalid Card Option";
				}

				if(serverInput != null)
				{
					//System.out.println(serverText);
					System.out.println("Opposing player played: " + cardName);
					System.out.println("Cards in hand:");
					//still testing
					//Each Client has their own player 1 and player 2 hands. Still a working progress
					if (playerTurn == 1)
					{
						game.showCards(1);
						playerTurn = 2;
					}

					else if (playerTurn == 2)
					{
						game.showCards(2);
						playerTurn = 1;
					}
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
