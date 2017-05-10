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
	public boolean readOutput = false;
	private int numDisplay = 0;
	private int round = 1;
	int x = 11;
	SushiGoClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
		try
		{
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while (true)
			{

				// Get data sent from the server
				String serverText = serverInput.readLine();
				if (serverInput != null)
				{
					try
					{
						String[] items = serverText.split(",");
						String state = items[0];
						
						
						
						if (state.length() == 3 && state.equals("win"))
						{
							//If the user wins, alert the user and break the loop.
							
							System.out.println("You win!");
							System.exit(0);
							break;
						} 
						else if (state.length() == 4 && state.equals("lose"))
						{
							//If the user loses, alert the user and break the loop.
							System.out.println("You lose!");
							System.exit(0);
							break;
						} 
						else if (state.length() == 4 && state.equals("wait"))
						{
							//If the user has to wait, go to the top of the loop.
							System.out.println("Wait for your turn!");
							continue;
						} 
						else if (state.length() == 3 && state.equals("new"))
						{
							if(round < 4)
							{
								System.out.println(" ");
								System.out.println("-------------------------------------------------------");
								System.out.println("-------------------------------------------------------");
								System.out.println("Round : " + round);
								System.out.println("-------------------------------------------------------");
								System.out.println("-------------------------------------------------------");
								System.out.println(" ");
								round++;
								
							}
							else
								System.out.println("Type 1 to see results");
							continue;
							
						}
						else
						{
							//If it's the user's turn, alert them.
							System.out.println("-------------------------------------------------------");
							System.out.println("Player " + 1 + " points: " + items[12]);
							System.out.println("Player " + 2 + " points: " + items[13]);
							System.out.println("-------------------------------------------------------");
							System.out.println(" ");
							if(items[11].equals("tableCards"))
							{
								System.out.println("-------------------------------------------------------");
								System.out.println("Player 1 Table Cards: ");
								for(int i = 14; i < 24; ++i)
								{
									
									System.out.println(i-13 + "." + items[i]);
								}
								System.out.println("Player 2 Table Cards: ");
								for(int i = 24; i < 34; ++i)
								{
									
									System.out.println(i-23 + "." + items[i]);
								}
								System.out.println("-------------------------------------------------------");
							}
							System.out.println("It's your turn!");

							//Parse items 1-4 into an array of ints.
							//Show the user the game state.
							for(int i = 1; i < 11; ++i)
							{
          							System.out.println(i + "." + items[i]);
							}
						}
						

						
						

					} 
					catch (Exception e)
					{
						//If there was a problem, alert the user.
						System.out.println("Couldn't parse serverText: "+serverText);
						System.out.println(e.getMessage());
					}
					Scanner keyboard = new Scanner(System.in);
					int row=-1;

					System.out.println("Which card would you like to play?");
					keyboard = new Scanner(System.in);
					row = keyboard.nextInt();
					//Turn the string into the proper format
					String outputString = Integer.toString(row);
					//Create an output stream and send the string off.
					DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
					serverOutput.writeBytes(outputString+"\n");
					//System.out.println(outputString);
				}
				else
				{
					// Connection was lost
					System.out.println("Closing connection for socket " + connectionSock);
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
