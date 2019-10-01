import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandling {

	public static void main(String[] args) {
	 Scanner scnr = new Scanner ("a");
	 try {
		 scnr.nextInt();
		 System.out.print("here");
	 }catch(InputMismatchException e) {
		 System.out.println("Caught Exception");
	 }finally {
		 System.out.println("In Finally");
	 }
	 System.out.println("Done");
	}
}
