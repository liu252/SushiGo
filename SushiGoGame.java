import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SushiGoGame
{
  public String[] gameDeck = {
                            "Pudding","Pudding","Pudding","Pudding","Pudding",
                            "Pudding","Pudding","Pudding","Pudding","Pudding",
                            "Egg","Egg","Egg","Egg","Egg",
                            "Salmon","Salmon","Salmon","Salmon","Salmon",
                            "Salmon","Salmon","Salmon","Salmon","Salmon",
                            "Squid","Squid","Squid","Squid","Squid",
                            "Maki Roll (1)","Maki Roll (1)","Maki Roll (1)",
                            "Maki Roll (1)","Maki Roll (1)","Maki Roll (1)",
                            "Maki Roll (2)","Maki Roll (2)","Maki Roll (2)",
                            "Maki Roll (2)","Maki Roll (2)","Maki Roll (2)",
                            "Maki Roll (2)","Maki Roll (2)","Maki Roll (2)",
                            "Maki Roll (2)","Maki Roll (2)","Maki Roll (2)",
                            "Maki Roll (3)","Maki Roll (3)","Maki Roll (3)",
                            "Maki Roll (3)","Maki Roll (3)","Maki Roll (3)",
                            "Maki Roll (3)","Maki Roll (3)",
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
                            "ChopSticks","ChopSticks","ChopSticks","ChopSticks"
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
  public int round = 1;


  public int playerCount = 0;
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
    playerCount = playerNum;
    shuffleArray(gameDeck);

    if (playerCount == 2)//10 cards each
    {
      int cardCount = 0;
      for(int x = 0; x < 20; x+=2)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        cardCount++;
      }
    }
    else if (playerCount == 3)//9 cards each
    {
      int cardCount = 0;
      for(int x = 0; x < 27; x+=3)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        cardCount++;
      }
    }
    else if (playerCount == 4)//8 cards each
    {
      int cardCount = 0;
      for(int x = 0; x < 32; x+=4)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        player4Cards[cardCount] = gameDeck[x+3];
        cardCount++;
      }
    }
    else if (playerCount == 5)//7 cards each
    {
      int cardCount = 0;
      for(int x = 0; x < 32; x+=5)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        player3Cards[cardCount] = gameDeck[x+2];
        player4Cards[cardCount] = gameDeck[x+3];
        player5Cards[cardCount] = gameDeck[x+4];
        cardCount++;
      }
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
  public void showCards(int playerNum)
  {
    if (playerNum == 1)
    {
      if (playerCount == 2)
      {
        for (int x = 0; x < 10; x++)
        {
          int numDisplay = x+1;
          System.out.print(numDisplay + "." + player1Cards[x] + " ");
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
           System.out.print(numDisplay + "." + player2Cards[x] + " ");
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
  }

  public void Move(int playerNum, int playerChoice)
  {
    int cardNum = playerChoice - 1;
    if (playerNum == 1)
    {
      String card = player1Cards[cardNum];
      player1Cards[cardNum] = " ";
      points(1 , card);
    }
    else if (playerNum == 2)
    {
      String card = player2Cards[cardNum];
      player2Cards[cardNum] = " ";
      points(2 , card);
    }
    else if (playerNum == 3)
    {
      String card = player3Cards[cardNum];
      player3Cards[cardNum] = " ";
      points(3 , card);
    }
    else if (playerNum == 4)
    {
      String card = player4Cards[cardNum];
      player4Cards[cardNum] = " ";
      points(4 , card);
    }
    else if (playerNum == 5)
    {
      String card = player5Cards[cardNum];
      player5Cards[cardNum] = " ";
      points(5 , card);
    }
  }

  public void points(int playerNum, String card)
  {
    int player = playerNum - 1;
    if (card.equals("Pudding"))
    {
      playerPuddingCount[player] = playerPuddingCount[player]+1;
    }
    else if (card.equals("Egg"))
    {
      playerPoints[player] = playerPoints[player] + 1;
    }
    else if(card.equals("Salmon"))
    {
      playerPoints[player] = playerPoints[player] + 2;
    }
    else if(card.equals("Squid"))
    {
      playerPoints[player] = playerPoints[player] + 3;
    }
    else if(card.equals("Maki Roll (1)"))
    {
      playerMakiCount[player] = playerMakiCount[player] + 1;
    }
    else if(card.equals("Maki Roll (2)"))
    {
      playerMakiCount[player] = playerMakiCount[player] + 2;
    }
    else if(card.equals("Maki Roll (3)"))
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
    }
  }


}
