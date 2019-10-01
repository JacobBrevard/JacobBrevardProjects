
public class Fibonacci {

	public static int fibIterative(int n) {
		
		int [] nums = new int [n + 1];
		
		if(n <= 1) {
			return 1;
		}else {
			
		
		nums[0] = 1;
		nums[1] = 1;
		for(int i = 2; i < nums.length; i++) {
			nums[i] = nums[i - 1] + nums[i - 2];
		}
		}
		return nums[n];
	}
	

	// recursive algorithm
	public static int fib (int n) { // Example of the use of recursion
		
		if(n == 0) {
			return 1; // Base Case
		}
		else if (n == 1) {
			return 1; // Base Case
		}
		else {
			return fib(n-1) + fib(n-2); // Smaller problems with the same algorithm
		}
	}
	
	public static void main(String[] args) {
		System.out.println("fib 0 = " + fib(0));
		System.out.println("fib 1 = " + fib(1));
		System.out.println("fib 2 = " + fib(2));
		System.out.println("fib 3 = " + fib(3));
		System.out.println("fib 4 = " + fib(4));
		
		System.out.println("");
		
		System.out.println("fib 0 = " + fibIterative(0));
		System.out.println("fib 1 = " + fibIterative(1));
		System.out.println("fib 2 = " + fibIterative(2));
		System.out.println("fib 3 = " + fibIterative(3));
		System.out.println("fib 4 = " + fibIterative(4));
	}
	
}
