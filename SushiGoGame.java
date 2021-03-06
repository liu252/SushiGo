import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;


public class SushiGoGame
{
  public String[] gameDeck = {
                            "Pudding","Pudding","Pudding","Pudding","Pudding",
                            "Pudding","Pudding","Pudding","Pudding","Pudding",
                            "Egg","Egg","Egg","Egg","Egg",
                            "Salmon","Salmon","Salmon","Salmon","Salmon",
                            "Salmon","Salmon","Salmon","Salmon","Salmon",
                            "Squid","Squid","Squid","Squid","Squid",
                            "Maki_Roll_(1)","Maki_Roll_(1)","Maki_Roll_(1)",
                            "Maki_Roll_(1)","Maki_Roll_(1)","Maki_Roll_(1)",
                            "Maki_Roll_(2)","Maki_Roll_(2)","Maki_Roll_(2)",
                            "Maki_Roll_(2)","Maki_Roll_(2)","Maki_Roll_(2)",
                            "Maki_Roll_(2)","Maki_Roll_(2)","Maki_Roll_(2)",
                            "Maki_Roll_(2)","Maki_Roll_(2)","Maki_Roll_(2)",
                            "Maki_Roll_(3)","Maki_Roll_(3)","Maki_Roll_(3)",
                            "Maki_Roll_(3)","Maki_Roll_(3)","Maki_Roll_(3)",
                            "Maki_Roll_(3)","Maki_Roll_(3)",
                            "Dumpling","Dumpling","Dumpling","Dumpling",
                            "Dumpling","Dumpling","Dumpling","Dumpling",
                            "Dumpling","Dumpling","Dumpling","Dumpling",
                            "Dumpling","Dumpling",
                            "Tempura","Tempura","Tempura","Tempura","Tempura",
                            "Tempura","Tempura","Tempura","Tempura","Tempura",
                            "Tempura","Tempura","Tempura","Tempura",
                            "Wasabi","Wasabi","Wasabi","Wasabi","Wasabi",
                            "Wasabi",
                            "Sashimi","Sashimi","Sashimi","Sashimi","Sashimi",
                            "Sashimi","Sashimi","Sashimi","Sashimi","Sashimi",
                            "Sashimi","Sashimi","Sashimi","Sashimi",
                            "Egg","Egg","Egg","Egg"
                          }; // 108 Cards
  public int[] playerPoints = {0,0,0,0,0};
  public int[] playerPuddingCount = {0,0,0,0,0};
  public int[] playerMakiCount = {0,0,0,0,0};
  public int[] playerDumplingCount = {0,0,0,0,0};
  public int[] playerTempuraCount = {0,0,0,0,0};
  public int[] playerSashimiCount = {0,0,0,0,0};
  public int[] playerWasabiCount = {0,0,0,0,0};
  public int[] playerChopsticksCount = {0,0,0,0,0};
  public String player1Cards[] = new String[10];
  public String player2Cards[] = new String[10];
  public String player3Cards[] = new String[9];
  public String player4Cards[] = new String[8];
  public String player5Cards[] = new String[7];
  public String player1TableCards[] = new String[10];
  public String player2TableCards[] = new String[10];
  public String player3TableCards[] = new String[9];
  public String player4TableCards[] = new String[8];
  public String player5TableCards[] = new String[7];
  public int player1TableNum = 0;
  public int player2TableNum = 0;
  public int player3TableNum = 0;
  public int player4TableNum = 0;
  public int player5TableNum = 0;

  public int round = 1;
  int state;
  int[] rows;
  String playerOne = "";
  String playerTwo = "";
  public SushiGoClientHandler handler1;
  public SushiGoClientHandler handler2;
  boolean player1Connected = false;//variables telling if the players are connected to the game or not
  boolean player2Connected = false;
  boolean isPlayer1 = true;//boolean to track whose turn it is



  public int playerCount = 0;
  public int deckCount =0;

  //Card Info
  //1.Pudding: 10 int PuddingCount
  //2.Egg Nigiri: 5 int Points++
  //3.Salmon Nigiri: 10 int Points+=2
  //4.Squid Nigiri: 5 int Points+=3
  //5.Maki Roll(1): 6 int MakiCount++
  //6.Maki Roll(2): 12 int MakiCount+=2
  //7.Maki Roll(3): 8 int MakiCount +=3
  //8.Dumpling: 14 int DumplingCount++
  //9.Tempura: 14 int TempuraCount++
  //10.Wasabi: 6 boolean Wasabi = true
  //11.Sashimi: 14 int SashimiCount++
  //12.Chopsticks: 4 boolean Chopsticks = true
  //Total: 108
  //Types: 12

  SushiGoGame(int playerNum)
  {
    //Initialize Players
    state = 0;
    playerCount = playerNum;
    shuffleArray(gameDeck);
    for(int i = 0; i < 10; ++i)
    {
    player1TableCards[i] = " ";
    player2TableCards[i] = " ";
    }
    if (playerCount == 2)//10 cards each
    {
      int cardCount = 0;
      for(int x = deckCount; x < (deckCount+20); x+=2)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        cardCount++;
      }
      deckCount = deckCount + 20;
    }
    /*else if (playerCount == 3)//9 cards each
    {
      int cardCount = 0;
      for(int x = deckCount; x < (deckCount+27); x+=3)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        cardCount++;
      }
      deckCount = deckCount + 27;
    }
    else if (playerCount == 4)//8 cards each
    {
      int cardCount = 0;
      for(int x = deckCount; x < (deckCount+32); x+=4)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        player4Cards[cardCount] = gameDeck[x+3];
        cardCount++;
      }
      deckCount = deckCount + 32;
    }
    else if (playerCount == 5)//7 cards each
    {
      int cardCount = 0;
      for(int x = deckCount; x < (deckCount+35); x+=5)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        player4Cards[cardCount] = gameDeck[x+3];
        player5Cards[cardCount] = gameDeck[x+4];
        cardCount++;
      }
      deckCount = deckCount + 35;
    }*/
  }
  
  public void updateState(SushiGoClientHandler hand, String input)
  {
    if (hand==handler1 && !isPlayer1)
    {
      handler1.sendToClient("noturn");
      return;
    }
    if (hand==handler2 && isPlayer1)
    {
      handler2.sendToClient("noturn");
      return;
    }
 
    int value = Integer.parseInt(input);
    
    if(round > 3)
    {
       if(playerPoints[0] < playerPoints[1])//depending on who wins it sends to them if they won or lost
      {
        handler1.sendToClient("lose");
        handler2.sendToClient("win");
      }
      else
      {
        handler2.sendToClient("lose");
        handler1.sendToClient("win");
      }
    }
    
    if(isPlayer1)//if it is player 1's turn and it's swithcing to player 2's turn it updates to tell player 1 to wait and player 2 to play
    {
       Move(1, value);
      handler1.sendToClient("wait" + "," + player1Cards[0] + "," + player1Cards[1] + "," + player1Cards[2] + "," + player1Cards[3] + "," + player1Cards[4] + "," + player1Cards[5] + "," + player1Cards[6] + "," + player1Cards[7] + "," + player1Cards[8] + "," + player1Cards[9] + "," + "notableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
      handler2.sendToClient("play" + "," + player2Cards[0] + "," + player2Cards[1] + "," + player2Cards[2] + "," + player2Cards[3] + "," + player2Cards[4] + "," + player2Cards[5] + "," + player2Cards[6] + "," + player2Cards[7] + "," + player2Cards[8] + "," + player2Cards[9] + "," + "tableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
      isPlayer1 = false;//switches to player 2's turn
     
    }
    else//if its switching from player 2 to player 1's turn tells player 2 to wait and player 1 to play
    {
	Move(2, value);
        handler2.sendToClient("wait" + "," + player2Cards[0] + "," + player2Cards[1] + "," + player2Cards[2] + "," + player2Cards[3] + "," + player2Cards[4] + "," + player2Cards[5] + "," + player2Cards[6] + "," + player2Cards[7] + "," + player2Cards[8] + "," + player2Cards[9] + "," + "notableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
        handler1.sendToClient("play" + "," + player1Cards[0] + "," + player1Cards[1] + "," + player1Cards[2] + "," + player1Cards[3] + "," + player1Cards[4] + "," + player1Cards[5] + "," + player1Cards[6] + "," + player1Cards[7] + "," + player1Cards[8] + "," + player1Cards[9] + "," + "tableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
        isPlayer1 = true;
	
    }

  }

  static void shuffleArray(String[] ar)
  {
  // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      String a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
  /*public void showCards(int playerNum)
  {
    if (playerNum == 1)
    {
      if (playerCount == 2)
      {
        for (int x = 0; x < 10; x++)
        {
          int numDisplay = x+1;
          System.out.println(numDisplay + "." + player1Cards[x] + " ");
	  //handler1.sendToClient(numDisplay + "." + player1Cards[x] + " ");
        }
      }
      else if (playerCount == 3)
      {
        for (int x = 0; x < 9; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player1Cards[x] + " ");
        }
      }
      else if (playerCount == 4)
      {
        for (int x = 0; x < 8; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player1Cards[x] + " ");
        }
      }
      else if (playerCount == 5)
      {
        for (int x = 0; x < 7; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player1Cards[x] + " ");
        }
      }
     }
     else if (playerNum == 2)
     {
       if (playerCount == 2)
       {
         for (int x = 0; x < 10; x++)
         {
           int numDisplay = x+1;
           System.out.println(numDisplay + "." + player2Cards[x] + " ");
          // handler2.sendToClient(numDisplay + "." + player2Cards[x] + " ");
         }
       }
       else if (playerCount == 3)
       {
         for (int x = 0; x < 9; x++)
         {
           int numDisplay = x+1;
           System.out.print(numDisplay + "." + player2Cards[x] + " ");
         }
       }
       else if (playerCount == 4)
       {
         for (int x = 0; x < 8; x++)
         {
           int numDisplay = x+1;
           System.out.print(numDisplay + "." + player2Cards[x] + " ");
         }
       }
       else if (playerCount == 5)
       {
         for (int x = 0; x < 7; x++)
         {
           int numDisplay = x+1;
           System.out.print(numDisplay + "." + player2Cards[x] + " ");
         }
       }
    }
    else if (playerNum == 3)
    {
      if (playerCount == 3)
      {
        for (int x = 0; x < 9; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player3Cards[x] + " ");
        }
      }
      else if (playerCount == 4)
      {
        for (int x = 0; x < 8; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player3Cards[x] + " ");
        }
      }
      else if (playerCount == 5)
      {
        for (int x = 0; x < 7; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player3Cards[x] + " ");
        }
      }
    }
    else if (playerNum == 4)
    {
      if (playerCount == 4)
      {
        for (int x = 0; x < 8; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player4Cards[x] + " ");
        }
      }
      else if (playerCount == 5)
      {
        for (int x = 0; x < 7; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player4Cards[x] + " ");
        }
      }
    }
    else if (playerNum == 5)
    {
      for (int x = 0; x < 7; x++)
      {
        int numDisplay = x+1;
        System.out.print(numDisplay + "." + player5Cards[x] + " ");
      }
    }
  }*/

  public void Move(int playerNum, int playerChoice)
  {
    int cardNum = playerChoice - 1;
    if (playerNum == 1)
    {
      String card = player1Cards[cardNum];
      player1Cards[cardNum] = " ";
      points(1 , card);
      player1TableCards[player1TableNum] = card;
      player1TableNum++;
    }
    else if (playerNum == 2)
    {
      String card = player2Cards[cardNum];
      player2Cards[cardNum] = " ";
      points(2 , card);
      player2TableCards[player2TableNum] = card;
      player2TableNum++;
      if(player1TableNum > 9 && player2TableNum > 9)
      {
      	countPoints();
        refreshRound();
	if(round > 3)
		endGame();
	handler1.sendToClient("new");
        handler2.sendToClient("new");
	//displayPoints();
	
      }
      else
      	swapCards();
    }
    /*else if (playerNum == 3)
    {
      String card = player3Cards[cardNum];
      player3Cards[cardNum] = " ";
      points(3 , card);
      player3TableCards[player3TableNum] = card;
      player3TableNum++;
    }
    else if (playerNum == 4)
    {
      String card = player4Cards[cardNum];
      player4Cards[cardNum] = " ";
      points(4 , card);
      player4TableCards[player4TableNum] = card;
      player4TableNum++;
    }
    else if (playerNum == 5)
    {
      String card = player5Cards[cardNum];
      player5Cards[cardNum] = " ";
      points(5 , card);
      player5TableCards[player5TableNum] = card;
      player5TableNum++;
    }*/
  }

  public void displayTableCards()
  {
    if (playerCount == 2)
    {
      //Player 1's Screen
      System.out.println("Player 1 Collected Cards: ");
      for(int x = 0; x < player1TableNum; x++)
      {
        handler1.sendToClient(player1TableCards[x] + ", ");
      }
      System.out.println("");
      System.out.println("Player 2 Collected Cards: ");
      for(int x = 0; x < player2TableNum; x++)
      {
        System.out.println(player2TableCards[x] + ", ");
      }
      System.out.println("");
    }
    /*else if (playerCount == 3)
    {
      System.out.print("Player 1 Collected Cards: ");
      for(int x = 0; x < player1TableNum; x++)
      {
        System.out.print(player1TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 2 Collected Cards: ");
      for(int x = 0; x < player2TableNum; x++)
      {
        System.out.print(player2TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 3 Collected Cards: ");
      for(int x = 0; x < player3TableNum; x++)
      {
        System.out.print(player3TableCards[x] + ", ");
      }
      System.out.println();
    }
    else if (playerCount == 4)
    {
      System.out.print("Player 1 Collected Cards: ");
      for(int x = 0; x < player1TableNum; x++)
      {
        System.out.print(player1TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 2 Collected Cards: ");
      for(int x = 0; x < player2TableNum; x++)
      {
        System.out.print(player2TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 3 Collected Cards: ");
      for(int x = 0; x < player3TableNum; x++)
      {
        System.out.print(player3TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 4 Collected Cards: ");
      for(int x = 0; x < player4TableNum; x++)
      {
        System.out.print(player4TableCards[x] + ", ");
      }
      System.out.println();
    }
    else if (playerCount == 5)
    {
      System.out.print("Player 1 Collected Cards: ");
      for(int x = 0; x < player1TableNum; x++)
      {
        System.out.print(player1TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 2 Collected Cards: ");
      for(int x = 0; x < player2TableNum; x++)
      {
        System.out.print(player2TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 3 Collected Cards: ");
      for(int x = 0; x < player3TableNum; x++)
      {
        System.out.print(player3TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 4 Collected Cards: ");
      for(int x = 0; x < player4TableNum; x++)
      {
        System.out.print(player4TableCards[x] + ", ");
      }
      System.out.println();
      System.out.print("Player 5 Collected Cards: ");
      for(int x = 0; x < player5TableNum; x++)
      {
        System.out.print(player5TableCards[x] + ", ");
      }
      System.out.println();
    }*/
  }

  public void points(int playerNum, String card)
  {
    int player = playerNum - 1;
    if (card.equals("Pudding"))
    {
      playerPuddingCount[player] = playerPuddingCount[player]+1;
    }
    else if (card.equals("Egg") && playerWasabiCount[player] == 0)
    {
      playerPoints[player] = playerPoints[player] + 1;
    }
    else if(card.equals("Egg") && playerWasabiCount[player] >= 1)
    {
      playerPoints[player] = playerPoints[player] + 3;
      playerWasabiCount[player] = playerWasabiCount[player] - 1;
    }
    else if(card.equals("Salmon") && playerWasabiCount[player] == 0)
    {
      playerPoints[player] = playerPoints[player] + 2;
    }
    else if(card.equals("Salmon") && playerWasabiCount[player] >= 1)
    {
        playerPoints[player] = playerPoints[player] + 6;
        playerWasabiCount[player] = playerWasabiCount[player] - 1;
    }
    else if(card.equals("Squid") && playerWasabiCount[player] == 1)
    {
      playerPoints[player] = playerPoints[player] + 3;
    }
    else if(card.equals("Squid") && playerWasabiCount[player] >= 1)
    {
        playerPoints[player] = playerPoints[player] + 9;
        playerWasabiCount[player] = playerWasabiCount[player] - 1;
    }
    else if(card.equals("Maki_Roll_(1)"))
    {
      playerMakiCount[player] = playerMakiCount[player] + 1;
    }
    else if(card.equals("Maki_Roll_(2)"))
    {
      playerMakiCount[player] = playerMakiCount[player] + 2;
    }
    else if(card.equals("Maki_Roll_(3)"))
    {
      playerMakiCount[player] = playerMakiCount[player] + 3;
    }
    else if(card.equals("Dumpling"))
    {
      playerDumplingCount[player] = playerDumplingCount[player] + 1;
    }
    else if(card.equals("Tempura"))
    {
      playerTempuraCount[player] = playerTempuraCount[player] + 1;
    }
    else if(card.equals("Wasabi"))
    {
      playerWasabiCount[player] = playerWasabiCount[player] + 1;
    }
    else if(card.equals("Sashimi"))
    {
      playerSashimiCount[player] = playerSashimiCount[player] + 1;
    }
    else if(card.equals("Chopsicks"))
    {
      playerChopsticksCount[player] = playerChopsticksCount[player] + 1;
    }
  }
  public void swapCards()
  {
    String tempArr[] = new String[10];
    if (playerCount == 2)
    {
      for (int x = 0; x < 10; x++)
      {
        tempArr[x] = player1Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player1Cards[x] = player2Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player2Cards[x] = tempArr[x];
      }
    }
    /*else if (playerCount == 3)
    {
      for (int x = 0; x < 10; x++)
      {
        tempArr[x] = player1Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player1Cards[x] = player2Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player2Cards[x] = player3Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player3Cards[x] = tempArr[x];
      }
    }
    else if (playerCount == 4)
    {
      for (int x = 0; x < 10; x++)
      {
        tempArr[x] = player1Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player1Cards[x] = player2Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player2Cards[x] = player3Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player3Cards[x] = player4Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player4Cards[x] = tempArr[x];
      }
    }
    else if (playerCount == 5)
    {
      for (int x = 0; x < 10; x++)
      {
        tempArr[x] = player1Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player1Cards[x] = player2Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player2Cards[x] = player3Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player3Cards[x] = player4Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player4Cards[x] = player5Cards[x];
      }
      for (int x = 0; x < 10; x++)
      {
        player5Cards[x] = tempArr[x];
      }
    }*/
  }
  public void countPoints()
  {
    int highestMakiPlayer = 0;
    int makiCount = 0;
    for(int x = 0; x < playerCount; x++)
    {
      if (playerDumplingCount[x] == 5)
      {
        playerPoints[x] = playerPoints[x] + 15;
      }
      else if (playerDumplingCount[x] == 4)
      {
        playerPoints[x] = playerPoints[x] + 10;
      }
      else if (playerDumplingCount[x] == 3)
      {
        playerPoints[x] = playerPoints[x] + 6;
      }
      else if(playerDumplingCount[x] == 2)
      {
        playerPoints[x] = playerPoints[x] + 3;
      }
      else if (playerDumplingCount[x] == 1)
      {
        playerPoints[x] = playerPoints[x] + 1;
      }
      //Tempura Points
      if(playerTempuraCount[x] >= 6 && playerTempuraCount[x] <=7)
      {
        playerPoints[x] = playerPoints[x] + 15;
      }
      else if(playerTempuraCount[x] >= 4 && playerTempuraCount[x] <= 5)
      {
        playerPoints[x] = playerPoints[x] + 10;
      }
      else if(playerTempuraCount[x] >= 2 && playerTempuraCount[x] <= 3)
      {
        playerPoints[x] = playerPoints[x] + 5;
      }
      //Sashimi Points
      if(playerSashimiCount[x] >= 9 && playerSashimiCount[x] <= 11)
      {
        playerPoints[x] = playerPoints[x] + 30;
      }
      else if(playerSashimiCount[x] >= 6 && playerSashimiCount[x] <= 8)
      {
        playerPoints[x] = playerPoints[x] + 20;
      }
      else if(playerSashimiCount[x] >= 3 && playerSashimiCount[x] <= 5)
      {
        playerPoints[x] = playerPoints[x] + 10;
      }
      //Maki Count
      if(playerMakiCount[x] > makiCount)
      {
        makiCount = playerMakiCount[x];
        highestMakiPlayer = x;
      }
    }
    playerPoints[highestMakiPlayer] = playerPoints[highestMakiPlayer] + 6;
  }
  public void refreshRound()
  {
    for(int x = 0; x < 5; ++x)
    {
      playerMakiCount[x] = 0;
      playerDumplingCount[x] = 0;
      playerTempuraCount[x] = 0;
      playerSashimiCount[x] = 0;
      playerWasabiCount[x] = 0;
      playerChopsticksCount[x] = 0;
      player1TableNum = 0;
      player2TableNum = 0;
      player3TableNum = 0;
      player4TableNum = 0;
      player5TableNum = 0;
    }
    shuffleArray(gameDeck);
    for(int i = 0; i < 10; ++i)
    {
    player1TableCards[i] = " ";
    player2TableCards[i] = " ";
    }
    if (playerCount == 2)//10 cards each
    {
      int cardCount = 0;
      for(int x = deckCount; x < (deckCount+20); x+=2)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        cardCount++;
      }
      deckCount = deckCount + 20;
    }
    round++;
  }
  public void displayPoints()
  {
    for(int x = 0; x < playerCount; x++)
    {
      int player = x+1;
      System.out.println("Player " + player + " points: " + playerPoints[x]);
      System.out.println("Player " + player + " points: " + playerPoints[x]);
    }
  }
  public void endGame()
  {
    int high = 0;
    int low = 10;
    int highW1 = 6;
    int highW2 = 6;
    int lowL1 = 6;
    int lowL2 = 6;
    for(int x = 0; x < playerCount; x++)
    {
      if(playerPuddingCount[x] > high)
      {
        high = playerPuddingCount[x];
        highW1 = x;
        if(highW2 != 6)
        {
          if(playerPuddingCount[highW1] != playerPuddingCount[highW2])
          {
            highW2 = 6;
          }
        }
      }
      else if(playerPuddingCount[x] == high)
      {
        highW2 = x;
      }

      if(playerPuddingCount[x] < low)
      {
        low = playerPuddingCount[x];
        lowL1 = x;
        if(lowL2 != 6)
        {
          if(playerPuddingCount[lowL1] != playerPuddingCount[lowL2])
          {
            lowL2 = 6;
          }
        }
      }
      else if(playerPuddingCount[x] == low)
      {
        lowL2 = x;
      }
    }
    if(highW2 == 6)
    {
      playerPoints[highW1] = playerPoints[highW1] + 6;
    }
    else
    {
      playerPoints[highW1] = playerPoints[highW1] + 3;
      playerPoints[highW2] = playerPoints[highW2] + 3;
    }
    if(lowL2 == 6)
    {
      playerPoints[lowL1] = playerPoints[lowL1] + 6;
    }
    else
    {
      playerPoints[lowL1] = playerPoints[lowL1] + 3;
      playerPoints[lowL2] = playerPoints[lowL2] + 3;
    }
    int winnerPoints = 0;
    int winner = 6;
    for(int x = 0; x < playerCount; x++)
    {
      System.out.println("Player " + (x+1) + " Score: " + playerPoints[x]);
      System.out.println("Player " + (x+1) + " Score: " + playerPoints[x]);
      if(playerPoints[x] > winnerPoints)
      {
        winnerPoints = playerPoints[x];
        winner = (x+1);
      }
    }	
  }

  public void addHandler(SushiGoClientHandler hand) //adds a handler to the list of handlers
  {
    if(!player1Connected)//nobody is connected
    {
	state = 1;
        handler1 = hand;
        player1Connected = true;
        handler1.sendToClient("wait");//tells them to wait until all players are connected
    }
    else if(!player2Connected)//connects player 2 and gives player one the first play
    {
      handler2 = hand;
      state = 2;
      player2Connected = true;
      handler1.sendToClient("new");
      handler2.sendToClient("new");
      handler2.sendToClient("wait" + "," + player2Cards[0] + "," + player2Cards[1] + "," + player2Cards[2] + "," + player2Cards[3] + "," + player2Cards[4] + "," + player2Cards[5] + "," + player2Cards[6] + "," + player2Cards[7] + "," + player2Cards[8] + "," + player2Cards[9] + "," + "notableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
        handler1.sendToClient("play" + "," + player1Cards[0] + "," + player1Cards[1] + "," + player1Cards[2] + "," + player1Cards[3] + "," + player1Cards[4] + "," + player1Cards[5] + "," + player1Cards[6] + "," + player1Cards[7] + "," + player1Cards[8] + "," + player1Cards[9]+ "," + "notableCards" + "," + Integer.toString(playerPoints[0]) + "," + Integer.toString(playerPoints[1]) + "," + player1TableCards[0] + "," + player1TableCards[1] + "," + player1TableCards[2] + "," + player1TableCards[3] + "," + player1TableCards[4] + "," + player1TableCards[5] + "," + player1TableCards[6] + "," + player1TableCards[7] + "," + player1TableCards[8] + "," + player1TableCards[9] + "," + player2TableCards[0] + "," + player2TableCards[1] + "," + player2TableCards[2] + "," + player2TableCards[3] + "," + player2TableCards[4] + "," + player2TableCards[5] + "," + player2TableCards[6] + "," + player2TableCards[7] + "," + player2TableCards[8] + "," + player2TableCards[9] + ",");
    }
  }


}
