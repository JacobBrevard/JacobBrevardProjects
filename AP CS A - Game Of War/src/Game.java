import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

public static void main(String[] args) {
// TODO Auto-generated method stub

        Deck deck1 = new Deck(); //create new Deck

        ArrayList<Card> player1 = new ArrayList<Card>(); //Separating the two decks. Storing the object of a card

        ArrayList<Card> player2 = new ArrayList<Card>(); //Separating the two decks. Storing the object of a card

        Random rand = new Random(); 

        for(int i = 52; i > 0; i--){  //Deals the cards into two decks

            int x = rand.nextInt(i);  // Generates Random Number for Starting Card being dealt

            if(i % 2 == 0){ //Populating the two different decks with the same number of cards

                player1.add(deck1.getDeck().get(x));

            }

            else{

                player2.add(deck1.getDeck().get(x));

            }

        }

         int p1d[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,-1}; //Array Created to Print out visual aspect

         System.out.print(Arrays.toString(p1d)); 

         System.out.println("Choose A Card(Not -1):");

         int randomCounter = 25; //Variable Created to keep the random number generated in bounds of the array list

         Scanner in = new Scanner(System.in); //Scanner Created for input

            int n = in.nextInt(); //Creating variable n to store user input

            in.nextLine(); 

            int xy = (int) (Math.random()*randomCounter + 1); // Random number created for computer generated array list index value

           System.out.println("Your Card:" + " " + player1.get(n).getValue()); // Prints out the card selected by the User

           System.out.println("Opponents Card:" + " " + player2.get(xy).getValue()); // Prints out the card selected with the Computers random number generator stored under variable xy

        int q = 0; // Variable that increments by 1 every time the program runs 

        int p4 = 0;    

        int p3 = 0;

        int playerCounter = 0; //Variable to keep track of player wins

        int opponentCounter = 0; //Variable to keep track of opponent wins

while (q < 24){

    for(int i = 0; i <= p1d.length-1; i++){

        if(p1d[i] == -1){

            if(i != 0) {
            p1d[i-1] = -1;
            }
            else {
            	p1d[0] = -1;
            }
        	

        }

    }

            if(randomCounter == 1){

            randomCounter = 2;

        }

        if(player1.get(n).getValue() > player2.get(xy).getValue()){ //Checks Value Of cards to see who wins

            System.out.println("You Win!!!");

            playerCounter = playerCounter + 1;

            System.out.println("Score-");

            System.out.println("You:" + " " + playerCounter);

            System.out.println("Opponent:" + " " + opponentCounter);

            player1.remove(n);

            player2.remove(xy);

    
            randomCounter = randomCounter - 1;

            xy = (int) (Math.random()*randomCounter + 1);

            

            System.out.print("[,");

            for(int i = 0; i <= p1d.length-1; i++){

                if(p1d[i] != -1){

                    System.out.print(p1d[i] +",");

                }

            }

            System.out.print("]");

            System.out.println("Choose A Card:");

            n = in.nextInt();

            in.nextLine();

            q = q + 1;

            System.out.println("Your Card:" + " " + player1.get(n).getValue());

            System.out.println("Opponents Card:" + " " + player2.get(xy).getValue());

        }

      else if (player2.get(xy).getValue() > player1.get(n).getValue()){

            System.out.println("You Loose");

            opponentCounter = opponentCounter + 1;

            System.out.println("Score-");

            System.out.println("You:" + " " + playerCounter);

            System.out.println("Opponent:" + " " + opponentCounter);

            player1.remove(n);

            player2.remove(xy);


            randomCounter = randomCounter - 1;

            xy = (int) (Math.random()*randomCounter + 1);

            System.out.print("[,");

            for(int i = 0; i <= p1d.length-1; i++){

                if(p1d[i] != -1){

                    System.out.print(p1d[i] +",");

                }

            }

            System.out.print("]");

            System.out.println("Choose A Card:");

            n = in.nextInt();

            in.nextLine(); 

            q = q + 1;

            System.out.println("Your Card:" + " " + player1.get(n).getValue());

            System.out.println("Opponents Card:" + " " + player2.get(xy).getValue());

        }

        else{ //If the value of the cards are a tie it will go to the value of the suit to determine who wins

            p3 = player1.get(n).getSuit();

            p4 = player2.get(xy).getSuit();

            System.out.println("It's A Tie. Comparing Suit Values to Determine Winner");
            
            System.out.println("Your Suit:" + " " + p3);

            System.out.println("Opponents Suit:" + " " + p4);

        }

            if(p3 > p4){

                System.out.println("You Win!!!");

                playerCounter = playerCounter + 1;

                System.out.println("Score-");

                System.out.println("You:" + " " + playerCounter);

                System.out.println("Opponent:" + " " + opponentCounter);

                player1.remove(n);

                player2.remove(xy);

                randomCounter = randomCounter - 1;

                xy = (int) (Math.random()*randomCounter + 1);

                System.out.print("[,");

                for(int i = 0; i <= p1d.length-1; i++){

                    if(p1d[i] != -1){

                        System.out.print(p1d[i] +",");

                    }

                }

                System.out.print("]");

                System.out.println("Choose A Card:");

                n = in.nextInt();

                in.nextLine(); 

                q = q + 1;

                System.out.println("Your Card:" + " " + player1.get(n).getValue());

                System.out.println("Opponents Card:" + " " + player2.get(xy).getValue());

            }

            else if (p4 > p3){

                System.out.println("You Loose");

                opponentCounter = opponentCounter + 1;

                System.out.println("Score-");

                System.out.println("You:" + " " + playerCounter);

                System.out.println("Opponent:" + " " + opponentCounter);

                player1.remove(n);

                player2.remove(xy);

            

                randomCounter = randomCounter - 1;

                xy = (int) (Math.random()*randomCounter + 1);

                System.out.print("[,");

                for(int i = 0; i <= p1d.length-1; i++){

                    if(p1d[i] != -1){

                        System.out.print(p1d[i] +",");

                    }

                }

                System.out.print("]");          
             
                System.out.println("Choose A Card:");
                
                n = in.nextInt();
                
                q = q + 1;
                
                in.nextLine(); 
                
                System.out.println("Your Card:" + " " + player1.get(n).getValue());
                
                System.out.println("Opponents Card:" + " " + player2.get(xy).getValue());

               
            } 
            }  
            if(playerCounter > opponentCounter) {
            	System.out.println("  ");
            	System.out.println("Game Over!! Player Wins!!");
            }
            else if(opponentCounter > playerCounter) {
            	System.out.println("  ");
            	System.out.println("Game Over!! Opponent/CPU Wins!!");
            }
            else {
            	System.out.println("  ");
            	System.out.println("Game Over!! It is a tie!!");
            }
            
            
 } 

}    




