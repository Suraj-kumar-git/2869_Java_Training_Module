package Random_Date_Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class RandomDateScanner {

	public static void main(String[] args) {
		Random rand = new Random();
		int rnum = rand.nextInt(10)+1;
		System.out.print("Give your input: ");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		System.out.println("This is a random number: "+rnum); // Generates from 1 to 10
		if(rnum==input) {
			System.out.println("You guessed it right.");
		}else {
			System.out.println("You guessed it wrong.");
		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date today = new Date();
//		today.getDate();
		System.out.println("Today is: "+today);
	}

}
