import java.util.Scanner;

public class blindsCalculator {

  private static int buyIn;


  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    
    System.out.print("Enter Number of Players: ");
    
    int numOfPlayers = userInput.nextInt();
    
    System.out.print("Enter BuyIn: $");
    
    double buyIn = userInput.nextDouble();
    
    double[] payOuts = calculatePayOuts(numOfPlayers, buyIn);

    int place = 1;
    
    for(int i = payOuts.length - 1; i >= 0; i++) {
      System.out.println("Place " + place + ": $" + payOuts[i]);
      
      place++;
    }
    
  }
  
  private static double[] calculatePayOuts(int players, double buyIn) {
    int numberOfPayOuts = players/3;
    
    double moneyPool = players * buyIn;
    
    double[] payNumbers = new double[numberOfPayOuts];
    
    double divisor = numberOfPayOuts * 3;
    
    for(int i = 0; i <= numberOfPayOuts - 1; i++) {
      double payOutValue = moneyPool/divisor;
      
      payNumbers[i] = payOutValue;
      
      divisor /= 2;
      
    }
    
    
    return payNumbers;
    
  }
  

}
