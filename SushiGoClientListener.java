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
						System.out.println(isPlayer1);
						System.out.println("Cards in hand:");
						//test
						

						//SushiGoServer.game.showCards(1);
						//SushiGoServer.game.showCards(2);
							
						//Scanner keyboard = new Scanner(System.in);
						//cardNum = keyboard.nextInt();
							
						
						//SushiGoServer.game.Move(1, cardNum);
						//SushiGoServer.game.isPlayer1 = false;
						//System.out.println("end of player 1 turn");

						//test
						//Each Client has their own player 1 and player 2 hands. Still a working progress
						if (SushiGoServer.game.isPlayer1)
						{
							SushiGoServer.game.showCards(1);
							SushiGoServer.game.showCards(2);
							
							Scanner keyboard = new Scanner(System.in);
							cardNum = keyboard.nextInt();
							
							SushiGoServer.game.Move(1, cardNum);
							SushiGoServer.game.isPlayer1 = false;
							System.out.println("end of player 1 turn");
						}
						else if (SushiGoServer.game.isPlayer1 == false)
						{
							SushiGoServer.game.showCards(1);
							SushiGoServer.game.showCards(2);
							//game.handler2.sendToClient("Hi There2");
							
							Scanner keyboard = new Scanner(System.in);
							cardNum = keyboard.nextInt();
							SushiGoServer.game.Move(2, cardNum);
							SushiGoServer.game.displayTableCards();
							SushiGoServer.game.swapCards();
							SushiGoServer.game.isPlayer1 = true;
							if(SushiGoServer.game.player1Cards.length == 0)
							{
								SushiGoServer.game.round = SushiGoServer.game.round + 1;
								SushiGoServer.game.countPoints();
								if(SushiGoServer.game.round > 3)
									SushiGoServer.game.endGame();
								else
									SushiGoServer.game.refreshRound();
							}
							System.out.println("end of player 2 turn");
						}
						
						
						//Turn the string into the proper format
						String outputString = "your turn";
						//Create an output stream and send the string off.
						DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
						serverOutput.writeBytes(outputString+"\n");
						
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
