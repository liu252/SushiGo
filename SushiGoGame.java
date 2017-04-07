import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class SushiGoGame
{
  public int[] gameDeck = {
                            1,1,1,1,1,1,1,1,1,1,
                            2,2,2,2,2,3,3,3,3,3,
                            3,3,3,3,3,4,4,4,4,4,
                            5,5,5,5,5,5,6,6,6,6,
                            6,6,6,6,6,6,6,6,7,7,
                            7,7,7,7,7,7,8,8,8,8,
                            8,8,8,8,8,8,8,8,8,8,
                            9,9,9,9,9,9,9,9,9,9,
                            9,9,9,9,10,10,10,10,10,10,
                            11,11,11,11,11,11,11,11,11,11,
                            11,11,11,11,12,12,12,12
                          } // 108 Cards
  public int[] playerPoints = {0,0,0,0,0};
  public int[] playerPuddingCount = {0,0,0,0,0};
  public int[] playerMakiCount = {0,0,0,0,0};
  public int[] playerPuddingCount = {0,0,0,0,0};
  public int[] playerDumplingCount = {0,0,0,0,0};
  public int[] playerTempuraCount = {0,0,0,0,0};
  public int[] playerSashimiCount = {0,0,0,0,0};
  public boolean[] playerWasabi = {false,false,false,false,false};
  public boolean[] playerChopsticks = {false,false,false,false,false};
  public int player1Cards[] = {0,0,0,0,0,0,0,0,0,0};
  public int player2Cards[] = {0,0,0,0,0,0,0,0,0,0};
  public int player3Cards[] = {0,0,0,0,0,0,0,0,0,0};
  public int player4Cards[] = {0,0,0,0,0,0,0,0,0,0};
  public int player5Cards[] = {0,0,0,0,0,0,0,0,0,0};
  public int round = 1;


  public int players = 0;
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

  SushiGoGame(int playersNum)
  {
    //Initialize Players
    players = playerNum;
    shuffleArray(gameDeck);

    if (players == 2)//10 cards each
    {
      int cardCount = 0;
      for(int x = 0; x < 20; x+=2)
      {
        player1Cards[cardCount] = gameDeck[x];
        player2Cards[cardCount] = gameDeck[x+1];
        cardCount++;
      }
    }
    else if (players == 3)//9 cards each
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
    else if (players == 4)//8 cards each
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
    else if (players == 5)//7 cards each
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
  static void shuffleArray(int[] ar)
  {
  // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
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
      playerWasabi[x] = false;
      playerChopsticks[x] = false;
    }
  }


}
