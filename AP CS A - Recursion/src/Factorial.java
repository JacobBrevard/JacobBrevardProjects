
public class Factorial {
	public static int doMath(int num){

		//base case -- gets us out of recursive call

		if (num == 1){

			return 1;

		}

		return num * doMath(num-1);

	}

	public static void main (String[] args){

		System.out .println(doMath(4));

	}

}

